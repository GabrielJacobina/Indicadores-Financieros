package com.indicadoresfinancieros.service;

import com.indicadoresfinancieros.document.Moneda;
import com.indicadoresfinancieros.repository.MonedaRepository;
import com.indicadoresfinancieros.util.MonedaCreator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class MonedaServiceImplTest {

    @InjectMocks
    private MonedaServiceImpl monedaService;

    @Mock
    private MonedaRepository monedaRepositoryMock;

    private final Moneda  moneda = MonedaCreator.createValidMoneda();

    @BeforeEach
    public void setUp() {
        BDDMockito.when(monedaRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(moneda));
    }

    @Test
    @DisplayName("findByData returns Mono with moneda when it exists")
    public void findByData_ReturnMonoMoneda_whenSuccesful() {
        StepVerifier.create(monedaService.findByData("10-01-2021"))
                .expectSubscription()
                .expectNext(moneda)
                .verifyComplete();
    }

}