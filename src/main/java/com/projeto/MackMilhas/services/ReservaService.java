package com.projeto.MackMilhas.services;

import com.projeto.MackMilhas.entities.Reserva;
import com.projeto.MackMilhas.repositories.ReservaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepo repository;

    public List<Reserva> listAll(){
        return repository.findAll();
    }

    public Reserva findById(Long id){
        Optional<Reserva> reserva = repository.findById(id);
        return reserva.get();
    }

    public Reserva salvaReserva(Reserva reserva){
        return repository.save(reserva);
    }
}
