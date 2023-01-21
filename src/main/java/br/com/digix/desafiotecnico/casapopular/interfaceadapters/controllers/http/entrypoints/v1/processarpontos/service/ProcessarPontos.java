package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.service;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.PontuacaoResponse;

import java.util.List;

public interface ProcessarPontos {
    List<PontuacaoResponse> execute();
}
