package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Pessoa;
import br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.validacaopontuacao.ValidarPontuacao;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.PessoaModel;
import br.com.digix.desafiotecnico.casapopular.usecases.providers.FamiliaProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.FamiliaBuilder.umaFamilia;
import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.PontuacaoBuilder.umaPontuacao;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessarPontosInteractorUnitTest {

    @Mock
    private ValidarPontuacao validarPontuacao;
    @Mock
    private FamiliaProvider provider;
    @InjectMocks
    private ProcessarPontosInteractor processarPontos;

    @DisplayName("Deve processar a pontuação com sucesso")
    @Test
    void shouldRunPointsSuccessfully() {
        final var pontuacao = umaPontuacao().build();

        when(this.provider.getAll()).thenReturn(Collections.singletonList(umaFamilia().build()));
        when(this.validarPontuacao.execute(anyList())).thenReturn(Collections.singletonList(pontuacao));

        final var pontuacoes = this.processarPontos.execute();

        assertThat(pontuacoes).isNotNull();
        assertThat(pontuacoes).asList().hasSize(1);

        pontuacoes.forEach(pontuacaoModel -> {
            assertThat(pontuacaoModel.getPontos()).isEqualTo(pontuacao.getPontos());
            assertThat(pontuacaoModel.getFamilia()).isNotNull();

            this.assertPessoa(pontuacaoModel.getFamilia().getPai().get(), pontuacao.getFamilia().getPai().get());
            this.assertPessoa(pontuacaoModel.getFamilia().getMae(), pontuacao.getFamilia().getMae());

            for (int i = 0; i < pontuacaoModel.getFamilia().getDependentes().size(); i++) {
                this.assertPessoa(pontuacaoModel.getFamilia().getDependentes().get(i), pontuacao.getFamilia().getDependentes().get(i));
            }
        });
    }

    private void assertPessoa(final PessoaModel pessoaModel, final Pessoa pessoa) {
        assertThat(pessoaModel).isNotNull();
        assertThat(pessoaModel.getNome()).isEqualTo(pessoa.getNome());
        assertThat(pessoaModel.getIdade()).isEqualTo(pessoa.getIdade());
        assertThat(pessoaModel.getRenda()).isEqualTo(pessoa.getRenda());
    }
}