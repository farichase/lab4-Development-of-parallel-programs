package mylab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;

import static akka.http.javadsl.server.Directives.route;

public class AkkaApp {

    private static Route createRoute(ActorSystem system, ActorRef routeActor){
        return route(get(() -> parameter(
                
        ) ));
    }

    public static void main(String[] args){
        ActorSystem system = ActorSystem.create("akkalab4");
        ActorRef routeActor = system.actorOf(Props.create(RouteActor.class, system));
        final Http http = Http.get(system);
        final AkkaApp app = new AkkaApp();
        final Materializer materializer = ActorMaterializer.create(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> flow =
                createRoute(system, routeActor)
    }
}
