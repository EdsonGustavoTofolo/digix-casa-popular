package br.com.digix.desafiotecnico.casapopular.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Data
@Builder
public class Familia {
    private Integer id;
    private Pessoa pai;
    private Pessoa mae;
    private List<Pessoa> dependentes;

    public BigDecimal getRendaTotal() {
        final var rendaPai = Optional.ofNullable(this.pai).map(Pessoa::getRenda).orElse(BigDecimal.ZERO);
        final var rendaMae = this.mae.getRenda();
        final var rendaTotalDependentes = this.dependentes.stream().map(Pessoa::getRenda).reduce(BigDecimal.ZERO, BigDecimal::add);

        return rendaPai.add(rendaMae).add(rendaTotalDependentes);
    }
}
