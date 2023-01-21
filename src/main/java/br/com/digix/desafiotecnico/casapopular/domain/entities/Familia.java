package br.com.digix.desafiotecnico.casapopular.domain.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Familia {
    @EqualsAndHashCode.Include
    private Integer id;
    private Pessoa pai;
    private Pessoa mae;
    private List<Pessoa> dependentes;

    public List<Pessoa> getDependentes() {
        return Collections.unmodifiableList(this.dependentes);
    }

    public Optional<Pessoa> getPai() {
        return Optional.ofNullable(this.pai);
    }

    public BigDecimal getRendaTotal() {
        final var rendaPai = Optional.ofNullable(this.pai).map(Pessoa::getRenda).orElse(BigDecimal.ZERO);
        final var rendaMae = this.mae.getRenda();
        final var rendaTotalDependentes = this.dependentes.stream().map(Pessoa::getRenda).reduce(BigDecimal.ZERO, BigDecimal::add);

        return rendaPai.add(rendaMae).add(rendaTotalDependentes);
    }

    public long getQuantidadeDeDependentesMenoresDe18anos() {
        return this.dependentes.stream().filter(pessoa -> pessoa.getIdade() < 18).count();
    }
}
