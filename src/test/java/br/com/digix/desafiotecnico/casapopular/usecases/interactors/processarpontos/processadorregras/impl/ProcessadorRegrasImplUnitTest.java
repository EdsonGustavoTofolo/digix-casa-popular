package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.processadorregras.impl;

import br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.processadorregras.regras.Regra;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.FamiliaBuilder.umaFamilia;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessadorRegrasImplUnitTest {

    @Mock
    private Regra regraPorRenda;
    @Mock
    private Regra regraPorQuantidadeDeDependentes;

    @Test
    void execute() {
        final var familia1 = umaFamilia().id(1).build();
        final var familia2 = umaFamilia().id(2).build();

        when(this.regraPorRenda.execute(familia1)).thenReturn(5);
        when(this.regraPorQuantidadeDeDependentes.execute(familia1)).thenReturn(2);

        when(this.regraPorRenda.execute(familia2)).thenReturn(3);
        when(this.regraPorQuantidadeDeDependentes.execute(familia2)).thenReturn(3);

        final var processadorRegras = new ProcessadorRegrasImpl(Arrays.asList(this.regraPorRenda, this.regraPorQuantidadeDeDependentes));

        final var pontuacoes = processadorRegras.execute(List.of(familia1, familia2));

        assertThat(pontuacoes).isNotNull();
        assertThat(pontuacoes.get(0).getFamilia()).isSameAs(familia1);
        assertThat(pontuacoes.get(0).getPontos()).isEqualTo(7);

        assertThat(pontuacoes.get(1).getFamilia()).isSameAs(familia2);
        assertThat(pontuacoes.get(1).getPontos()).isEqualTo(6);
    }
}