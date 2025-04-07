package br.com.fiap.moneyou.controller;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.moneyou.model.Usuario;
import br.com.fiap.moneyou.repository.UsuarioRepository;

@RestController
@RequestMapping("users")
public class UsuarioController {

    private Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("verify-email/{email}")
    public Optional<Usuario> getUserByEmail(@PathVariable String email) {
        log.info("Buscando usuário com email " + email);
        return repository.findByEmail(email);
    }

    @GetMapping("{id}")
    public Usuario getUserById(@PathVariable Long id) {
        log.info("Buscando usuário " + id);
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario user) {
        log.info("Criando usuário " + user);
        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Apagando usuário " + id);
        Usuario user = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        repository.delete(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
