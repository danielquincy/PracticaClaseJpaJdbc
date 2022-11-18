package com.clase.modelo.mapper;

import com.clase.modelo.entity.Santos;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SantosMapper implements RowMapper<Santos> {
    @Override
    public Santos mapRow(ResultSet rs, int rowNum) throws SQLException {
        Santos oSantos = new Santos();
        oSantos.setId(rs.getInt("Id"));
        oSantos.setNombre(rs.getString("Nombre"));
        oSantos.setApellido(rs.getString("Apellido"));
        oSantos.setEdad(rs.getInt("Edad"));

        return oSantos;
    }
}