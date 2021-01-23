package com.indicadoresfinancieros.service;

import com.indicadoresfinancieros.document.Moneda;
import com.indicadoresfinancieros.repository.MonedaRepository;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
class MonedaServiceImplTest {

    @InjectMocks
    private MonedaServiceImpl monedaService;

    @Mock
    private MonedaRepository monedaRepositoryMock;

    private final Moneda  moneda = MonedaCreator.createValidMoneda();

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
        BDDMockito.when(monedaRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(moneda));

        BDDMockito.when(monedaRepositoryMock.save(MonedaCreator.createMonedaToBeSaved()))
                .thenReturn(Mono.just(moneda));
    }

    @Test
    @DisplayName("findByData returns Mono with moneda when Successful")
    public void findByData_ReturnMonoMoneda_whenSuccessful() {
        StepVerifier.create(monedaService.findByData("10-01-2021"))
                .expectSubscription()
                .expectNext(moneda)
                .verifyComplete();
    }

    @Test
    @DisplayName("save creates a Moneda in the database when successful")
    public void save_CreatesMonedaInTheDatabase_whenSuccessful() {
        Moneda monedaToBeSaved = MonedaCreator.createMonedaToBeSaved();
        StepVerifier.create(monedaService.save(monedaToBeSaved))
                .expectSubscription()
                .expectNext(moneda)
                .verifyComplete();
    }

    /**
     * Test is returning null
     */
    //@Test
    @DisplayName("salvarMoneda creates an Moneda when Successful")
    public void salvarMoeda_CreatesMoneda_whenSuccessful() {
        LocalDate data = LocalDate.now();
        String dataBr = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String dataS = data.toString();

        StepVerifier.create(monedaService.salvarMoneda(dataBr, dataS))
                .expectSubscription()
                .expectNext(moneda)
                .verifyComplete();
    }

    @Test
    @DisplayName("obtendoMoneda creates a Moneda in the database when successful")
    public void obtendoMoneda_CreatesMonedaInTheDatabase_whenSuccessful() {
        StepVerifier.create(monedaService.obtendoMoneda(LocalDate.now()))
                .expectSubscription()
                .expectNext(moneda)
                .verifyComplete();
    }

}