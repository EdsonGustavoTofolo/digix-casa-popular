package br.com.digix.desafiotecnico.casapopular.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Pessoa {
    private String nome;
    private int idade;
    private BigDecimal renda;
}
