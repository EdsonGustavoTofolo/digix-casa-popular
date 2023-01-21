package br.com.digix.desafiotecnico.casapopular.interfaceadapters.gateways.database;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import br.com.digix.desafiotecnico.casapopular.usecases.providers.FamiliaProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FamiliaInMemoryDatabaseProvider implements FamiliaProvider {

    private final List<Familia> familias = new ArrayList<>();

    @Override
    public Integer save(final Familia familia) {
        familia.setId(familias.size() + 1);

        familias.add(familia);

        return familia.getId();
    }

    @Override
    public List<Familia> getAll() {
        return this.familias;
    }
}
