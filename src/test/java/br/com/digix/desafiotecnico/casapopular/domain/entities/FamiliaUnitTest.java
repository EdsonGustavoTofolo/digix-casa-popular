package br.com.digix.desafiotecnico.casapopular.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.FamiliaBuilder.umaFamilia;
import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.PessoaBuilder.umaPessoa;
import static org.assertj.core.api.Assertions.assertThat;

class FamiliaUnitTest {

    @DisplayName("Deve calcular a renda total da familia com sucesso")
    @Test
    void shouldCalculateIncomeSuccessfully() {
        final var joao = umaPessoa().nome("Joao").idade(20).renda(new BigDecimal("600.00")).build();
        final var pedro = umaPessoa().nome("Pedro").idade(18).renda(new BigDecimal("800.00")).build();
        final var paulo = umaPessoa().nome("Paulo").idade(25).renda(new BigDecimal("1800.00")).build();

        final var dependentes = List.of(joao, pedro, paulo);

        final var familia = umaFamilia().dependentes(dependentes).build();

        familia.getPai().setRenda(new BigDecimal("500.00"));
        familia.getMae().setRenda(new BigDecimal("900.00"));

        final var rendaTotal = familia.getRendaTotal();

        assertThat(rendaTotal).isNotNull();
        assertThat(rendaTotal).isEqualTo(new BigDecimal("4600.00"));
    }

    @Test
    void test() {
        final var joao = umaPessoa().nome("Joao").idade(20).build();
        final var pedro = umaPessoa().nome("Pedro").idade(15).build();
        final var paulo = umaPessoa().nome("Paulo").idade(25).build();
        final var jose = umaPessoa().nome("Jose").idade(17).build();
        final var mauro = umaPessoa().nome("Mauro").idade(18).build();

        final var dependentes = List.of(joao, pedro, paulo, jose, mauro);

        final var familia = umaFamilia().dependentes(dependentes).build();

        final var quantidadeDeDependentesMenoresDe18anos = familia.getQuantidadeDeDependentesMenoresDe18anos();

        assertThat(quantidadeDeDependentesMenoresDe18anos).isEqualTo(2L);
    }
}