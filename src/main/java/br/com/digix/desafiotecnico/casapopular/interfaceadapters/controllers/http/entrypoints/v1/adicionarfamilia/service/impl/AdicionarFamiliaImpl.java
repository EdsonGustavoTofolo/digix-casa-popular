package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.service.impl;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.FamiliaRequest;
import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.service.AdicionarFamilia;
import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.service.converters.FamiliaConverter;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.AdicionarFamiliaInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdicionarFamiliaImpl implements AdicionarFamilia {

    private final AdicionarFamiliaInputPort adicionarFamilia;

    @Override
    public Integer execute(final FamiliaRequest request) {
        final var familiaModel = FamiliaConverter.toModel(request);
        return this.adicionarFamilia.execute(familiaModel);
    }
}
