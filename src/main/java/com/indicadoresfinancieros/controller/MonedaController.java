package com.indicadoresfinancieros.controller;

import com.indicadoresfinancieros.document.Dolar;
import com.indicadoresfinancieros.document.MindicadorApi;
import com.indicadoresfinancieros.document.Moneda;
import com.indicadoresfinancieros.service.MonedaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Calendar;

@Api(value = "Indicadores Financieros API")
@RestController
@RequestMapping("/bideafactory")
public class MonedaController {

    @Autowired
    MonedaService monedaService;

    @ApiOperation(value = "Muestra información del dolar, UF y UTM del día actual.")
    @GetMapping("/indicadoreshoy")
    public ResponseEntity<Mono<Moneda>> obterMoedas() {
        try {
            Mono<Moneda> monedaMono = monedaService.obtendoMoneda(LocalDate.now());
            return new ResponseEntity<Mono<Moneda>>(monedaMono, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
