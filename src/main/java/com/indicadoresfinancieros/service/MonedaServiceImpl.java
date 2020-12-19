package com.indicadoresfinancieros.service;

import com.indicadoresfinancieros.document.Dolar;
import com.indicadoresfinancieros.document.Indicators;
import com.indicadoresfinancieros.document.MindicadorApi;
import com.indicadoresfinancieros.document.Moneda;
import com.indicadoresfinancieros.repository.MonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
public class MonedaServiceImpl implements MonedaService{

    @Autowired
    private MonedaRepository monedaRepository;

    private Boolean temNoBanco = false;

    @Override
    public Flux<Moneda> findAll() {
        return monedaRepository.findAll();
    }

    @Override
    public Mono<Moneda> findById(String codigo) {
        return monedaRepository.findById(codigo);
    }

    @Override
    public Mono<Moneda> findByData(LocalDate data) {
        //data = LocalDate.of(2020, 05, 10);
        LocalDateTime dateTime1 = data.atStartOfDay();
        System.out.println("Print do dateTime: " + dateTime1);
        Mono<Moneda> monedaFlux = monedaRepository.findMonedaByDate(dateTime1);

        return monedaFlux;
    }

    @Override
    public Mono<Moneda> save(Moneda moneda) {
        return monedaRepository.save(moneda);
    }

    @Override
    public MindicadorApi testeDeUf() {
        RestTemplate restTemplate = new RestTemplate();
        MindicadorApi mindicadorApi = restTemplate.getForObject(
                "https://mindicador.cl/api/uf/15-10-2020", MindicadorApi.class);

        return mindicadorApi;
    }

    @Override
    public MindicadorApi testeDeUtm() {
        RestTemplate restTemplate = new RestTemplate();
        MindicadorApi mindicadorApi = restTemplate.getForObject(
                "https://mindicador.cl/api/utm/15-10-2020", MindicadorApi.class);

        return mindicadorApi;
    }

    @Override
    public MindicadorApi obterMoeda(String url) {
        RestTemplate restTemplate = new RestTemplate();
        MindicadorApi mindicadorApi = restTemplate.getForObject(
                url, MindicadorApi.class);

        return mindicadorApi;
    }

    @Override
    public Dolar testeDeDolar() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "currency26.p.rapidapi.com");
        headers.set("x-rapidapi-key", "eeacb905efmshd6297967a0b2e40p1858aajsn6f1d82ef9693");

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Dolar> response = restTemplate.exchange(
                "https://currency26.p.rapidapi.com/convert/USD/CLP/1", HttpMethod.GET, entity, Dolar.class);

        return response.getBody();
    }



    @Override
    public Mono<Moneda> obtendoMoneda(LocalDate date) {
        //date = LocalDate.of(2051, 9, 24);
        Mono<Moneda> monedaB = this.findByData(date);
        System.out.println(monedaB);
        //if (monedaB != null) {
           // return monedaB;
        //}
        Moneda moneda = new Moneda();
        BigDecimal valorUf = testeDeUf().getSerie().get(0).getValor();
        BigDecimal valorUtm = testeDeUtm().getSerie().get(0).getValor();
        BigDecimal valorDolar = testeDeDolar().getValor();
        Indicators indicators = new Indicators(valorUf, valorUtm, valorDolar);
        moneda.setDate(date);
        moneda.setIndicators(indicators);
        return this.save(moneda);

    }


}
