package com.apirest.webflux;

import com.apirest.webflux.document.PlayList;
import com.apirest.webflux.repository.PlayListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class DummyData implements CommandLineRunner {

    private final PlayListRepository playListRepository;

    DummyData(PlayListRepository playListRepository) {
        this.playListRepository = playListRepository;
    }

    /**
     * Método para salvar algumas PlayList ao iniciar a aplicação
     * Primeiramente é deletado toda a informação e através de um fluxo de stream
     * ele vai inserir algumas playlist, nome e ID random
     *
      */
    @Override
    public void run(String... args) throws Exception {
        playListRepository.deleteAll()
                .thenMany(
                        Flux.just("API REST Spring Boot", "Deploy de uma aplicação java no IBM Cloud", "Java 11")
                        .map(nome -> new PlayList(UUID.randomUUID().toString(), nome))
                        .flatMap(playListRepository::save)
                ).subscribe(System.out::println);
    }

}
