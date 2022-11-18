package com.clase.modelo.mapper;

import com.clase.modelo.entity.Daniel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DanielMapper implements RowMapper<Daniel> {
    @Override
    public Daniel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Daniel oDaniel = new Daniel();
        oDaniel.setId(rs.getInt("ID"));
        oDaniel.setNombre1(rs.getString("NOMBRE_1"));
        oDaniel.setNombre2(rs.getString("NOMBRE_2"));
        oDaniel.setApellido1(rs.getString("APELLIDO_1"));
        oDaniel.setApellido2(rs.getString("APELLIDO_2"));
        oDaniel.setCedula(rs.getString("CEDULA"));
        oDaniel.setEdad(rs.getInt("EDAD"));
        oDaniel.setSexo(rs.getString("SEXO"));
        return oDaniel;
    }
}
