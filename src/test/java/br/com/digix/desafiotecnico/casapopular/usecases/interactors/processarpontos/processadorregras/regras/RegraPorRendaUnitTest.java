package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.processadorregras.regras;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.FamiliaBuilder.umaFamilia;
import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.PessoaBuilder.umaPessoa;
import static org.assertj.core.api.Assertions.assertThat;

class RegraPorRendaUnitTest {

    private RegraPorRenda regra;

    @BeforeEach
    void setup() {
        this.regra = new RegraPorRenda();
    }

    private static Stream<Arguments> provideFamilyWithHouseholdIncomeLessOrEqualToNineHundred() {
        final var familia1 = umaFamilia()
                .id(1)
                .pai(umaPessoa().nome("Jose").renda(new BigDecimal("300.00")).build())
                .mae(umaPessoa().nome("Maria").renda(new BigDecimal("300.00")).build())
                .dependentes(List.of(umaPessoa().nome("John").renda(new BigDecimal("300.00")).build()))
                .build();

        final var familia2 = umaFamilia()
                .id(2)
                .pai(umaPessoa().nome("Jose").renda(BigDecimal.ZERO).build())
                .mae(umaPessoa().nome("Maria").renda(new BigDecimal("300.00")).build())
                .dependentes(List.of(umaPessoa().nome("John").renda(new BigDecimal("300.00")).build()))
                .build();

        final var familia3 = umaFamilia()
                .id(3)
                .pai(umaPessoa().nome("Jose").renda(new BigDecimal("300.00")).build())
                .mae(umaPessoa().nome("Maria").renda( BigDecimal.ZERO).build())
                .dependentes(List.of(umaPessoa().nome("John").renda(new BigDecimal("300.00")).build()))
                .build();

        final var familia4 = umaFamilia()
                .id(4)
                .pai(umaPessoa().nome("Jose").renda(new BigDecimal("100.00")).build())
                .mae(umaPessoa().nome("Maria").renda( new BigDecimal("100.00")).build())
                .dependentes(List.of(
                        umaPessoa().nome("John").renda(new BigDecimal("300.00")).build(),
                        umaPessoa().nome("Mary").renda(new BigDecimal("300.00")).build()
                        )
                )
                .build();

        final var familia5 = umaFamilia()
                .id(5)
                .pai(umaPessoa().nome("Jose").renda(new BigDecimal("299.99")).build())
                .mae(umaPessoa().nome("Maria").renda( new BigDecimal("399.99")).build())
                .dependentes(new ArrayList<>())
                .build();

        return Stream.of(
                Arguments.of(
                        Named.of("Pai (300.0) Mae (300.0) 1 Filho (300.0)", familia1)),
                Arguments.of(
                        Named.of("Pai (0.0) Mae (300.0) 1 Filho (300.0)", familia2)),
                Arguments.of(
                        Named.of("Pai (300.0) Mae (0.0) 1 Filho (300.0)", familia3)),
                Arguments.of(
                        Named.of("Pai (100.0) Mae (100.0) 2 Filhos (300.0 + 300.0)", familia4)),
                Arguments.of(
                        Named.of("Pai (299.99) Mae (399.99) Sem Filhos", familia5))
        );
    }

    private static Stream<Arguments> provideFamilyWithHouseholdIncomeLessOrEqualToFifteenHundred() {
        final var familia1 = umaFamilia()
                .id(1)
                .pai(umaPessoa().nome("Jose").renda(new BigDecimal("900.00")).build())
                .mae(umaPessoa().nome("Maria").renda(new BigDecimal("300.00")).build())
                .dependentes(List.of(umaPessoa().nome("John").renda(new BigDecimal("300.00")).build()))
                .build();

        final var familia2 = umaFamilia()
                .id(2)
                .pai(umaPessoa().nome("Jose").renda(BigDecimal.ZERO).build())
                .mae(umaPessoa().nome("Maria").renda(new BigDecimal("1000.00")).build())
                .dependentes(List.of(umaPessoa().nome("John").renda(new BigDecimal("499.00")).build()))
                .build();

        final var familia3 = umaFamilia()
                .id(3)
                .pai(umaPessoa().nome("Jose").renda(new BigDecimal("999.00")).build())
                .mae(umaPessoa().nome("Maria").renda( BigDecimal.ZERO).build())
                .dependentes(List.of(umaPessoa().nome("John").renda(BigDecimal.ZERO).build()))
                .build();

        final var familia4 = umaFamilia()
                .id(4)
                .pai(umaPessoa().nome("Jose").renda(new BigDecimal("800.00")).build())
                .mae(umaPessoa().nome("Maria").renda( new BigDecimal("100.00")).build())
                .dependentes(List.of(
                                umaPessoa().nome("John").renda(new BigDecimal("100.00")).build(),
                                umaPessoa().nome("Mary").renda(new BigDecimal("99.99")).build()
                        )
                )
                .build();

        final var familia5 = umaFamilia()
                .id(5)
                .pai(umaPessoa().nome("Jose").renda(new BigDecimal("799.99")).build())
                .mae(umaPessoa().nome("Maria").renda( new BigDecimal("699.99")).build())
                .dependentes(new ArrayList<>())
                .build();

        return Stream.of(
                Arguments.of(
                        Named.of("Pai (900.0) Mae (300.0) 1 Filho (300.0)", familia1)),
                Arguments.of(
                        Named.of("Pai (0.0) Mae (1000.0) 1 Filho (499.0)", familia2)),
                Arguments.of(
                        Named.of("Pai (999.0) Mae (0.0) 1 Filho (0.0)", familia3)),
                Arguments.of(
                        Named.of("Pai (800.0) Mae (100.0) 2 Filhos (100.0 + 99.99)", familia4)),
                Arguments.of(
                        Named.of("Pai (799.99) Mae (699.99) Sem Filhos", familia5))
        );
    }

