package com.clase.modelo.repo.dao;

import com.clase.modelo.entity.Daniel;
import com.clase.modelo.entity.Persona;
import com.clase.modelo.repo.crud.PersonaCrud;

import java.util.List;

public interface PersonaDAO{

    List<Persona> obtenerTodo();
    Persona obtenerPorId(Integer prId);
    void guardar(Persona oPersona);
    Persona guardarJdbc(Persona oPersona);
    Persona actualizarJdbc(Persona oPersona);

    void borrarJdbc(Persona oPersona);

    List<Persona> guardarSimpleJdbcInsert(Persona oPersona);


}
