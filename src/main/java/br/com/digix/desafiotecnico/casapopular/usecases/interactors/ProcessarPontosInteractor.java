package br.com.digix.desafiotecnico.casapopular.usecases.interactors;

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

    @Override
    public List<PontuacaoModel> execute() {
        final var familias = this.familiaProvider.getAll();

        return null;
    }
}
