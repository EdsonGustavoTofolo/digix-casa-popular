package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.validacaopontuacao;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import br.com.digix.desafiotecnico.casapopular.domain.entities.Pontuacao;

import java.util.List;

public interface ValidarPontuacao {
    List<Pontuacao> execute(final List<Familia> familias);
}
