package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.validacaopontuacao.regras;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;

public interface Regra {
    int execute(final Familia familia);
}
