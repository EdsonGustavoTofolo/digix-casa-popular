package br.com.digix.desafiotecnico.casapopular.usecases.interactors.processarpontos.converters;

import br.com.digix.desafiotecnico.casapopular.domain.entities.Familia;
import br.com.digix.desafiotecnico.casapopular.domain.entities.Pessoa;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.FamiliaModel;
import br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos.PessoaModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FamiliaConverter {

    public static FamiliaModel toModel(final Familia familia) {
        return FamiliaModel.builder()
                .id(familia.getId())
                .pai(familia.getPai().map(FamiliaConverter::toPessoaDomain).orElse(null))
                .mae(toPessoaDomain(familia.getMae()))
                .dependentes(familia.getDependentes().stream().map(FamiliaConverter::toPessoaDomain).toList())
                .build();
    }

    private static PessoaModel toPessoaDomain(final Pessoa pessoa) {
        return PessoaModel.builder()
                .nome(pessoa.getNome())
                .idade(pessoa.getIdade())
                .renda(new BigDecimal(pessoa.getRenda().toString()))
                .build();
    }
}
