package com.clase.servicio;

import com.clase.modelo.entity.Daniel;
import com.clase.modelo.repo.dao.DanielDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanielService {
    private final DanielDAO oDanielDAO;

    @Autowired
    public DanielService(DanielDAO oDanielDAO) {
        this.oDanielDAO = oDanielDAO;
    }


    public List<Daniel> obtenerTodo() {
        return oDanielDAO.obtenerTodo();
    }
    public Daniel obtenerPorId(Integer prId){
        return oDanielDAO.obtenerPorId(prId);
    }
    public void guardar(Daniel oDaniel){
        oDanielDAO.guardar(oDaniel);
    }
    public Daniel guardarJdbc(Daniel oDaniel){
        return   oDanielDAO.guardarJdbc(oDaniel);
    }

    public List<Daniel> guardarSimpleJdbcInsert(Daniel oDaniel){
        return oDanielDAO.guardarSimpleJdbcInsert(oDaniel);
    }

    public Daniel actualizarJdbc(Daniel oDaniel){
        return   oDanielDAO.actualizarJdbc(oDaniel);
    }

    public void borrarJdbc(Daniel oDaniel){
        oDanielDAO.borrarJdbc(oDaniel);
    }

    public List<Daniel> callProcedure(Daniel oDaniel){
        return oDanielDAO.callProcedure(oDaniel);
    }
}
