package com.projeto.MackMilhas.entities;

import jakarta.validation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_pessoa")
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pessoa;

    @NotBlank
    private String nome;

    @NotBlank
    private String senha;

    @OneToMany(mappedBy = "pessoa")
    private List<Reserva> lista_de_reservas;

    public Pessoa(Long id_pessoa, String nome, String senha) {
        this.id_pessoa = id_pessoa;
        this.nome = nome;
        this.senha = senha;
    }

}
