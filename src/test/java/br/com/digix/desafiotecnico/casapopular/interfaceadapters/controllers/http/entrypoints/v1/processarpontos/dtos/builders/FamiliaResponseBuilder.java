package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.builders;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.FamiliaResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.builders.PessoaResponseBuilder.umaPessoa;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FamiliaResponseBuilder {

    private FamiliaResponse.FamiliaResponseBuilder request;

    public static FamiliaResponse.FamiliaResponseBuilder umaFamilia() {
        final var builder = new FamiliaResponseBuilder();

        final var pai = umaPessoa().build();
        final var mae = umaPessoa().nome("Maria").idade(39).renda(BigDecimal.ZERO).build();
        final var dependente = umaPessoa().nome("Pedro").idade(10).renda(BigDecimal.ZERO).build();

        builder.request = FamiliaResponse.builder();
        builder.request
                .id(1)
                .pai(pai)
                .mae(mae)
                .dependentes(List.of(dependente));

        return builder.request;
    }

}