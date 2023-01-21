package br.com.digix.desafiotecnico.casapopular.domain.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pontuacao {
    private Integer pontos;
    private Familia familia;
}
