package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.builders;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.PessoaRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PessoaRequestBuilder {

    private PessoaRequest.PessoaRequestBuilder pessoa;

    public static PessoaRequest.PessoaRequestBuilder umaPessoa() {
        final var builder = new PessoaRequestBuilder();

        builder.pessoa = PessoaRequest.builder();
        return builder.pessoa
                .nome("Jose")
                .idade(40)
                .renda(new BigDecimal("1000.00"));
    }
}
