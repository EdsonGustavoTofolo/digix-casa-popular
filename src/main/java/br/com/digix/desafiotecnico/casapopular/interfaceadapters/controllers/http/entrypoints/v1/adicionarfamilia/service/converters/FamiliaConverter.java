package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.service.converters;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.FamiliaRequest;
import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.PessoaRequest;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.dtos.FamiliaModel;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.dtos.PessoaModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FamiliaConverter {

    public static FamiliaModel toModel(final FamiliaRequest request) {
        final var pai = toPessoaModel(request.getPai());
        final var mae = toPessoaModel(request.getMae());
        final var dependentes = request.getDependentes().stream()
                .map(FamiliaConverter::toPessoaModel).toList();

        return FamiliaModel.builder()
                .pai(pai)
                .mae(mae)
                .dependentes(dependentes)
                .build();
    }

    private static PessoaModel toPessoaModel(final PessoaRequest pessoaRequest) {
        return PessoaModel.builder()
                .nome(pessoaRequest.getNome())
                .idade(pessoaRequest.getIdade())
                .renda(new BigDecimal(pessoaRequest.getRenda().toString()))
                .build();
    }
}
