package br.com.fiap.moneyou.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.moneyou.model.Cofrinho;
import br.com.fiap.moneyou.repository.CofrinhoRepository;

@RestController
@RequestMapping("cofrinhos")
public class CofrinhoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CofrinhoRepository repository;

    @GetMapping
    public List<Cofrinho> index() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cofrinho> create(@RequestBody Cofrinho cofrinho) {
        log.info("Guardando seu dinheiro no cofrinho...");
        repository.save(cofrinho);
        return ResponseEntity.status(201).body(cofrinho);
    }

    @GetMapping("{id}")
    public Cofrinho get(@PathVariable Long id) {
        log.info("Buscando cofrinho " + id);
        return getCofrinho(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando cofrinho " + id);
        repository.delete(getCofrinho(id));
    }

    @PutMapping("{id}")
    public Cofrinho update(@PathVariable Long id, @RequestBody Cofrinho cofrinho) {
        log.info("Atualizando cofrinho " + id + " " + cofrinho);

        getCofrinho(id);
        cofrinho.setId(id);
        repository.save(cofrinho);

        return cofrinho;
    }

    private Cofrinho getCofrinho(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
