package br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.builders;

import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.dtos.FamiliaModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.builders.PessoaModelBuilder.umaPessoa;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FamiliaModelBuilder {

    private FamiliaModel.FamiliaModelBuilder request;

    public static FamiliaModel.FamiliaModelBuilder umaFamilia() {
        final var builder = new FamiliaModelBuilder();

        final var pai = umaPessoa().build();
        final var mae = umaPessoa().nome("Maria").idade(39).renda(BigDecimal.ZERO).build();
        final var dependente = umaPessoa().nome("Pedro").idade(10).build();

        builder.request = FamiliaModel.builder();
        builder.request
                .pai(pai)
                .mae(mae)
                .dependentes(List.of(dependente));

        return builder.request;
    }

}