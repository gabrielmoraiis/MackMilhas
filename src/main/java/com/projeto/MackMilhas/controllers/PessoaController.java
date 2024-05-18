package com.projeto.MackMilhas.controllers;

import com.projeto.MackMilhas.entities.Pessoa;
import com.projeto.MackMilhas.repositories.PessoaRepo;
import com.projeto.MackMilhas.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@RestController
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;
    public PessoaController() {}


    @GetMapping("/pessoa")
    Iterable<Pessoa> getPessoa() {
        return pessoaService.listAll();
    }

    @GetMapping("/pessoa/{id}")
    Optional<Pessoa> getPessoa(@PathVariable(required = true, name="id") long id){
        return Optional.ofNullable(pessoaService.findById(id));
    }

    @PostMapping("/pessoa")
    Pessoa createPessoa(@RequestBody Pessoa p) {
        return pessoaService.salvaPessoa(p);
    }

    @PostMapping("/login")
    //public ResponseEntity<Pessoa> login(@RequestParam String nome, @RequestParam String senha) {
    public ResponseEntity<Pessoa> login(@RequestBody Pessoa p) {
        Pessoa pessoa = this.pessoaService.buscaPorNome(p.getNome());
        if(pessoa != null){
            if(pessoa.getSenha().equals(p.getSenha())){
                return ResponseEntity.status(HttpStatus.OK).body(pessoa);
            }
        }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/pessoa/{id}")
    Optional<Pessoa> updatePessoa(@RequestBody Pessoa pessoa, @PathVariable(required = true, name="id") long id) {
        Pessoa pessoaAtualizada = pessoaService.atualizaPessoa(id, pessoa);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados do prefessor com id " + id);
    }

    @DeleteMapping(value = "/pessoa/{id}")
    void deletePessoa(@PathVariable(required = true, name = "id") long id) {
        pessoaService.deletaPessoa(id);
    }
}
