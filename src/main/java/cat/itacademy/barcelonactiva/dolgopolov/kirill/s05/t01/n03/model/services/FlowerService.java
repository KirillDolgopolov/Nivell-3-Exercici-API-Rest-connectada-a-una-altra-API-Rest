package cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t01.n03.model.services;

import cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t01.n03.model.dto.FlowerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@AllArgsConstructor
public class FlowerService {

    private final WebClient webClient;

    public Mono<FlowerDTO> addFlower(FlowerDTO flowerDTO) {
        Mono<FlowerDTO> monoRequest = webClient.post().uri("http://localhost:9001/flor/add")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(flowerDTO), FlowerDTO.class).retrieve().bodyToMono(FlowerDTO.class)
                .timeout(Duration.ofMillis(10_000));
        return monoRequest;

    }

    public Flux<FlowerDTO> getAllFlower() {
        Flux<FlowerDTO> fluxRequest = webClient.get().uri("http://localhost:9001/flor/getAll")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve().bodyToFlux(FlowerDTO.class)
                .timeout(Duration.ofMillis(10_000));
        return fluxRequest;
    }


    public Mono<FlowerDTO> getFlowerByID(Integer id) {
        return webClient.get()
                .uri("http://localhost:9001/flor/getOne/{id}", id)
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .timeout(Duration.ofMillis(10_000));
    }


    public Mono<Void> deleteFlowerByID(Integer id) {
        return webClient.delete()
                .uri("http://localhost:9001/flor/delete/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .timeout(Duration.ofMillis(10_000)).then();
    }


    public Mono<FlowerDTO> updateFlower(FlowerDTO flowerDTO) {
        Mono<FlowerDTO> monoRequest = webClient.put().uri("http://localhost:9001/flor/update/{id}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(flowerDTO), FlowerDTO.class)
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .timeout(Duration.ofMillis(10_000));
        return monoRequest;

    }

    public Mono<FlowerDTO> updateFlower(Integer id, FlowerDTO flowerDTO) {
        Mono<FlowerDTO> monoRequest = webClient.put().uri("http://localhost:9001/flor/update/{id}", id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(flowerDTO), FlowerDTO.class)
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .timeout(Duration.ofMillis(10_000));
        return monoRequest;
    }



}
