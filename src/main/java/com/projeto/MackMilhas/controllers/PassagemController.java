package com.projeto.MackMilhas.controllers;

import com.projeto.MackMilhas.entities.Passagem;
import com.projeto.MackMilhas.services.PassagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class PassagemController {

    @Autowired
    private PassagemService passagemService;

    public PassagemController() {}

    @GetMapping("/passagem")
    public Iterable<Passagem> getPassagem() {
        return passagemService.listAll();
    }

    @GetMapping("/passagem/{id}")
    public ResponseEntity<Passagem> getPassagem(@PathVariable long id) {
        Optional<Passagem> passagem = Optional.ofNullable(passagemService.findById(id));
        return passagem.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passagem não encontrada com id " + id));
    }

    @PostMapping("/passagem")
    public ResponseEntity<Passagem> createPassagem(@RequestBody Passagem p) {
        Passagem novaPassagem = passagemService.salvaPassagem(p);
        return new ResponseEntity<>(novaPassagem, HttpStatus.CREATED);
    }

    @PutMapping("/passagem/{id}")
    public ResponseEntity<Passagem> updatePassagem(@RequestBody Passagem passagem, @PathVariable long id) {
        Optional<Passagem> passagemExistente = Optional.ofNullable(passagemService.findById(id));
        if (!passagemExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Passagem não encontrada com id " + id);
        }
        Passagem passagemAtualizada = passagemService.atualizaPassagem(id, passagem);
        return new ResponseEntity<>(passagemAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/passagem/{id}")
    public ResponseEntity<Void> deletePassagem(@PathVariable long id) {
        Optional<Passagem> passagemExistente = Optional.ofNullable(passagemService.findById(id));
        if (!passagemExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Passagem não encontrada com id " + id);
        }
        passagemService.deletaPassagem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
