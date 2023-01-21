package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.validacaopontuacao.regras;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RegraPorRenda implements Regra {

    @Override
    public int execute(final Familia familia) {
        if (familia.getRendaTotal().compareTo(new BigDecimal("900.00")) < 0) {
            return 5;
        } else if (familia.getRendaTotal().compareTo(new BigDecimal("1500.00")) < 0) {
            return 3;
        }
        return 0;
    }
}
