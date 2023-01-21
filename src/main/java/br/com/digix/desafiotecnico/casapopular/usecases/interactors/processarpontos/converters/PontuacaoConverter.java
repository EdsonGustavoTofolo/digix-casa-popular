package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.converters;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Pontuacao;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.PontuacaoModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PontuacaoConverter {

    public static PontuacaoModel toModel(final Pontuacao pontuacao) {
        return PontuacaoModel.builder()
                .pontos(pontuacao.getPontos())
                .familia(FamiliaConverter.toModel(pontuacao.getFamilia()))
                .build();
    }
}
