package id.lagrangian.gauge.resouce;

import id.lagrangian.gauge.exception.LastUpdateFindException;
import id.lagrangian.gauge.model.ExchangeRates;
import id.lagrangian.gauge.service.IndonesianExchangeService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/exchange-rates")
public class IndonesianExchangeResource {

    @Inject
    IndonesianExchangeService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/latest")
    public ExchangeRates latest() throws IOException, LastUpdateFindException {
        return service.latest();
    }
}
