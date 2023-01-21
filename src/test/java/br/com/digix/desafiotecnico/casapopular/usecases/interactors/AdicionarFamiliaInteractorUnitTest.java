package br.com.digix.desafiotecnico.casapopular.usecases.interactors;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import br.com.digix.desafiotecnico.casapopular.domain.entities.Pessoa;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.dtos.PessoaModel;
import br.com.digix.desafiotecnico.casapopular.usecases.providers.FamiliaProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.builders.FamiliaModelBuilder.umaFamilia;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdicionarFamiliaInteractorUnitTest {

    @Mock
    private FamiliaProvider familiaProvider;
    @InjectMocks
    private AdicionarFamiliaInteractor interactor;

    @Captor
    private ArgumentCaptor<Familia> familiaArgumentCaptor;

    @DisplayName("Deve adicionar uma familia com sucesso")
    @Test
    void shouldAddFamilySuccessfully() {
        when(this.familiaProvider.save(any())).thenReturn(1);

        final var familiaModel = umaFamilia().build();

        final var id = this.interactor.execute(familiaModel);

        assertThat(id).isNotNull();
        assertThat(id).isEqualTo(1);

        verify(this.familiaProvider).save(this.familiaArgumentCaptor.capture());

        final var familia = this.familiaArgumentCaptor.getValue();

        assertThat(familia).isNotNull();
        assertThat(familia.getDependentes()).isNotNull();

        for (int i = 0; i < familia.getDependentes().size(); i++) {
            this.assertPessoa(familia.getDependentes().get(i), familiaModel.getDependentes().get(i));
        }

        this.assertPessoa(familia.getPai(), familiaModel.getPai().get());
        this.assertPessoa(familia.getMae(), familiaModel.getMae());
    }

    private void assertPessoa(final Pessoa actualPessoa, final PessoaModel expectedPessoa) {
        assertThat(actualPessoa).isNotNull();
        assertThat(actualPessoa.getNome()).isEqualTo(expectedPessoa.getNome());
        assertThat(actualPessoa.getIdade()).isEqualTo(expectedPessoa.getIdade());
        assertThat(actualPessoa.getRenda()).isEqualTo(expectedPessoa.getRenda());
    }
}