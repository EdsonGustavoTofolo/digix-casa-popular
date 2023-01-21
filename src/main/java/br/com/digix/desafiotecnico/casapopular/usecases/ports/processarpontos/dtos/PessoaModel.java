package br.com.digix.desafiotecnico.casapopular.usecases.ports.processarpontos.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Data
@Builder
@Value
public class PessoaModel {
    String nome;
    Integer idade;
    BigDecimal renda;
}
