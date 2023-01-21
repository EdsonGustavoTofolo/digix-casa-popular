package br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.builders;

import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.PontuacaoModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.builders.FamiliaModelBuilder.umaFamilia;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PontuacaoModelBuilder {

    private PontuacaoModel.PontuacaoModelBuilder pontuacao;

    public static PontuacaoModel.PontuacaoModelBuilder umaPontuacao() {
        final var builder = new PontuacaoModelBuilder();

        builder.pontuacao = PontuacaoModel.builder();
        builder.pontuacao
                .pontos(5)
                .familia(umaFamilia().build());

        return builder.pontuacao;
    }
}
