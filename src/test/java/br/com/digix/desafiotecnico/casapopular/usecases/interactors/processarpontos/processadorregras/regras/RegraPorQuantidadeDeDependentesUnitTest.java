package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.processadorregras.regras;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.FamiliaBuilder.umaFamilia;
import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.PessoaBuilder.umaPessoa;
import static org.assertj.core.api.Assertions.assertThat;

class RegraPorQuantidadeDeDependentesUnitTest {

    private RegraPorQuantidadeDeDependentes regra;

    @BeforeEach
    void setup() {
        this.regra = new RegraPorQuantidadeDeDependentes();
    }

    private static Stream<Arguments> provideFamilyWithOneOrTwoDependentsLessThanEighteen() {
        final var joao = umaPessoa().idade(17).build();
        final var paulo = umaPessoa().idade(15).build();
        final var jose = umaPessoa().idade(20).build();

        final var familiaComUmDependente = umaFamilia()
                .id(1)
                .dependentes(List.of(joao))
                .build();

        final var familiaComDoisDependentes = umaFamilia()
                .id(2)
                .dependentes(List.of(joao, paulo))
                .build();

        final var familiaComDoisDependentesEumMaior = umaFamilia()
                .id(3)
                .dependentes(List.of(joao, paulo, jose))
                .build();

        final var familiaComUmDependenteEumMaior = umaFamilia()
                .id(4)
                .dependentes(List.of(joao, jose))
                .build();

        return Stream.of(
                Arguments.of(
                        Named.of("Uma familia com 1 dependente menor de 18 anos", familiaComUmDependente)),
                Arguments.of(
                        Named.of("Uma familia com 2 dependentes menores de 18 anos", familiaComDoisDependentes)),
                Arguments.of(
                        Named.of("Uma familia com 2 dependentes menores de 18 anos e 1 dependente maior de 18", familiaComDoisDependentesEumMaior)),
                Arguments.of(
                        Named.of("Uma familia com 1 dependente menor de 18 anos e 1 dependente maior de 18", familiaComUmDependenteEumMaior))
        );
    }

    private static Stream<Arguments> provideFamilyWithThreeOrMoreDependentsLessThanEighteen() {
        final var john = umaPessoa().idade(17).build();
        final var joao = umaPessoa().idade(10).build();
        final var paulo = umaPessoa().idade(15).build();
        final var jose = umaPessoa().idade(20).build();
        final var joaquim = umaPessoa().idade(11).build();

        final var familiaComTresDependentesMenores = umaFamilia()
                .id(1)
                .dependentes(List.of(joao, paulo, john))
                .build();

        final var familiaComTresDependentesMenoresEumMaior = umaFamilia()
                .id(2)
                .dependentes(List.of(john, joao, paulo, jose))
                .build();

        final var familiaComQuatroDependentesMenores = umaFamilia()
                .id(3)
                .dependentes(List.of(john, joao, paulo, joaquim))
                .build();

        return Stream.of(
                Arguments.of(
                        Named.of("Uma familia com 3 dependentes menores de 18 anos", familiaComTresDependentesMenores)),
                Arguments.of(
                        Named.of("Uma familia com 3 dependentes menores de 18 anos e 1 dependente maior de 18", familiaComTresDependentesMenoresEumMaior)),
                Arguments.of(
                        Named.of("Uma familia com 4 dependentes menores de 18 anos", familiaComQuatroDependentesMenores))
                );
    }

    private static Stream<Arguments> provideFamilyWithoutDependentsLessThanEighteen() {
        final var john = umaPessoa().idade(18).build();
        final var joao = umaPessoa().idade(20).build();

        final var familiaComUmDependenteMaior = umaFamilia()
                .id(1)
                .dependentes(List.of(joao))
                .build();

        final var familiaComDoisDependentesMaiores = umaFamilia()
                .id(2)
                .dependentes(List.of(john, joao))
                .build();

        return Stream.of(
                Arguments.of(
                        Named.of("Uma familia com 1 dependente maior de 18 anos", familiaComUmDependenteMaior)),
                Arguments.of(
                        Named.of("Uma familia com 2 dependentes maiores de 18 anos", familiaComDoisDependentesMaiores))
        );
    }

    @DisplayName("Deve retornar 2 quando a familia tem 1 ou 2 dependentes menores de 18 anos")
    @ParameterizedTest
    @MethodSource("provideFamilyWithOneOrTwoDependentsLessThanEighteen")
    void shouldReturnTwoWhenTheFamilyHasOneOrTwoDependentsLessThanEighteen(final Familia familia) {
        this.runAssert(familia, 2);
    }

    @DisplayName("Deve retornar 3 quando a familia tem 3 ou mais dependentes menores de 18 anos")
    @ParameterizedTest
    @MethodSource("provideFamilyWithThreeOrMoreDependentsLessThanEighteen")
    void shouldReturnThreeWhenTheFamilyHasThreeOrMoreDependentsLessThanEighteen(final Familia familia) {
        this.runAssert(familia, 3);
    }

    @DisplayName("Deve retornar 0 quando a familia nao tem dependentes menores de 18 anos")
    @ParameterizedTest
    @MethodSource("provideFamilyWithoutDependentsLessThanEighteen")
    void shouldReturnZeroWhenTheFamilyNotHaveDependentsLessThanEighteen(final Familia familia) {
        this.runAssert(familia, 0);
    }

    private void runAssert(final Familia familia, final int expectedPoints) {
        final var pontos = regra.execute(familia);
        assertThat(pontos).isEqualTo(expectedPoints);
    }
}