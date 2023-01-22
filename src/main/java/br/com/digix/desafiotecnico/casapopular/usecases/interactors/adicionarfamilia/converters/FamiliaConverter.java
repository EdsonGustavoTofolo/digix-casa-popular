package br.com.digix.desafiotecnico.casapopular.usecases.interactors.adicionarfamilia.converters;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import br.com.digix.desafiotecnico.casapopular.domain.entities.Pessoa;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.dtos.FamiliaModel;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.dtos.PessoaModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FamiliaConverter {

    public static Familia toDomain(final FamiliaModel model) {
        return Familia.builder()
                .pai(toPessoaDomain(model.getPai()))
                .mae(toPessoaDomain(model.getMae()))
                .dependentes(model.getDependentes().stream().map(FamiliaConverter::toPessoaDomain).toList())
                .build();
    }

    private static Pessoa toPessoaDomain(final PessoaModel model) {
        return Pessoa.builder()
                .nome(model.getNome())
                .idade(model.getIdade())
                .renda(new BigDecimal(model.getRenda().toString()))
                .build();
    }
}
