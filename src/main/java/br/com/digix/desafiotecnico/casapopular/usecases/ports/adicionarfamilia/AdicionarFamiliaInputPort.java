package br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia;

import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.dtos.FamiliaModel;

public interface AdicionarFamiliaInputPort {
    Integer execute(final FamiliaModel familiaModel);
}
