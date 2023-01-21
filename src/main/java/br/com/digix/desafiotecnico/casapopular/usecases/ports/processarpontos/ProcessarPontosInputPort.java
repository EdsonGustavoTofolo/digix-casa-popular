package br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos;

import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.PontuacaoModel;

import java.util.List;

public interface ProcessarPontosInputPort {
    List<PontuacaoModel> execute();
}
