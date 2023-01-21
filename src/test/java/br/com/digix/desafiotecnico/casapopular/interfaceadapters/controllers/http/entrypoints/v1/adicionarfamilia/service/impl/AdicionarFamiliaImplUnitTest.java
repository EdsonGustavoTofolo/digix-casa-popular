package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.service.impl;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.PessoaRequest;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.AdicionarFamiliaInputPort;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.dtos.FamiliaModel;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.dtos.PessoaModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.builders.FamiliaRequestBuilder.umaFamilia;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdicionarFamiliaImplUnitTest {

    @Mock
    private AdicionarFamiliaInputPort inputPort;
    @InjectMocks
    private AdicionarFamiliaImpl adicionarFamilia;

    @Captor
    private ArgumentCaptor<FamiliaModel> familiaModelArgumentCaptor;

    @DisplayName("Deve adicionar familia com sucesso")
    @Test
    void shouldAddFamilySuccessfully() {
        when(this.inputPort.execute(any())).thenReturn(1);

        final var familia = umaFamilia().build();

        final var id = this.adicionarFamilia.execute(familia);

        assertThat(id).isNotNull();
        assertThat(id).isEqualTo(1);

        verify(this.inputPort).execute(this.familiaModelArgumentCaptor.capture());

        final var familiaModel = this.familiaModelArgumentCaptor.getValue();

        assertThat(familiaModel).isNotNull();
        assertThat(familiaModel.getDependentes()).isNotNull();

        for (int i = 0; i < familiaModel.getDependentes().size(); i++) {
            this.assertPessoa(familiaModel.getDependentes().get(i), familia.getDependentes().get(i));
        }

        this.assertPessoa(familiaModel.getPai().get(), familia.getPai().get());
        this.assertPessoa(familiaModel.getMae(), familia.getMae());
    }

    private void assertPessoa(final PessoaModel actualPessoa, final PessoaRequest expectedPessoa) {
        assertThat(actualPessoa).isNotNull();
        assertThat(actualPessoa.getNome()).isEqualTo(expectedPessoa.getNome());
        assertThat(actualPessoa.getIdade()).isEqualTo(expectedPessoa.getIdade());
        assertThat(actualPessoa.getRenda()).isEqualTo(expectedPessoa.getRenda());
    }
}