package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.service.impl;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.PessoaResponse;
import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.PontuacaoResponse;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.ProcessarPontosInputPort;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.PessoaModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.builders.PontuacaoModelBuilder.umaPontuacao;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessarPontosImplUnitTest {

    @Mock
    private ProcessarPontosInputPort inputPort;
    @InjectMocks
    private ProcessarPontosImpl processarPontos;

    @DisplayName("Deve processar a pontuação da familia com sucesso")
    @Test
    void shouldRunPointsSuccessfully() {
        final var pontuacao = umaPontuacao().build();

        when(this.inputPort.execute()).thenReturn(Collections.singletonList(pontuacao));

        final var familiasPontuadas = this.processarPontos.execute();

        assertThat(familiasPontuadas).isNotNull();

        for (final PontuacaoResponse actualPontuacao : familiasPontuadas) {
            assertThat(actualPontuacao).isNotNull();

            assertThat(actualPontuacao.getPontos()).isEqualTo(pontuacao.getPontos());

            assertThat(actualPontuacao.getFamilia()).isNotNull();
            assertThat(actualPontuacao.getFamilia().getId()).isEqualTo(pontuacao.getFamilia().getId());

            this.assertPessoa(actualPontuacao.getFamilia().getPai(), pontuacao.getFamilia().getPai());
            this.assertPessoa(actualPontuacao.getFamilia().getMae(), pontuacao.getFamilia().getMae());

            assertThat(actualPontuacao.getFamilia().getDependentes()).isNotNull();

            for (int j = 0; j < actualPontuacao.getFamilia().getDependentes().size(); j++) {
                this.assertPessoa(actualPontuacao.getFamilia().getDependentes().get(0), pontuacao.getFamilia().getDependentes().get(0));
            }
        }
    }

    private void assertPessoa(final PessoaResponse actualPessoa, final PessoaModel expectedPessoa) {
        assertThat(actualPessoa).isNotNull();
        assertThat(actualPessoa.getNome()).isEqualTo(expectedPessoa.getNome());
        assertThat(actualPessoa.getIdade()).isEqualTo(expectedPessoa.getIdade());
        assertThat(actualPessoa.getRenda()).isEqualTo(expectedPessoa.getRenda());
    }
}