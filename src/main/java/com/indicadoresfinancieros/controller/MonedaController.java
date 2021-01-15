package com.indicadoresfinancieros.controller;

import com.indicadoresfinancieros.document.Moneda;
import com.indicadoresfinancieros.service.MonedaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Api(value = "Indicadores Financieros API")
@RestController
@RequestMapping("/bideafactory")
public class MonedaController {

    @Autowired
    MonedaService monedaService;

    @ApiOperation(value = "Muestra información del dolar, UF y UTM del día actual.")
    @GetMapping("/indicadoreshoy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Retorna información de indicadores financieros"),
            @ApiResponse(responseCode = "400", description = "Request Invalido")
    })
    public ResponseEntity<Mono<Moneda>> obterMoedas() {
        Mono<Moneda> monedaMono = monedaService.obtendoMoneda(LocalDate.now());
        return new ResponseEntity<>(monedaMono, HttpStatus.OK);
    }
}
