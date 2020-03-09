package pl.training.gzyrek.offer;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import io.vertx.reactivex.ext.unit.Async;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * This is our JUnit test for our verticle. The test uses vertx-unit, so we declare a custom runner.
 */
@ExtendWith(VertxExtension.class)
public class OfferVerticleTest {

  private Vertx vertx;
  private Integer port;

  @Disabled
  @Test
  public void testMyApplication() {
      VertxTestContext context = new VertxTestContext();

//      vertx.createHttpClient().get(port, "localhost","/offers/olx/hyundai", httpClientResponse -> {
//          assert 200 = httpClientResponse.handler(body -> {
//              context.assertComplete(body.toJson());
//          }).statusCode();
//      })
//    vertx.createHttpClient().getNow(port, "localhost", "/offers/olx/hyundai", response -> {
//      response.handler(body -> {
//        context.assertComplete(body.toString());
//        async.complete();
//      });
//    });
  }
}
