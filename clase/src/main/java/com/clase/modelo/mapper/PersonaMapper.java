package com.clase.modelo.mapper;


import com.clase.modelo.entity.Persona;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaMapper implements RowMapper<Persona> {
    @Override
    public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
        Persona oPersona = new Persona();
        oPersona.setId(rs.getInt("ID"));
        oPersona.setNombre1(rs.getString("PRIMER_NOMBRE"));
        oPersona.setNombre2(rs.getString("SEGUNDO_NOMBRE"));
        oPersona.setApellido1(rs.getString("PRIMER_APELLIDO"));
        oPersona.setApellido2(rs.getString("SEGUNDO_APELLIDO"));
        return oPersona;
    }
}
