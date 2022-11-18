package com.clase.servicio;

import com.clase.modelo.entity.Compra;
import com.clase.modelo.repo.crud.CompraCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    private final CompraCrud oCompraDAO;

    @Autowired
    public CompraService(CompraCrud oCompraDAO) {
        this.oCompraDAO = oCompraDAO;
    }

    public List<Compra> obtenerTodo() {
        return (List<Compra>) oCompraDAO.findAll();
    }


    public Optional<Compra> obtenerPorId(Integer prId){
        return oCompraDAO.findById(prId);
    }

    public void guardar(Compra oCompra){
        oCompraDAO.save(oCompra);
    }

    public void borrar(Compra oCompra){
        oCompraDAO.delete(oCompra);
    }

}
