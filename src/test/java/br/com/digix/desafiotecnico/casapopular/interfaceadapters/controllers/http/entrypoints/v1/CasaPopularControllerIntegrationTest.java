package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.FamiliaRequest;
import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.service.AdicionarFamilia;
import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.service.ProcessarPontos;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.CasaPopularControllerPaths.ADICIONAR_FAMILIA_PATH;
import static br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.CasaPopularControllerPaths.PROCESSAR_PATH;
import static br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.CasaPopularControllerPaths.getFullPath;
import static br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.builders.FamiliaRequestBuilder.umaFamilia;
import static br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.builders.PontuacaoResponseBuilder.umaPontuacao;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {CasaPopularController.class, AdicionarFamilia.class})
@WebMvcTest
class CasaPopularControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdicionarFamilia adicionarFamilia;
    @MockBean
    private ProcessarPontos processarPontos;

    @Captor
    private ArgumentCaptor<FamiliaRequest> familiaRequestArgumentCaptor;

    private final ObjectMapper mapper = new ObjectMapper();

    @DisplayName("Deve processar a pontuação das familias")
    @Test
    void shouldRunFamilyPontuation() throws Exception {
        when(this.processarPontos.execute()).thenReturn(List.of(umaPontuacao().build()));

        this.mockMvc.perform(
                        post(getFullPath(PROCESSAR_PATH))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].pontos").value(5))
                .andExpect(jsonPath("$[0].familia").exists())
                .andExpect(jsonPath("$[0].familia.id").value(1))
                .andExpect(jsonPath("$[0].familia.pai.nome").value("Jose"))
                .andExpect(jsonPath("$[0].familia.pai.idade").value(40))
                .andExpect(jsonPath("$[0].familia.pai.renda").value(1000.00D))
                .andExpect(jsonPath("$[0].familia.mae.nome").value("Maria"))
                .andExpect(jsonPath("$[0].familia.mae.idade").value(39))
                .andExpect(jsonPath("$[0].familia.mae.renda").value(0))
                .andExpect(jsonPath("$[0].familia.dependentes[0].nome").value("Pedro"))
                .andExpect(jsonPath("$[0].familia.dependentes[0].idade").value(10))
                .andExpect(jsonPath("$[0].familia.dependentes[0].renda").value(0));

        verify(this.processarPontos).execute();
    }

    @DisplayName("Deve adicionar uma família com sucesso")
    @Test
    void shouldAddFamilyWithSuccess() throws Exception {
        final var expectedFamilia = umaFamilia().build();

        this.mockMvc.perform(
                post(getFullPath(ADICIONAR_FAMILIA_PATH))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(expectedFamilia)))
                .andExpect(status().isCreated());

        verify(this.adicionarFamilia).execute(this.familiaRequestArgumentCaptor.capture());

        final var actualFamiliaRequest = this.familiaRequestArgumentCaptor.getValue();

        assertThat(actualFamiliaRequest).isNotNull();
        assertThat(actualFamiliaRequest).isEqualTo(expectedFamilia);
    }
}