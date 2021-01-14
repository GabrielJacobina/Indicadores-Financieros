package com.indicadoresfinancieros;

import com.indicadoresfinancieros.document.Moneda;
import com.indicadoresfinancieros.service.MonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDate;

//import static com.sun.corba.se.impl.util.RepositoryId.cache;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class MonedaHandler {

    @Autowired
    MonedaService monedaService;

    public Mono<ServerResponse> obterMonedas(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(monedaService.obtendoMoneda(LocalDate.now()), Moneda.class);
    }

}
