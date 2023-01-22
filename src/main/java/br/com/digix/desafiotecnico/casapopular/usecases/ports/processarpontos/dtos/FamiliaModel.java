package br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@Builder
@Value
public class FamiliaModel {
    Integer id;
    PessoaModel pai;
    PessoaModel mae;
    List<PessoaModel> dependentes;
}
