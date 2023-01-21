package br.com.digix.desafiotecnico.casapopular.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Familia {
    private Integer id;
    private Pessoa pai;
    private Pessoa mae;
    private List<Pessoa> dependentes;
}
