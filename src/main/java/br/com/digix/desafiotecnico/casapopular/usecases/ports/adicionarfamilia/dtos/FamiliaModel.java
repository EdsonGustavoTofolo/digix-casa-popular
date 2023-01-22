package br.com.digix.desafiotecnico.casapopular.usecases.ports.adicionarfamilia.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@Builder
@Value
public class FamiliaModel {
    PessoaModel pai;
    PessoaModel mae;
    List<PessoaModel> dependentes;
}
