package br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class PontuacaoModel {
    Integer pontos;
    FamiliaModel familia;
}
