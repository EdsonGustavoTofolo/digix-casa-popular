package br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.builders;

import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.FamiliaModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.builders.PessoaModelBuilder.umaPessoa;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FamiliaModelBuilder {

    private FamiliaModel.FamiliaModelBuilder request;

    public static FamiliaModel.FamiliaModelBuilder umaFamilia() {
        final var builder = new FamiliaModelBuilder();

        final var pai = umaPessoa().build();
        final var mae = umaPessoa().nome("Maria").idade(39).renda(BigDecimal.ZERO).build();
        final var dependente = umaPessoa().nome("Pedro").idade(10).renda(BigDecimal.ZERO).build();

        builder.request = FamiliaModel.builder();
        builder.request
                .id(1)
                .pai(pai)
                .mae(mae)
                .dependentes(List.of(dependente));

        return builder.request;
    }

}