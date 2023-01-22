package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.processadorregras.regras;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegraPorQuantidadeDeDependentes implements Regra {

    @Override
    public int execute(final Familia familia) {
        long quantidadeDeDependentesMenoresDe18anos = familia.getQuantidadeDeDependentesMenoresDe18anos();

        log.info("Familia {} com {} dependente(s) menor(es) de 18 anos", familia.getId(), quantidadeDeDependentesMenoresDe18anos);

        if (quantidadeDeDependentesMenoresDe18anos > 0 && quantidadeDeDependentesMenoresDe18anos < 3) {
            return  2;
        } else if (quantidadeDeDependentesMenoresDe18anos >= 3) {
            return  3;
        }
        return 0;
    }
}
