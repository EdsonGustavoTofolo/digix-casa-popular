package br.com.digix.desafiotecnico.casapopular.usecases.interactors;

import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.ProcessarPontosInputPort;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.PontuacaoModel;
import br.com.digix.desafiotecnico.casapopular.usecases.providers.FamiliaProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessarPontosInteractor implements ProcessarPontosInputPort {

    private final FamiliaProvider familiaProvider;

    @Override
    public List<PontuacaoModel> execute() {
        final var familias = this.familiaProvider.getAll();

        final List<PontuacaoModel> pontuacoes = new ArrayList<>(familias.size());

        familias.forEach(familia -> {
            int pontos = 0;

            if (familia.getRendaTotal().compareTo(new BigDecimal("900.00")) < 0) {
                pontos += 5;
            } else if (familia.getRendaTotal().compareTo(new BigDecimal("1500.00")) < 0) {
                pontos += 3;
            }
            long quantidadeDeDependentesMenoresDe18anos = familia.getQuantidadeDeDependentesMenoresDe18anos();
            if (quantidadeDeDependentesMenoresDe18anos > 0 && quantidadeDeDependentesMenoresDe18anos < 3) {
                pontos += 2;
            } else if (quantidadeDeDependentesMenoresDe18anos >= 3) {
                pontos += 3;
            }

            final var pontuacao = PontuacaoModel.builder()
                    .pontos(pontos)
                    .familia(null)
                    .build();

            pontuacoes.add(pontuacao);
        });

        return pontuacoes;
    }
}
