package com.clase.modelo.repo.crud;

import com.clase.modelo.entity.Persona;
import com.clase.modelo.repo.dao.PersonaDAO;
import org.springframework.data.repository.CrudRepository;

public interface PersonaCrud extends CrudRepository<Persona, Integer>, PersonaDAO {
}
