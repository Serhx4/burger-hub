package com.github.serhx4.rest;

import com.github.serhx4.domain.dto.BurgerCreateDto;
import com.github.serhx4.domain.dto.BurgerReadDto;
import com.github.serhx4.service.BurgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/burgers")
@RequiredArgsConstructor
public class BurgerRestController {

    private final BurgerService burgerService;

    @GetMapping
    public List<BurgerReadDto> chefDesigns() {
        return burgerService.findAllByUsername("chef");
    }

    @GetMapping(params = "author")
    public List<BurgerReadDto> burgersByAuthor(@RequestParam("author") String author) {
        return burgerService.findAllByUsername(author);
    }

    @GetMapping("/{id}")
    public BurgerReadDto burgerById(@PathVariable("id") Long id) {
        return burgerService.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BurgerReadDto addBurger(@Valid @RequestBody BurgerCreateDto burgerCreateDto) {
        return burgerService.save(burgerCreateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBurger(@PathVariable("id") Long id) {
        if(!burgerService.deleteById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
