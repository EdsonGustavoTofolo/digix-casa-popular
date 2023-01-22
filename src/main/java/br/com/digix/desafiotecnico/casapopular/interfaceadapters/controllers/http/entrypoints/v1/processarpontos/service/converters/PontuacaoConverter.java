package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.service.converters;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.FamiliaResponse;
import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.PessoaResponse;
import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.PontuacaoResponse;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.FamiliaModel;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.PessoaModel;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.PontuacaoModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PontuacaoConverter {

    public static PontuacaoResponse toResponse(final PontuacaoModel pontuacaoModel) {
        return PontuacaoResponse.builder()
                .pontos(pontuacaoModel.getPontos())
                .familia(toFamiliaResponse(pontuacaoModel.getFamilia()))
                .build();
    }

    private static FamiliaResponse toFamiliaResponse(final FamiliaModel familiaModel) {
        final var pai = toPessoaResponse(familiaModel.getPai());
        final var mae = toPessoaResponse(familiaModel.getMae());
        final var dependentes = familiaModel.getDependentes().stream().map(PontuacaoConverter::toPessoaResponse).toList();

        return FamiliaResponse.builder()
                .id(familiaModel.getId())
                .pai(pai)
                .mae(mae)
                .dependentes(dependentes)
                .build();
    }

    private static PessoaResponse toPessoaResponse(final PessoaModel pessoa) {
        return PessoaResponse.builder()
                .nome(pessoa.getNome())
                .idade(pessoa.getIdade())
                .renda(new BigDecimal(pessoa.getRenda().toString()))
                .build();
    }
}