    private static Stream<Arguments> provideFamilyWithHouseholdIncomeMoreThanFifteenHundred() {
        final var familia1 = umaFamilia()
                .id(1)
                .pai(umaPessoa().nome("Jose").renda(new BigDecimal("900.00")).build())
                .mae(umaPessoa().nome("Maria").renda(new BigDecimal("601.00")).build())
                .dependentes(new ArrayList<>())
                .build();

        final var familia2 = umaFamilia()
                .id(2)
                .pai(umaPessoa().nome("Jose").renda(BigDecimal.ZERO).build())
                .mae(umaPessoa().nome("Maria").renda(new BigDecimal("1000.00")).build())
                .dependentes(List.of(umaPessoa().nome("John").renda(new BigDecimal("700.00")).build()))
                .build();

        final var familia3 = umaFamilia()
                .id(3)
                .pai(umaPessoa().nome("Jose").renda(new BigDecimal("1900.00")).build())
                .mae(umaPessoa().nome("Maria").renda( BigDecimal.ZERO).build())
                .dependentes(List.of(umaPessoa().nome("John").renda(BigDecimal.ZERO).build()))
                .build();

        final var familia4 = umaFamilia()
                .id(4)
                .pai(umaPessoa().nome("Jose").renda(new BigDecimal("800.00")).build())
                .mae(umaPessoa().nome("Maria").renda( new BigDecimal("600.00")).build())
                .dependentes(List.of(
                                umaPessoa().nome("John").renda(new BigDecimal("100.00")).build(),
                                umaPessoa().nome("Mary").renda(new BigDecimal("99.99")).build()
                        )
                )
                .build();

        return Stream.of(
                Arguments.of(
                        Named.of("Pai (900.0) Mae (601.0) Sem Filhos", familia1)),
                Arguments.of(
                        Named.of("Pai (0.0) Mae (1000.0) 1 Filho (700.0)", familia2)),
                Arguments.of(
                        Named.of("Pai (1900.0) Mae (0.0) 1 Filho (0.0)", familia3)),
                Arguments.of(
                        Named.of("Pai (800.0) Mae (600.0) 2 Filhos (100.0 + 99.99)", familia4))
        );
    }

    @DisplayName("Deve retornar 5 quando a renda total familiar for de ate 900.00 reais")
    @ParameterizedTest
    @MethodSource("provideFamilyWithHouseholdIncomeLessOrEqualToNineHundred")
    void shouldReturnFiveWhenTotalHouseholdIncomeIsLessThanNineHundred(final Familia familia) {
        this.runAssert(familia, 5);
    }

    @DisplayName("Deve retornar 3 quando a renda total familiar for de ate 1500.00 reais")
    @ParameterizedTest
    @MethodSource("provideFamilyWithHouseholdIncomeLessOrEqualToFifteenHundred")
    void shouldReturnThreeWhenTotalHouseholdIncomeIsLessThanFifteenHundred(final Familia familia) {
        this.runAssert(familia, 3);
    }

    @DisplayName("Deve retornar 0 quando a renda total familiar ultrapassar 1500.00 reais")
    @ParameterizedTest
    @MethodSource("provideFamilyWithHouseholdIncomeMoreThanFifteenHundred")
    void shouldReturnZeroWhenTotalHouseholdIncomeIsMoreThanFifteenHundred(final Familia familia) {
        this.runAssert(familia, 0);
    }

    private void runAssert(final Familia familia, final int expectedPoints) {
        final var pontos = regra.execute(familia);
        assertThat(pontos).isEqualTo(expectedPoints);
    }
}