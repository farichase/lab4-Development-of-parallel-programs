package mylab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
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

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class AkkaApp {

    private final static Timeout timeout = Timeout.create(Duration.ofSeconds(5));
    private final static int PORT = 8080;
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

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("akkalab4");
        ActorRef routeActor = system.actorOf(Props.create(RouteActor.class, system));
        final Http http = Http.get(system);
        final AkkaApp app = new AkkaApp();
        final Materializer materializer = ActorMaterializer.create(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> flow =
                app.createRoute(system, routeActor).flow(system, materializer);
        final CompletionStage<ServerBinding> bindingCompletionStage = http.bindAndHandle(
                flow,
                ConnectHttp.toHost("localhost", PORT),
                materializer
        );
        System.out.println("Server online at http://localhost:8080");
        System.in.read();
        bindingCompletionStage.thenCompose(ServerBinding::unbind).thenAccept(unbound -> system.terminate());
    }
}
