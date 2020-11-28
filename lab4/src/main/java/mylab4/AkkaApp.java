package mylab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import akka.util.Timeout;
import scala.concurrent.Future;

import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;

public class AkkaApp {

    private final static Timeout timeout = Timeout.create(Duration.ofSeconds(5));
    private static Route createRoute(ActorSystem system, ActorRef routeActor){
        return route(
                get(() -> parameter( "packageID", key -> {
                    Future<Object> res = Patterns.ask(routeActor, key, timeout);
                    return completeOKWithFuture(res, Jackson.marshaller());
                }
                )).orElse(
                post(() -> entity(
                    Jackson.unmarshaller(StoreFunction.class), msg -> {
                        routeActor.tell(msg, ActorRef.noSender());
                        return complete("Tests!");
                    })
                ))
        );
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
