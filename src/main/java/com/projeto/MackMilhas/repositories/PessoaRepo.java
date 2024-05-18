package com.projeto.MackMilhas.repositories;

import com.projeto.MackMilhas.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PessoaRepo extends JpaRepository<Pessoa, Long> {
    Pessoa findByNome(String nome);
}
