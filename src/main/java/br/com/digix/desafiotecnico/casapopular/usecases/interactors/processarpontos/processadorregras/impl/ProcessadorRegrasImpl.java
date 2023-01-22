package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.processadorregras.impl;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import br.com.digix.desafiotecnico.casapopular.domain.entities.Pontuacao;
import br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.processadorregras.ProcessadorRegras;
import br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.processadorregras.regras.Regra;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessadorRegrasImpl implements ProcessadorRegras {

    private final List<Regra> regras;

    @Override
    public List<Pontuacao> execute(final List<Familia> familias) {
        return familias.stream().map(this::executarRegras).toList();
    }

    private Pontuacao executarRegras(final Familia familia) {
        log.info("Executando regras para familia {}.", familia.getId());

        final var pontuacaoTotal = this.regras.stream()
                .map(regra -> regra.execute(familia))
                .reduce(0, Integer::sum);

        return Pontuacao.builder()
                .pontos(pontuacaoTotal)
                .familia(familia)
                .build();
    }
}
