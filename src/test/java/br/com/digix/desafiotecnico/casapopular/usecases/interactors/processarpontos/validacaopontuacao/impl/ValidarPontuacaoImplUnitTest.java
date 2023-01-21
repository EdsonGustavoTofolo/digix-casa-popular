package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.validacaopontuacao.impl;

import br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.validacaopontuacao.regras.Regra;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.FamiliaBuilder.umaFamilia;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidarPontuacaoImplUnitTest {

    @Mock
    private List<Regra> regras;
    @InjectMocks
    private ValidarPontuacaoImpl validarPontuacao;

    @Test
    void execute() {
        final var familia1 = umaFamilia().id(1).build();
        final var familia2 = umaFamilia().id(2).build();

        this.validarPontuacao.execute(List.of(familia1, familia2));

    }
}