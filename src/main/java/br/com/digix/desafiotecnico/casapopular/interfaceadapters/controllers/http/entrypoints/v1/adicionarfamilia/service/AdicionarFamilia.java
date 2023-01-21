package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.service;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.FamiliaRequest;

public interface AdicionarFamilia {
    Integer execute(final FamiliaRequest request);
}
