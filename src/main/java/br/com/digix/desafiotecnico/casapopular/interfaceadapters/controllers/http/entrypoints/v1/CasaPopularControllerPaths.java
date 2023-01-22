package br.com.digix.desafiotecnico.casapopular.interfaceadapters.controllers.http.entrypoints.v1;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CasaPopularControllerPaths {

    public static final String BASE_PATH = "/api/v1/casa-popular";
    public static final String ADICIONAR_FAMILIA_PATH = "/familias";
    public static final String PROCESSAR_PATH = "/familias/pontos";

    public static String getFullPath(final String path) {
        return BASE_PATH + path;
    }
}
