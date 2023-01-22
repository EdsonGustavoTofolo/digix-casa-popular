package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1;

import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.dtos.FamiliaRequest;
import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.adicionarfamilia.service.AdicionarFamilia;
import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.dtos.PontuacaoResponse;
import br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.processarpontos.service.ProcessarPontos;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.CasaPopularControllerPaths.ADICIONAR_FAMILIA_PATH;
import static br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.CasaPopularControllerPaths.BASE_PATH;
import static br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1.CasaPopularControllerPaths.PROCESSAR_PATH;

@RestController
@RequestMapping(BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class CasaPopularController {

    private final AdicionarFamilia adicionarFamilia;
    private final ProcessarPontos processarPontos;

    @PostMapping(ADICIONAR_FAMILIA_PATH)
    public ResponseEntity<Void> adicionarFamilia(@Valid @RequestBody FamiliaRequest request) {
        log.info("Recebida requisição para adicionar familia.");

        final var id = this.adicionarFamilia.execute(request);

        final var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        log.info("Requisição de adicionar família realizada com sucesso.");

        return ResponseEntity.created(location).build();
    }

    @PostMapping(PROCESSAR_PATH)
    public ResponseEntity<List<PontuacaoResponse>> processarPontos() {
        log.info("Recebida requisição para processar pontos.");

        final var familiasPontuadas = this.processarPontos.execute();

        log.info("Requisição de processar pontos realizada com sucesso.");

        return ResponseEntity.ok(familiasPontuadas);
    }
}
