package com.clase.web;

import com.clase.modelo.entity.Compra;
import com.clase.servicio.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/Compra")
public class CompraController {

    private final CompraService oService;

    @Autowired
    public CompraController(CompraService oService) {
        this.oService = oService;
    }


    @GetMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Compra>> obtenerTodo() {
        return new ResponseEntity<>(oService.obtenerTodo(), HttpStatus.OK);
    }

    @GetMapping(value = "/obtenerPorId/{prId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Compra>> obtenerPorId(@PathVariable Integer prId){
        return new ResponseEntity<>(oService.obtenerPorId(prId), Objects.nonNull(oService.obtenerPorId(prId))? HttpStatus.OK:  HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void guardar(@RequestBody Compra oCompra) {
        oService.guardar(oCompra);
    }

    @DeleteMapping(value = "/borrar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String borrarJdbc(@RequestBody Compra oCompra) {
        String oText = oCompra.getId().toString().concat(" ").concat(oCompra.getDescripcion());
        oService.borrar(oCompra);
        return "Registro (" + oText + ") Borrado con Éxito.";
    }
}

