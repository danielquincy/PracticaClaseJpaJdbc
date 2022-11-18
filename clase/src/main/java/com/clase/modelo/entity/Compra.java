package com.clase.modelo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "COMPRA")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @JoinColumn(name = "ID_PERSONA",referencedColumnName = "ID",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Persona persona;

}
