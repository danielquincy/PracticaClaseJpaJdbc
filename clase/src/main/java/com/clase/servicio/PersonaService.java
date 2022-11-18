package com.clase.servicio;

import com.clase.modelo.entity.Daniel;
import com.clase.modelo.entity.Persona;
import com.clase.modelo.repo.crud.PersonaCrud;
import com.clase.modelo.repo.dao.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaCrud oPersonaDAO;

    @Autowired
    public PersonaService(PersonaCrud oPersonaDAO) {
        this.oPersonaDAO = oPersonaDAO;
    }

    public List<Persona> obtenerTodo() {
        return (List<Persona>) oPersonaDAO.findAll();
    }

    public Optional<Persona> obtenerPorId(Integer prId){
        return oPersonaDAO.findById(prId);
    }

    public void guardar(Persona oPersona){
        oPersonaDAO.save(oPersona);
    }

    public void guardarJdbc(Persona oPersona){
        oPersonaDAO.guardarJdbc(oPersona);
    }

    public void borrar(Persona oPersona){
        oPersonaDAO.delete(oPersona);
    }

//    public List<Persona> obtenerTodo() {
//        return oPersonaDAO.obtenerTodo();
//    }
//
//    public Persona obtenerPorId(Integer prId){
//        return oPersonaDAO.obtenerPorId(prId);
//    }
//
//    public void guardar(Persona oPersona){
//        oPersonaDAO.guardar(oPersona);
//    }
//
//    public Persona guardarJdbc(Persona oPersona){
//      return   oPersonaDAO.guardarJdbc(oPersona);
//    }
//
//
//    public List<Persona> guardarSimpleJdbcInsert(Persona oPersona){
//        return oPersonaDAO.guardarSimpleJdbcInsert(oPersona);
//    }
//
//    public Persona actualizarJdbc(Persona oPersona){
//      return   oPersonaDAO.actualizarJdbc(oPersona);
//    }
//
//    public void borrarJdbc(Persona oPersona){
//        oPersonaDAO.borrarJdbc(oPersona);
//    }
}
