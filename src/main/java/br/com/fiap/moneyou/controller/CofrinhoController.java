package br.com.fiap.moneyou.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.moneyou.model.Cofrinho;

@RestController
public class CofrinhoController {

    private Logger log = LoggerFactory.getLogger(getClass());
    private List<Cofrinho> repository = new ArrayList<>();

    @GetMapping("/cofrinhos")
    public List<Cofrinho> index() {
        return repository;
    }

    @PostMapping("/cofrinhos")
    public ResponseEntity<Cofrinho> create(@RequestBody Cofrinho cofrinho) {
        log.info("Guardando seu dinheiro no cofrinho...");
        repository.add(cofrinho);
        return ResponseEntity.status(201).body(cofrinho);
    }

    @GetMapping("/cofrinhos/{id}")
    public ResponseEntity<Cofrinho> get(@PathVariable Long id) {
        log.info("Buscando cofrinho " + id);
        var cofrinho = repository
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (cofrinho.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cofrinho.get());
    }

    @DeleteMapping("/cofrinhos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando cofrinho " + id);
        repository.remove(getCofrinho(id));
    }

    @PutMapping("/cofrinhos/{id}")
    public Cofrinho update(@PathVariable Long id, @RequestBody Cofrinho cofrinho) {
        log.info("Atualizando categoria " + id + " " + cofrinho);
        cofrinho.setId(id);
        repository.add(cofrinho);

        return cofrinho;
    }

    private Cofrinho getCofrinho(Long id) {
        return repository
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
