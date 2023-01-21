package br.com.digix.desafiotecnico.casapopular.usecases.interactors;

import br.com.digix.desafiotecnico.casapopular.usecases.providers.FamiliaProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.FamiliaBuilder.umaFamilia;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessarPontosInteractorUnitTest {

    @Mock
    private FamiliaProvider provider;
    @InjectMocks
    private ProcessarPontosInteractor processarPontos;

    @DisplayName("Deve processar a pontuação com sucesso")
    @Test
    void shouldRunPointsSuccessfully() {
        when(this.provider.getAll()).thenReturn(Collections.singletonList(umaFamilia().build()));

        final var pontuacoes = this.processarPontos.execute();

        assertThat(pontuacoes).isNotNull();
    }
}