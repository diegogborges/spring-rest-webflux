package com.apirest.webflux.controller;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
public class PlaylistController {

    @Autowired
    PlaylistService service;

    /**
     * As classes Flux e o Mono são expostas nas APIs reativas do Spring Framework:
     *
     * Flux é um stream reativo formado 0 ou N elementos.
     * Mono é um stream reativo formado por 0 ou 1 elemento.
     * 
     */

    @GetMapping(value = "/playlist")
    public Flux<Playlist> getPlaylist() {
        return service.findAll();
    }

    @GetMapping(value = "/playlist/{id}")
    public Mono<Playlist> getPlaylistId(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping(value = "/playlist")
    public Mono<Playlist> save(@RequestBody Playlist playlist) {
        return service.save(playlist);
    }

    /**
     * Método retorna a playlist conforme o flux do request
     * a cada 10 segundos retorna uma playlist
     * Na programação Reactiva assíncrona, um request não espera o outro terminar
     * @return
     */
    @GetMapping(value = "/playlist/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> getPlaylistByEvents() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Playlist> events = service.findAll();
        System.out.println("Passou aqui events");
        return Flux.zip(interval, events);
    }

}
