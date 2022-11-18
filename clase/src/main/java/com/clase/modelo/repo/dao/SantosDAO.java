package com.clase.modelo.repo.dao;

import com.clase.modelo.entity.Santos;

import java.util.List;

public interface SantosDAO {
    List<Santos> obtenerTodo();
    Santos obtenerPorId(Integer prId);
    void guardar(Santos oSantos);
    Santos guardarJdbc(Santos oSantos);
    Santos actualizarJdbc(Santos oSantos);

    void borrarJdbc(Santos oSantos);

    List<Santos> guardarSimpleJdbcInsert(Santos oSantos);
}
