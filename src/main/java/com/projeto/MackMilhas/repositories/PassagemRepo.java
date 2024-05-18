package com.projeto.MackMilhas.repositories;

import com.projeto.MackMilhas.entities.Passagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassagemRepo extends JpaRepository<Passagem, Long> {
}
