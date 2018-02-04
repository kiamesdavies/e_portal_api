/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.binder;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.portal.commons.events.GenericEvents;
import javax.inject.Inject;
import javax.inject.Named;

import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.Duration;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class Genesis {

    @Inject
    public Genesis(ActorSystem actorSystem
    // , @Named("jackOfAllTrade") ActorRef jackActorRef
    //, @Named("invoiceGenerator") ActorRef invoiceGeneratorActorRef
    ) {

       // actorSystem.eventStream().subscribe(jackActorRef, GenericEvents.class);

//        QuartzSchedulerExtension quartzSchedulerExtension = QuartzSchedulerExtension.get(actorSystem);
//
//        Date schedule = quartzSchedulerExtension.schedule("Every7AM", invoiceGeneratorActorRef, "GenerateInvoice");
//
//        play.Logger.info("Quartz Schedule on {}", schedule);
//
//        actorSystem.scheduler().scheduleOnce(Duration.create(30, TimeUnit.MINUTES),
//                () -> {
//                    System.out.println("Notifying invoce generator on " + new Date());
//                    invoiceGeneratorActorRef.tell("GenerateInvoice", ActorRef.noSender());
//                }, actorSystem.dispatcher());
    }

}
