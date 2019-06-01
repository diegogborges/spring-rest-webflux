package com.apirest.webflux;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

//@Component // identifica que é um Bean gerenciado pelo Spring Framework
public class PlaylistHandler {

    @Autowired
    PlaylistService service;

    /**
     * ServerResponse faz parte da API Reactiva do Spring Framework
     * Representa uma solicitação HTTP do lado do servidor, conforme tratada por um HandlerFunction.
     * O acesso aos cabeçalhos e ao corpo é oferecido por ServerRequest.Headers e body(BodyExtractor)respectivamente.
     * https://docs.spring.io/spring/docs/5.0.0.M3/javadoc-api/org/springframework/web/reactive/function/ServerRequest.html
     */

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Playlist.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findById(id), Playlist.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<Playlist> playlist = request.bodyToMono(Playlist.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(playlist.flatMap(service::save), Playlist.class));
    }
}
