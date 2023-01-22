package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Pontuacao;
import br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.converters.PontuacaoConverter;
import br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.processadorregras.ProcessadorRegras;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.ProcessarPontosInputPort;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.PontuacaoModel;
import br.com.digix.desafiotecnico.casapopular.usecases.providers.FamiliaProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessarPontosInteractor implements ProcessarPontosInputPort {

    private final FamiliaProvider familiaProvider;
    private final ProcessadorRegras processadorRegras;

    @Override
    public List<PontuacaoModel> execute() {
        log.info("Iniciando processo de pontuação por família.");

        log.info("Buscando todas as familias.");

        final var familias = this.familiaProvider.getAll();

        log.info("Iniciando processamento das regras.");

        final var pontuacoes = this.processadorRegras.execute(familias);

        final var pontuacoesPorFamilia = pontuacoes.stream()
                .sorted(this::ordenarPorMaiorPontuacao)
                .map(PontuacaoConverter::toModel)
                .toList();

        log.info("Processo de pontuação por familia finalizado com sucesso.");

        return pontuacoesPorFamilia;
    }

    private int ordenarPorMaiorPontuacao(final Pontuacao pontuacao1, final Pontuacao pontuacao2) {
        return Integer.compare(pontuacao2.getPontos(), pontuacao1.getPontos());
    }
}
