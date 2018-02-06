/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.actors;

import akka.actor.AbstractActor;
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import static akka.actor.SupervisorStrategy.resume;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import com.google.common.collect.ImmutableMap;
import com.portal.commons.events.Sms;
import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.configuration.IConfiguration;
import com.portal.configuration.IMessageTemplate;
import com.portal.payment.PaymentManager;
import java.net.ConnectException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

import play.db.jpa.JPAApi;
import play.libs.akka.InjectedActorSupport;
import scala.Option;
import scala.concurrent.duration.Duration;

/**
 *
 * @author poseidon
 */
public class CronJob extends AbstractActor implements InjectedActorSupport {

    private final int nrOfWorkers = 5;
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private final CronJob.CronJobWorker.Factory childFactory;
    private Router router;

    @Inject
    public CronJob(CronJobWorker.Factory childFactory) {
        this.childFactory = childFactory;
        receive(
                ReceiveBuilder
                        .match(String.class, s -> router.route(s, self()))
                        .build()
        );
    }

    @Override
    public void preStart() {
        if (router == null) {
            RoundRobinRoutingLogic roundRobinPool = new RoundRobinRoutingLogic();

            List<Routee> routees = new ArrayList<Routee>();
            for (int i = 0; i < nrOfWorkers; i++) {
                ActorRef r = injectedChild(() -> childFactory.create(), "cronjob-" + java.util.UUID.randomUUID().toString());
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

    private static final SupervisorStrategy STRATEGY
            = new OneForOneStrategy(10, Duration.create("1 minute"), DeciderBuilder.
                    match(ConnectException.class, e -> resume()).
                    match(ConcurrentModificationException.class, e -> resume()).
                    matchAny(o -> resume()).build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return STRATEGY;
    }

    public static class CronJobWorker extends AbstractActor {

        @Inject
        public CronJobWorker(JPAApi jPAApi, PaymentManager paymentManager, IConfiguration iConfiguration, ActorSystem actorSystem, IMessageTemplate messageTemplate) {
            receive(ReceiveBuilder
                    .match(String.class, s -> s.equalsIgnoreCase("InitializePayment"), s -> {
                        play.Logger.info("Generating payment");
                        try {
                            String configuration = jPAApi.withTransaction(() -> {
                                try {
                                    return iConfiguration.getConfiguration(IConfiguration.Config.RENEWAL_NOTIFICATION_DATES);
                                } catch (ResourceNotFound ex) {
                                    play.Logger.error("Cant find resource", ex);
                                }
                                return "";
                            });

                            List<String> split = Arrays.asList(configuration.split(","));
                            play.Logger.info("Found days for configuration {}", split);
                            split.forEach(day -> {
                                LocalDate localDate = LocalDate.now().plusDays(Integer.valueOf(day.trim()));
                                play.Logger.info("Getting payment for expiry Date {}", localDate);

                                jPAApi.withTransaction(() -> paymentManager.getAllExpiredPayment(localDate)).forEach(payment -> {

                                    jPAApi.withTransaction(() -> {
                                        try {

                                            String msgTemplate = messageTemplate.getMessageTemplate(IMessageTemplate.MessageType.EXPIRED_PAYMENT).getSmsTemplate();
                                            String name = String.format("%s %s", Objects.toString(payment.getAppUserId().getFirstName(), ""), Objects.toString(payment.getAppUserId().getLastName(), ""));
                                            String body = messageTemplate.parseMessageTemplate(msgTemplate, ImmutableMap.of("name", name.trim(), "day", day, "teacherRegNumber", payment.getAppUserId().getUserName()));
                                            Sms sms = new Sms(payment.getAppUserId().getMobileNumber(), "TRCN", body);
                                            actorSystem.scheduler().scheduleOnce(Duration.create(1, TimeUnit.SECONDS),
                                                    () -> actorSystem.eventStream().publish(sms), actorSystem.dispatcher());
                                        } catch (Exception ex) {
                                            play.Logger.error("Invoice has already been created by  another or something else happened ", ex);

                                        }
                                    });

                                });
                            });
                            LocalDate today = LocalDate.now();
                            play.Logger.info("Initializing payment for this day {}", today);

                            jPAApi.withTransaction(() -> paymentManager.getAllExpiredPayment(today)).forEach(payment -> {

                                jPAApi.withTransaction(() -> {
                                    try {

                                        paymentManager.initializePaymentJpa(payment.getAppUserId().getAppUserId(), "online", null, null);

                                        String msgTemplate = messageTemplate.getMessageTemplate(IMessageTemplate.MessageType.PAYMENT_RENEWAL_STARTED).getSmsTemplate();
                                        String name = String.format("%s %s", Objects.toString(payment.getAppUserId().getFirstName(), ""), Objects.toString(payment.getAppUserId().getLastName(), ""));
                                        String body = messageTemplate.parseMessageTemplate(msgTemplate, ImmutableMap.of("name", name.trim(), "teacherRegNumber", payment.getAppUserId().getUserName()));
                                        Sms sms = new Sms(payment.getAppUserId().getMobileNumber(), "TRCN", body);
                                        actorSystem.scheduler().scheduleOnce(Duration.create(1, TimeUnit.SECONDS),
                                                () -> actorSystem.eventStream().publish(sms), actorSystem.dispatcher());
                                    } catch (Exception ex) {
                                        play.Logger.error("Invoice has already been created by  another or something else happened ", ex);

                                    }
                                });

                            });

                        } catch (Exception e) {

                        }
                    }).build());
        }

        public interface Factory {

            Actor create();
        }
    }
}
