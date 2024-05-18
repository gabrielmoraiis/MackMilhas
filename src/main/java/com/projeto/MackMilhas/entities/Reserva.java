package com.projeto.MackMilhas.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reserva;

    @OneToOne
    @JoinColumn(name = "id_passagem_reserva")
    private Passagem passagem;

    @ManyToOne
    @JoinColumn(name = "id_pessoa_reserva")
    private Pessoa pessoa;

//    public Reserva(Long id_reserva, Long id_passagem_reserva, Long id_pessoa_reserva) {
//        this.id_reserva = id_reserva;
//        this.passagem = passagemService.findById(id_passagem_reserva);
//        this.pessoa = pessoaService.findById(id_pessoa_reserva);
//    }
}
