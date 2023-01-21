package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.builders;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.PontuacaoResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.builders.FamiliaResponseBuilder.umaFamilia;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PontuacaoResponseBuilder {

    private PontuacaoResponse.PontuacaoResponseBuilder pontuacao;

    public static PontuacaoResponse.PontuacaoResponseBuilder umaPontuacao() {
        final var builder = new PontuacaoResponseBuilder();

        builder.pontuacao = PontuacaoResponse.builder();
        builder.pontuacao
                .pontos(5)
                .familia(umaFamilia().build());

        return builder.pontuacao;
    }
}
