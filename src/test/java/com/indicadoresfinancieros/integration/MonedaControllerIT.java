package com.indicadoresfinancieros.integration;

import com.indicadoresfinancieros.document.Moneda;
import com.indicadoresfinancieros.repository.MonedaRepository;
import com.indicadoresfinancieros.service.MonedaServiceImpl;
import com.indicadoresfinancieros.util.MonedaCreator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@Import(MonedaServiceImpl.class)
public class MonedaControllerIT {

    @MockBean
    private MonedaRepository monedaRepositoryMock;

    @Autowired
    private WebTestClient testClient;

    private final Moneda moneda = MonedaCreator.createValidMoneda();

    @BeforeAll
    public static void blockHoundSetup() {
        BlockHound.install();
    }

    @BeforeEach
    public void setUp() {
        BDDMockito.when(monedaRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(moneda));
    }

    @Test
    public void blockHoundWorks() {
        try {
            FutureTask<?> task = new FutureTask<>(() -> {
                Thread.sleep(0);
                return "";
            });
            Schedulers.parallel().schedule(task);

            task.get(10, TimeUnit.SECONDS);
            Assertions.fail("should fail");
        } catch (Exception e) {
            Assertions.assertTrue(e.getCause() instanceof BlockingOperationError);
        }
    }

    @Test
    @DisplayName("obterMoedas returns Mono with moneda when Successful")
    public void obterMoedas_ReturnMonoMoneda_whenSuccessful() {
        testClient
                .get()
                .uri("/bideafactory/indicadoreshoy")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.date").isEqualTo(moneda.getDate())
                .jsonPath("$.indicators.dolar").isEqualTo(moneda.getIndicators().getDolar())
                .jsonPath("$.indicators.uf").isEqualTo(moneda.getIndicators().getUf())
                .jsonPath("$.indicators.utm").isEqualTo(moneda.getIndicators().getUtm());
    }

}
