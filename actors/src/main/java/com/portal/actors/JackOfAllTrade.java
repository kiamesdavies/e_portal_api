/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.actors;

import akka.actor.AbstractActor;

import akka.actor.ActorRef;

import akka.actor.*;
import static akka.actor.SupervisorStrategy.resume;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import com.portal.commons.events.GenericEvents;
import com.portal.commons.events.Sms;
import com.portal.entities.JpaSmsLog;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.mail.EmailException;
import play.db.jpa.JPAApi;
import play.libs.akka.InjectedActorSupport;
import scala.Option;
import scala.concurrent.duration.Duration;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class JackOfAllTrade extends AbstractActor implements InjectedActorSupport {

    //private ActorRef workerRouter;
    private final int nrOfWorkers = 50;
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private final JackOfAllTradeWorker.Factory childFactory;
    private Router router;

    @Inject
    public JackOfAllTrade(JackOfAllTradeWorker.Factory childFactory) {
        this.childFactory = childFactory;
        receive(
                ReceiveBuilder
                        .match(GenericEvents.class, s -> router.route(s, self()))
                        .build()
        );
    }

    @Override
    public void preStart() {
        if (router == null) {
            RoundRobinRoutingLogic roundRobinPool = new RoundRobinRoutingLogic();

            List<Routee> routees = new ArrayList<Routee>();
            for (int i = 0; i < nrOfWorkers; i++) {
                ActorRef r = injectedChild(() -> childFactory.create(), "jackOfAllTradeWorker-" + java.util.UUID.randomUUID().toString());
                getContext().watch(r);
                routees.add(new ActorRefRoutee(r));
            }
            this.router = new Router(roundRobinPool, routees);

        }

    }

    // Overriding postRestart to disable the call to preStart()
    // after restarts
    @Override
    public void postRestart(Throwable reason) {
    }

    // The default implementation of preRestart() stops all the children
    // of the actor. To opt-out from stopping the children, we
    // have to override preRestart()
    @Override
    public void preRestart(Throwable reason, Option<Object> message)
            throws Exception {
        // Keep the call to postStop(), but no stopping of children
        postStop();
    }

    private static SupervisorStrategy strategy
            = new OneForOneStrategy(10, Duration.create("1 minute"), DeciderBuilder.
                    match(ConnectException.class, e -> resume()).
                    match(EmailException.class, e -> resume()).
                    match(ConcurrentModificationException.class, e -> resume()).
                    matchAny(o -> resume()).build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    public static class JackOfAllTradeWorker extends AbstractActor {

        @Inject
        public JackOfAllTradeWorker(JPAApi jPAApi) {
            receive(ReceiveBuilder
                    .match(Sms.class, s -> {
                        System.out.println("SMS");
                        String body = s.body.replaceAll("<sms>", "").replaceAll("</sms>", "");
                        JpaSmsLog jpaSmsLog = new JpaSmsLog();
                        jpaSmsLog.setBody(body);
                        jpaSmsLog.setStatus("pending");
                        jpaSmsLog.setSubject(s.subject);
                        jpaSmsLog.setDateCreated(new Date());
                        jPAApi.withTransaction(() -> jPAApi.em().persist(jpaSmsLog));
                    })
                    .build()
            );
        }

        public interface Factory {

            Actor create();
        }
    }

}
