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
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.ResponseEntity.ok;


@Service
public class MonedaServiceImpl implements MonedaService{

    @Autowired
    private MonedaRepository monedaRepository;

    public Mono<Moneda> findByData(LocalDate data) {
        String dataS = data.toString();
        Mono<Moneda> monedaFlux = monedaRepository.findById(dataS);
        return monedaFlux;
    }

    public Mono<Moneda> save(Moneda moneda) {
        return monedaRepository.save(moneda);
    }

    public MindicadorApi obterMoeda(String url) {
        RestTemplate restTemplate = new RestTemplate();
        MindicadorApi mindicadorApi = restTemplate.getForObject(
                url, MindicadorApi.class);

        return mindicadorApi;
    }

    public Dolar obterDolar() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "currency26.p.rapidapi.com");
        headers.set("x-rapidapi-key", "eeacb905efmshd6297967a0b2e40p1858aajsn6f1d82ef9693");

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Dolar> response = restTemplate.exchange(
                "https://currency26.p.rapidapi.com/convert/USD/CLP/1", HttpMethod.GET, entity, Dolar.class);
        return response.getBody();
    }

    public Mono<Moneda> salvarMoneda(String dataBr, String data) {
        Moneda moneda = new Moneda();
        BigDecimal valorUf = obterMoeda("https://mindicador.cl/api/uf/".concat(dataBr)).getSerie().get(0).getValor();
        BigDecimal valorUtm = obterMoeda("https://mindicador.cl/api/utm/".concat(dataBr)).getSerie().get(0).getValor();
        BigDecimal valorDolar = obterDolar().getValor();
        Indicators indicators = new Indicators(valorUf, valorUtm, valorDolar);
        moneda.setDate(data);
        moneda.setIndicators(indicators);
        return this.save(moneda);
    }

    @Override
    public Mono<Moneda> obtendoMoneda(LocalDate date) {
        String dataBr = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String data = date.toString();
        Mono<Moneda> monedaB = this.findByData(date)
                .switchIfEmpty(this.salvarMoneda(dataBr, data));

        return monedaB;
    }


}
