package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.validacaopontuacao.impl;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import br.com.digix.desafiotecnico.casapopular.domain.entities.Pontuacao;
import br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.validacaopontuacao.ValidarPontuacao;
import br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.validacaopontuacao.regras.Regra;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidarPontuacaoImpl implements ValidarPontuacao {

    private final List<Regra> regras;

    @Override
    public List<Pontuacao> execute(final List<Familia> familias) {
        return familias.stream().map(this::executarRegras).toList();
    }

    private Pontuacao executarRegras(final Familia familia) {
        final var pontuacaoTotal = this.regras.stream()
                .map(regra -> regra.execute(familia))
                .reduce(0, Integer::sum);

        return Pontuacao.builder()
                .pontos(pontuacaoTotal)
                .familia(familia)
                .build();
    }
}
