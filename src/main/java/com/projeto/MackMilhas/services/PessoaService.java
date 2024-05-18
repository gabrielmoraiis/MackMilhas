package com.projeto.MackMilhas.services;

import com.projeto.MackMilhas.entities.Pessoa;
import com.projeto.MackMilhas.repositories.PessoaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepo repository;

    public List<Pessoa> listAll(){
        return repository.findAll();
    }

    public Pessoa findById(Long id){
        Optional<Pessoa> pessoa = repository.findById(id);
        return pessoa.get();
    }

    public Pessoa salvaPessoa(Pessoa pessoa){
        return repository.save(pessoa);
    }

    public void deletaPessoa(Long id){
        repository.deleteById(id);
    }

    public  Pessoa atualizaPessoa(Long id, Pessoa novaPessoa){
        if (repository.existsById(id)) {
            novaPessoa.setId_pessoa(id);
            return repository.save(novaPessoa);
        }
        return null;
    }

    public Pessoa buscaPorNome(String nome){
        return repository.findByNome(nome);
    }


}
