package com.projeto.MackMilhas.configs;

import com.projeto.MackMilhas.entities.Passagem;
import com.projeto.MackMilhas.entities.Pessoa;
import com.projeto.MackMilhas.entities.Reserva;
import com.projeto.MackMilhas.repositories.PassagemRepo;
import com.projeto.MackMilhas.repositories.PessoaRepo;
import com.projeto.MackMilhas.repositories.ReservaRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

    @Autowired
    PessoaRepo pessoaRepo;

    @Autowired
    PassagemRepo passagemRepo;

    @Autowired
    ReservaRepo reservaRepo;

    @Override
    public void run(String... args) throws Exception {
        Pessoa p1 = new Pessoa(null, "Marina", "1234567");
        Pessoa p2 = new Pessoa(null, "Gabriel", "1");

        Passagem pass1 = new Passagem(null, "São Paulo", "Salvador", "20/01/2025", 642.90);
        Passagem pass2 = new Passagem(null, "Rio de Janeiro", "São Paulo", "16/05/2024", 724.99);

        pessoaRepo.saveAll(Arrays.asList(p1,p2));
        passagemRepo.saveAll(Arrays.asList(pass1,pass2));


        System.out.println(pass1.getData());

//        Reserva r1 = new Reserva(null, pass1.getId_passagem(), p1.getId_pessoa());
//        Reserva r2 = new Reserva(null, pass2.getId_passagem(), p1.getId_pessoa());
//
//        reservaRepo.saveAll(Arrays.asList(r1,r2));
    }
}
