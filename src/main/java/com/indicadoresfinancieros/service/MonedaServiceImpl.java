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
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
public class MonedaServiceImpl implements MonedaService{

    @Autowired
    MonedaRepository monedaRepository;

    @Override
    public Flux<Moneda> findAll() {
        return monedaRepository.findAll();
    }

    @Override
    public Mono<Moneda> findById(String codigo) {
        return null; //monedaRepository.findById(codigo);
    }



    @Override
    public Mono<Moneda> findOne(String data) {
        System.out.println(data);
        //return monedaRepository.findById(data);
        return null;
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
        headers.set("x-rapidapi-key", "3e145c02a5mshd5aa02a0558e374p164099jsn355af2542355");

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Dolar> response = restTemplate.exchange(
                "https://currency26.p.rapidapi.com/convert/USD/CLP/1", HttpMethod.GET, entity, Dolar.class);

        return response.getBody();
    }



    @Override
    public Mono<Moneda> obtendoMoneda(LocalDate date) {
        Moneda moneda = new Moneda();
        BigDecimal valorUf = testeDeUf().getSerie().get(0).getValor();
        BigDecimal valorUtm = testeDeUtm().getSerie().get(0).getValor();
        BigDecimal valorDolar = testeDeDolar().getValor();
        Indicators indicators = new Indicators(valorUf, valorUtm, valorDolar);
        date = LocalDate.of(2020, 05, 10);
        moneda.setDate(date);
        moneda.setIndicators(indicators);
        return this.save(moneda);
    }


}
