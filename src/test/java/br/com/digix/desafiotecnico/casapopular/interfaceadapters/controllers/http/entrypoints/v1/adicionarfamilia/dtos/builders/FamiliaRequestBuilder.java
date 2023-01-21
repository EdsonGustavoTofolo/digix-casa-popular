package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.builders;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.FamiliaRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.builders.PessoaRequestBuilder.umaPessoa;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FamiliaRequestBuilder {

    private FamiliaRequest.FamiliaRequestBuilder request;

    public static FamiliaRequest.FamiliaRequestBuilder umaFamilia() {
        final var builder = new FamiliaRequestBuilder();

        final var pai = umaPessoa().build();
        final var mae = umaPessoa().nome("Maria").idade(39).renda(BigDecimal.ZERO).build();
        final var dependente = umaPessoa().nome("Pedro").idade(10).build();

        builder.request = FamiliaRequest.builder();
        builder.request
                .pai(pai)
                .mae(mae)
                .dependentes(List.of(dependente));

        return builder.request;
    }

}