package br.com.digix.desafiotecnico.casapopular.interfaceadapters.gateways.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.digix.desafiotecnico.casapopular.domain.entities.builders.FamiliaBuilder.umaFamilia;
import static org.assertj.core.api.Assertions.assertThat;

class FamiliaInMemoryDatabaseProviderUnitTest {

    private FamiliaInMemoryDatabaseProvider provider;

    @BeforeEach
    void setup() {
        this.provider = new FamiliaInMemoryDatabaseProvider();
    }

    @DisplayName("Deve adicionar a familia com sucesso")
    @Test
    void shouldSaveFamilySuccessfully() {
        final var familia = umaFamilia().build();

        final var id = this.provider.save(familia);

        assertThat(id).isNotNull();
        assertThat(id).isEqualTo(1);
    }
}