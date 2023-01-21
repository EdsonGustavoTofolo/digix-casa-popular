package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.builders;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.PessoaResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PessoaResponseBuilder {

    private PessoaResponse.PessoaResponseBuilder pessoa;

    public static PessoaResponse.PessoaResponseBuilder umaPessoa() {
        final var builder = new PessoaResponseBuilder();

        builder.pessoa = PessoaResponse.builder();
        return builder.pessoa
                .nome("Jose")
                .idade(40)
                .renda(new BigDecimal("1000.00"));
    }
}
