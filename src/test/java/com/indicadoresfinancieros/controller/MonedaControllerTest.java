package com.indicadoresfinancieros.controller;

import com.indicadoresfinancieros.document.Moneda;
import com.indicadoresfinancieros.service.MonedaServiceImpl;
import com.indicadoresfinancieros.util.MonedaCreator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
class MonedaControllerTest {

    @InjectMocks
    private MonedaController monedaController;

    @Mock
    private MonedaServiceImpl monedaServiceMock;

    private final Moneda moneda = MonedaCreator.createValidMoneda();

    @BeforeAll
    public static void blockHoundSetup() {
        BlockHound.install();
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

    @BeforeEach
    public void setUp() {
        BDDMockito.when(monedaServiceMock.obtendoMoneda(ArgumentMatchers.any()))
                .thenReturn(Mono.just(moneda));
    }

    @Test
    @DisplayName("obterMoedas returns Mono with moneda when Successful")
    public void obterMoedas_ReturnMonoMoneda_whenSuccessful() {
        Mono<Moneda> monedaMono = monedaController.obterMoedas().getBody();
        assert monedaMono != null;
        StepVerifier.create(monedaMono)
                .expectSubscription()
                .expectNext(moneda)
                .verifyComplete();
    }

}