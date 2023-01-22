package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.processadorregras.regras;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class RegraPorRenda implements Regra {

    private static final Double NOVECENTOS = 900.0;
    private static final Double MIL_QUINHENTOS = 1500.0;

    @Override
    public int execute(final Familia familia) {
        final var rendaTotal = familia.getRendaTotal();

        log.info("Familia {} com Renda Total de {}", familia.getId(), rendaTotal);

        if (rendaTotal.compareTo(new BigDecimal(NOVECENTOS)) <= 0) {
            return 5;
        } else if (rendaTotal.compareTo(new BigDecimal(MIL_QUINHENTOS)) <= 0) {
            return 3;
        }
        return 0;
    }
}
