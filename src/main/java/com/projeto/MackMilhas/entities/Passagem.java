package com.projeto.MackMilhas.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id_passagem")
@Entity
@Table(name = "passagem")
public class Passagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_passagem;

    @NotBlank
    private String origem;

    @NotBlank
    private String destino;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @NotNull
    private Double preco;

    @OneToOne(mappedBy = "passagem", cascade = CascadeType.ALL)
    private Reserva reserva;

    public Passagem(Long id_passagem, String origem, String destino, String data, Double preco) {
        this.id_passagem = id_passagem;
        this.origem = origem;
        this.destino = destino;
        this.data = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.preco = preco;
    }
}
