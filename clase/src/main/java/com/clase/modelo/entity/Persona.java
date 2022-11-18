package com.clase.modelo.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "PERSONA")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PRIMER_NOMBRE")
    private String nombre1;

    @Column(name = "SEGUNDO_NOMBRE")
    private String nombre2;

    @Column(name = "PRIMER_APELLIDO")
    private String apellido1;

    @Column(name = "SEGUNDO_APELLIDO")
    private String apellido2;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Persona persona = (Persona) o;
        return id != null && Objects.equals(id, persona.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return "Persona{id=}"+ id +"}";
    }

}
