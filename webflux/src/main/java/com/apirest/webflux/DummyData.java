package com.apirest.webflux;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.repository.PlaylistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class DummyData implements CommandLineRunner {

    private final PlaylistRepository playlistRepository;

    DummyData(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    /**
     * Método para salvar algumas Playlist ao iniciar a aplicação
     * Primeiramente é deletado toda a informação e através de um fluxo de stream
     * ele vai inserir algumas playlist, nome e ID random
      */
    @Override
    public void run(String... args) throws Exception {
        playlistRepository.deleteAll()
                .thenMany(
                        Flux.just("API REST Spring Boot", "Deploy de uma aplicação java no IBM Cloud", "Java 11")
                        .map(nome -> new Playlist(UUID.randomUUID().toString(), nome))
                        .flatMap(playlistRepository::save)
                ).subscribe(System.out::println);
    }

}
