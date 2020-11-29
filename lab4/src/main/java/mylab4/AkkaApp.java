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
import scala.concurrent.Future;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class AkkaApp {

    private final static int TIMEOUT = 4000;
    private final static int PORT = 8080;
    private final static String PARAMETER_NAME = "packageId";

    private static Route createRoute(ActorRef routeActor){
        return route(
                get(() -> parameter( PARAMETER_NAME, key -> {
                    Future<Object> res = Patterns.ask(routeActor, key, TIMEOUT);
                    return completeOKWithFuture(res, Jackson.marshaller());
                }
                )),
                post(() -> entity(
                    Jackson.unmarshaller(StoreFunction.class), msg -> {
                        routeActor.tell(msg, ActorRef.noSender());
                        return complete("Success!");
                    })
                )
        );
    }

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("akkalab4");
        ActorRef routeActor = system.actorOf(Props.create(RouteActor.class, system));
        final Http http = Http.get(system);
        final AkkaApp app = new AkkaApp();
        final Materializer materializer = ActorMaterializer.create(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> flow =
                app.createRoute(routeActor).flow(system, materializer);
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
