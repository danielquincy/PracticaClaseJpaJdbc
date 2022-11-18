package com.clase.modelo.repo.dao;

import com.clase.modelo.entity.Daniel;

import java.util.List;

public interface DanielDAO {
    List<Daniel> obtenerTodo();
    Daniel obtenerPorId(Integer prId);
    void guardar(Daniel oDaniel);
    Daniel guardarJdbc(Daniel oDaniel);
    Daniel actualizarJdbc(Daniel oDaniel);

    void borrarJdbc(Daniel oDaniel);

    List<Daniel> guardarSimpleJdbcInsert(Daniel oDaniel);

    List<Daniel> callProcedure(Daniel oDaniel);
}
