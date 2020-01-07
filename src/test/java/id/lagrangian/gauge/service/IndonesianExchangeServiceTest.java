package id.lagrangian.gauge.service;

import id.lagrangian.gauge.exception.LastUpdateFindException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class IndonesianExchangeServiceTest {

    @Inject
    IndonesianExchangeService service;

    @Test
    public void testFindLastUpdate() throws LastUpdateFindException, IOException {
        System.out.println(service.findLateUpdate());
    }

    @Test
    public void testFindExchangeRates() throws IOException {
        System.out.println(service.findExchangeRates());
    }

    @Test
    public void testLatestExchageRates() throws IOException, LastUpdateFindException {
        System.out.println(service.latest());
    }

}