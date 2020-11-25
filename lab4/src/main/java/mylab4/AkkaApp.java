package mylab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class AkkaApp {
    public static void main(String[] args){
        ActorSystem system = ActorSystem.create("akkalab4");
        ActorRef storeActor = system.actorOf(Props.create(RouteActor.class, system));
    }
}
