package cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t01.n03.controllers;

import cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t01.n03.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t01.n03.model.services.FlowerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/flor")
public class FlowerController {

    private FlowerService flowerService;

    @PostMapping("/addFlowerClient")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FlowerDTO> addFlower(@RequestBody FlowerDTO flowerDTO) {
        return flowerService.addFlower(flowerDTO);
    }

    @GetMapping("/getAllFlowersClient")
    @ResponseStatus(HttpStatus.OK)
    public Flux<FlowerDTO> getAll() {
        return flowerService.getAllFlower();
    }

    @GetMapping("getFlowerClient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FlowerDTO> getFLowerByID(@PathVariable("id") Integer id) {
        return flowerService.getFlowerByID(id);
    }


    @DeleteMapping("/deleteFlowerClient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteFlower(@PathVariable("id") Integer id) {
        return flowerService.deleteFlowerByID(id);
    }

    @PutMapping("/updateClient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FlowerDTO> updateByID(@RequestBody FlowerDTO flowerDTO, @PathVariable("id") Integer id) {
        return flowerService.updateFlower(id, flowerDTO);
    }


}



