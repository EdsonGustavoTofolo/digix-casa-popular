package br.com.digix.desafiotecnico.casapopular.domain.entities.builders;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Pontuacao;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.FamiliaBuilder.umaFamilia;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PontuacaoBuilder {

    private Pontuacao.PontuacaoBuilder pontuacao;

    public static Pontuacao.PontuacaoBuilder umaPontuacao() {
        final var builder = new PontuacaoBuilder();

        builder.pontuacao = Pontuacao.builder();
        builder.pontuacao
                .pontos(3)
                .familia(umaFamilia().build());

        return builder.pontuacao;
    }
}
