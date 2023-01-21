package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.validacaopontuacao.regras;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import org.springframework.stereotype.Service;

@Service
public class RegraPorQuantidadeDeDependentes implements Regra {

    @Override
    public int execute(final Familia familia) {
        long quantidadeDeDependentesMenoresDe18anos = familia.getQuantidadeDeDependentesMenoresDe18anos();
        if (quantidadeDeDependentesMenoresDe18anos > 0 && quantidadeDeDependentesMenoresDe18anos < 3) {
            return  2;
        } else if (quantidadeDeDependentesMenoresDe18anos >= 3) {
            return  3;
        }
        return 0;
    }
}
