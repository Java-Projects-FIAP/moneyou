package br.com.fiap.moneyou.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.moneyou.model.Cofrinho;

@RestController
public class CofrinhoController {

    private List<Cofrinho> repository = new ArrayList<>();

    // Listar todas as categorias
    // GET :8080/categories -> 200 ok -> json
    @GetMapping("/cofrinhos")
    public List<Cofrinho> index() {
        return repository;
    }

    // Cadastrar categorias
    @PostMapping("/cofrinhos")
    // @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Cofrinho> create(@RequestBody Cofrinho cofrinho) {
        System.out.println("Guardando seu dinheiro...");
        repository.add(cofrinho);
        return ResponseEntity.status(201).body(cofrinho);
    }

    // Detalhes da categorias
    @GetMapping("/cofrinhos/{id}")
    public ResponseEntity<Cofrinho> get(@PathVariable Long id) {
        System.out.println("Buscando categoria " + id);
        var cofrinho = repository
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (cofrinho.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cofrinho.get());
    }
}
