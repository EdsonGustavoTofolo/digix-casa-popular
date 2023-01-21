package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos;

import br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.converters.PontuacaoConverter;
import br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.validacaopontuacao.ProcessadorRegras;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.ProcessarPontosInputPort;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.PontuacaoModel;
import br.com.digix.desafiotecnico.casapopular.usecases.providers.FamiliaProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessarPontosInteractor implements ProcessarPontosInputPort {

    private final FamiliaProvider familiaProvider;
    private final ProcessadorRegras processadorRegras;

    @Override
    public List<PontuacaoModel> execute() {
        final var familias = this.familiaProvider.getAll();

        final var pontuacoes = this.processadorRegras.execute(familias);

        return pontuacoes.stream().map(PontuacaoConverter::toModel).toList();
    }
}
