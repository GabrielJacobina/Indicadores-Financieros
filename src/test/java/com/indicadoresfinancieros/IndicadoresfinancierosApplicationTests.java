package com.indicadoresfinancieros;

import com.indicadoresfinancieros.document.Moneda;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
class IndicadoresfinancierosApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void stringMono() {
        Mono<String> testeMono = Mono.just("Criar mono").log();

        testeMono.subscribe();
    }

    @Test
    public void mono() {
        Moneda moneda = new Moneda("2020-02-12", null);
        Mono<Moneda> mono = Mono.just(moneda).log();

        mono.subscribe(s -> {
            System.out.println(s.getDate());
        });
    }

}
