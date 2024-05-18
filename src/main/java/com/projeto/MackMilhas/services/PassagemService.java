package com.projeto.MackMilhas.services;

import com.projeto.MackMilhas.entities.Passagem;
import com.projeto.MackMilhas.repositories.PassagemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PassagemService {

    @Autowired
    private PassagemRepo repository;

    public List<Passagem> listAll(){
        return repository.findAll();
    }

    public Passagem findById(Long id){
        Optional<Passagem> passagem = repository.findById(id);
        return passagem.get();
    }

    public Passagem salvaPassagem(Passagem passagem){
        return repository.save(passagem);
    }

    public void deletaPassagem(Long id){
        repository.deleteById(id);
    }

    public Passagem atualizaPassagem(Long id, Passagem novaPassagem){
        if (repository.existsById(id)) {
            novaPassagem.setId_passagem(id);
            return repository.save(novaPassagem);
        }
        return null;
    }

    public LocalDate converterParaData(String dataString){
        return LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
