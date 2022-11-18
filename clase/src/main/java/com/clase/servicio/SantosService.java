package com.clase.servicio;

import com.clase.modelo.entity.Santos;
import com.clase.modelo.repo.dao.SantosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SantosService {

    private final SantosDAO oSantosDAO;

    @Autowired
    public SantosService(SantosDAO oSantosDAO) {
        this.oSantosDAO = oSantosDAO;
    }

    public List<Santos> obtenerTodo() {
        return oSantosDAO.obtenerTodo();
    }
    public Santos obtenerPorId(Integer prId){
        return oSantosDAO.obtenerPorId(prId);
    }
    public void guardar(Santos oSantos){
        oSantosDAO.guardar(oSantos);
    }
    public Santos guardarJdbc(Santos oSantos){
        return   oSantosDAO.guardarJdbc(oSantos);
    }

    public List<Santos> guardarSimpleJdbcInsert(Santos oSantos){
        return oSantosDAO.guardarSimpleJdbcInsert(oSantos);
    }

    public Santos actualizarJdbc(Santos oSantos){
        return   oSantosDAO.actualizarJdbc(oSantos);
    }

    public void borrarJdbc(Santos oSantos){
        oSantosDAO.borrarJdbc(oSantos);
    }
}
