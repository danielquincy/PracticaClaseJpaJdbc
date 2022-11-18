package com.clase.modelo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "DANIEL")
public class Daniel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NOMBRE_1")
    private String nombre1;
    @Column(name = "NOMBRE_2")
    private String nombre2;
    @Column(name = "APELLIDO_1")
    private String apellido1;
    @Column(name = "APELLIDO_2")
    private String apellido2;
    @Column(name = "CEDULA")
    private String cedula;
    @Column(name = "EDAD")
    private Integer edad;
    @Column(name = "SEXO")
    private String sexo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Daniel oDaniel = (Daniel) o;
        return id != null && Objects.equals(id, oDaniel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return "Daniel{id=}"+ id +"}";
    }

}

