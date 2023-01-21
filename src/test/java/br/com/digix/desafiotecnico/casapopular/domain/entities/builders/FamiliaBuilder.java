package br.com.digix.desafiotecnico.casapopular.domain.entities.builders;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.PessoaBuilder.umaPessoa;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FamiliaBuilder {

    private Familia.FamiliaBuilder request;

    public static Familia.FamiliaBuilder umaFamilia() {
        final var builder = new FamiliaBuilder();

        final var pai = umaPessoa().build();
        final var mae = umaPessoa().nome("Maria").idade(39).renda(BigDecimal.ZERO).build();
        final var dependente = umaPessoa().nome("Pedro").idade(10).build();

        builder.request = Familia.builder();
        builder.request
                .pai(pai)
                .mae(mae)
                .dependentes(List.of(dependente));

        return builder.request;
    }

}