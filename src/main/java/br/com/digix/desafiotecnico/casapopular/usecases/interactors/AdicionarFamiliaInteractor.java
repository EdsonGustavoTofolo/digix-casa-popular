package br.com.digix.desafiotecnico.casapopular.usecases.interactors;

import br.com.digix.desafiotecnico.casapopular.usecases.interactors.converters.FamiliaConverter;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.AdicionarFamiliaInputPort;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.dtos.FamiliaModel;
import br.com.digix.desafiotecnico.casapopular.usecases.providers.FamiliaProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdicionarFamiliaInteractor implements AdicionarFamiliaInputPort {

    private final FamiliaProvider familiaProvider;

    @Override
    public Integer execute(final FamiliaModel familiaModel) {
        final var familia = FamiliaConverter.toDomain(familiaModel);
        return this.familiaProvider.save(familia);
    }
}
