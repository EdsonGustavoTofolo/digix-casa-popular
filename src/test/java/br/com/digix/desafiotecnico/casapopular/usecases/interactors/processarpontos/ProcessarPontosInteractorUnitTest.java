package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Pessoa;
import br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.processadorregras.ProcessadorRegras;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.PessoaModel;
import br.com.digix.desafiotecnico.casapopular.usecases.providers.FamiliaProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.FamiliaBuilder.umaFamilia;
import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.PontuacaoBuilder.umaPontuacao;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessarPontosInteractorUnitTest {

    @Mock
    private ProcessadorRegras processadorRegras;
    @Mock
    private FamiliaProvider provider;
    @InjectMocks
    private ProcessarPontosInteractor processarPontos;

    @DisplayName("Deve processar a pontuação com sucesso")
    @Test
    void shouldRunPointsSuccessfully() {
        final var pontuacao3 = umaPontuacao().familia(umaFamilia().id(1).build()).pontos(3).build();
        final var pontuacao5 = umaPontuacao().familia(umaFamilia().id(2).build()).pontos(5).build();
        final var pontuacao8 = umaPontuacao().familia(umaFamilia().id(3).build()).pontos(8).build();

        final var expectedPontuacoes = List.of(pontuacao3, pontuacao5, pontuacao8);

        when(this.provider.getAll()).thenReturn(Collections.singletonList(umaFamilia().build()));
        when(this.processadorRegras.execute(anyList())).thenReturn(expectedPontuacoes);

        final var pontuacoes = this.processarPontos.execute();

        assertThat(pontuacoes).isNotNull();
        assertThat(pontuacoes).asList().hasSize(3);

        assertThat(pontuacoes.get(0).getPontos()).isEqualTo(pontuacao8.getPontos());
        assertThat(pontuacoes.get(1).getPontos()).isEqualTo(pontuacao5.getPontos());
        assertThat(pontuacoes.get(2).getPontos()).isEqualTo(pontuacao3.getPontos());

        pontuacoes.forEach(actualPontuacao -> {
            final var expectedPontuacao = expectedPontuacoes.stream()
                    .filter(pontuacao -> pontuacao.getFamilia().getId().equals(actualPontuacao.getFamilia().getId()))
                    .findFirst().orElse(null);

            assertThat(expectedPontuacao).isNotNull();
            assertThat(actualPontuacao.getPontos()).isEqualTo(expectedPontuacao.getPontos());
            assertThat(actualPontuacao.getFamilia()).isNotNull();

            this.assertPessoa(actualPontuacao.getFamilia().getPai(), expectedPontuacao.getFamilia().getPai());
            this.assertPessoa(actualPontuacao.getFamilia().getMae(), expectedPontuacao.getFamilia().getMae());

            for (int i = 0; i < actualPontuacao.getFamilia().getDependentes().size(); i++) {
                this.assertPessoa(actualPontuacao.getFamilia().getDependentes().get(i), expectedPontuacao.getFamilia().getDependentes().get(i));
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