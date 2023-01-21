package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.service.impl;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.PontuacaoResponse;
import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.service.ProcessarPontos;
import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.service.converters.PontuacaoConverter;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.ProcessarPontosInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessarPontosImpl implements ProcessarPontos {

    private final ProcessarPontosInputPort processarPontos;

    @Override
    public List<PontuacaoResponse> execute() {
        final var pontuacao = this.processarPontos.execute();
        return pontuacao.stream().map(PontuacaoConverter::toResponse).toList();
    }
}
