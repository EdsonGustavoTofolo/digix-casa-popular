package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@Builder
@Value
public class FamiliaResponse {
    Integer id;
    PessoaResponse pai;
    PessoaResponse mae;
    List<PessoaResponse> dependentes;
}
