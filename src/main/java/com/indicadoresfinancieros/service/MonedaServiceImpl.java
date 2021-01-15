package com.indicadoresfinancieros.service;

import com.indicadoresfinancieros.document.Moneda;
import com.indicadoresfinancieros.repository.MonedaRepository;
import com.indicadoresfinancieros.request.Dolar;
import com.indicadoresfinancieros.request.Indicators;
import com.indicadoresfinancieros.request.MindicadorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
@RequiredArgsConstructor
public class MonedaServiceImpl implements MonedaService{

    private final MonedaRepository monedaRepository;

    Moneda moneda = new Moneda();

    public Mono<Moneda> findByData(String dataS) {
        Mono<Moneda> mono = monedaRepository.findById(dataS);
        mono.subscribe(c -> this.moneda = c);
        mono.toProcessor().block();
        return mono;
    }

    public Mono<Moneda> save(Moneda moneda) {
        return monedaRepository.save(moneda);
    }

    public MindicadorApi obterMoeda(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                url, MindicadorApi.class);
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
        try {
            BigDecimal valorUf = obterMoeda("https://mindicador.cl/api/uf/".concat(dataBr)).getSerie().get(0).getValor();
            BigDecimal valorUtm = obterMoeda("https://mindicador.cl/api/utm/".concat(dataBr)).getSerie().get(0).getValor();
            BigDecimal valorDolar = obterDolar().getValor();
            Indicators indicators = new Indicators(valorUf, valorUtm, valorDolar);
            moneda.setDate(data);
            moneda.setIndicators(indicators);
            return this.save(moneda);
        } catch (Exception e) {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request"));
        }
    }

    @Override
    public Mono<Moneda> obtendoMoneda(LocalDate data) {
        String dataBr = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String dataS = data.toString();
        Mono<Moneda> monedaB = this.findByData(dataS);
        if (this.moneda.getDate() == null) {
            return this.salvarMoneda(dataBr, dataS);
        }
        return monedaB;
    }

}
