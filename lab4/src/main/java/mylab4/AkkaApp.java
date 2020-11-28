package mylab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;

public class AkkaApp {
    public static void main(String[] args){
        ActorSystem system = ActorSystem.create("akkalab4");
        ActorRef routeActor = system.actorOf(Props.create(RouteActor.class, system));
        final Http http = Http.get(system);
        
    }
}
