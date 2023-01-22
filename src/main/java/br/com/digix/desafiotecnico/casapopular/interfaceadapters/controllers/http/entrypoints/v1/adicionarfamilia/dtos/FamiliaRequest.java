package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@Value
public class FamiliaRequest {
    @Valid
    @NotNull
    PessoaRequest pai;
    @Valid
    @NotNull
    PessoaRequest mae;
    @NotNull
    List<@Valid PessoaRequest> dependentes;
}
