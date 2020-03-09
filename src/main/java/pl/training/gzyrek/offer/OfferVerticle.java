package pl.training.gzyrek.offer;

import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.training.gzyrek.offer.model.Offer;
import pl.training.gzyrek.offer.model.OfferDto;
import pl.training.gzyrek.offer.service.OfferService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

import java.util.List;

public class OfferVerticle extends AbstractVerticle {

    private OfferService offerService = new OfferService();
    private static Logger logger = LoggerFactory.getLogger(OfferVerticle.class);

      public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new OfferVerticle());
    //vertx.deployVerticle(MyVerticle.class);
  }
    @Override
    public void start(Future<Void> fut) {
        Router router = Router.router(vertx);
        router.get("/offers/olx/:keyword").handler(this::getOffers);

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );
    }

    private void getOffers(RoutingContext routingContext) {
        WebClient client = WebClient.create(vertx);
        final String keyword = sanitizeKeyword(routingContext.request().getParam("keyword"));
        client
                .get(443, "olx.pl", "/oferty/q-" + keyword + "/").ssl(true)
                .send(ar -> {
                    if (ar.succeeded()) {
                        HttpResponse<Buffer> response = ar.result();
                        logger.info("Received response with status code: " + response.statusCode());
                        logger.debug("Received response with body: " + response.bodyAsString());
                        List<Offer> offers = offerService.prepareOffers(response.bodyAsString());
                        routingContext.response()
                                .putHeader("content-type", "application/json; charset=utf-8")
                                .end(Json.encodePrettily(new OfferDto(offers)));
                    } else {
                        logger.error("Something went wrong " + ar.cause().getMessage());
                    }
                });
    }

    private String sanitizeKeyword(String keyword) {
        return keyword.trim().replace(" ", "-");
    }


}
