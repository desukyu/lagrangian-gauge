package id.lagrangian.gauge.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;

@QuarkusTest
public class IndonesianExchangeResourceTest {

    @Test
    public void testLatestEndpoint() {
        given()
                .when().get("/exchange-rates/latest")
                .then()
                    .statusCode(200);

    }
}
