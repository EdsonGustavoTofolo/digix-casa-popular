package br.com.digix.desafiotecnico.casapopular.domain.entities.builders;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Pessoa;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PessoaBuilder {

    private Pessoa.PessoaBuilder pessoa;

    public static Pessoa.PessoaBuilder umaPessoa() {
        final var builder = new PessoaBuilder();

        builder.pessoa = Pessoa.builder();
        return builder.pessoa
                .nome("Jose")
                .idade(40)
                .renda(new BigDecimal("1000.00"));
    }
}
