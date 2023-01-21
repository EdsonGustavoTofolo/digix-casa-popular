package br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.builders;

import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.dtos.PessoaModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PessoaModelBuilder {

    private PessoaModel.PessoaModelBuilder pessoa;

    public static PessoaModel.PessoaModelBuilder umaPessoa() {
        final var builder = new PessoaModelBuilder();

        builder.pessoa = PessoaModel.builder();
        return builder.pessoa
                .nome("Jose")
                .idade(40)
                .renda(new BigDecimal("1000.00"));
    }
}
