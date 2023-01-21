package br.com.digix.desafiotecnico.casapopular.usecases.providers;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;

import java.util.List;

public interface FamiliaProvider {
    Integer save(final Familia familia);
    List<Familia> getAll();
}
