package com.projeto.MackMilhas.controllers;

import com.projeto.MackMilhas.DTO.ReservaDto;
import com.projeto.MackMilhas.entities.Passagem;
import com.projeto.MackMilhas.entities.Pessoa;
import com.projeto.MackMilhas.entities.Reserva;
import com.projeto.MackMilhas.services.PassagemService;
import com.projeto.MackMilhas.services.PessoaService;
import com.projeto.MackMilhas.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PassagemService passagemService;

    @GetMapping
    public Iterable<Reserva> getReservas() {
        return reservaService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReserva(@PathVariable long id) {
        Optional<Reserva> reserva = Optional.ofNullable(reservaService.findById(id));
        return reserva.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestBody ReservaDto reservaDto) {
        try {
            Pessoa pessoa = pessoaService.findById(reservaDto.getPessoaId());
            Passagem passagem = passagemService.findById(reservaDto.getPassagemId());

            if (pessoa == null || passagem == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Reserva reserva = new Reserva();
            reserva.setPessoa(pessoa);
            reserva.setPassagem(passagem);

            Reserva novaReserva = reservaService.salvaReserva(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
