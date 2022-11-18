package com.clase.web;

import com.clase.modelo.entity.Santos;
import com.clase.servicio.SantosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/Santos")
public class SantosController {

    private final SantosService oSantosService;

    @Autowired
    public SantosController(SantosService oSantosService) {
        this.oSantosService = oSantosService;
    }

    @GetMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Santos>> obtenerTodo() {
        return new ResponseEntity<>(oSantosService.obtenerTodo(), HttpStatus.OK);
    }

    @GetMapping(value = "/obtenerPorId/{prId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Santos> obtenerPorId(@PathVariable Integer prId){
        return new ResponseEntity<>(oSantosService.obtenerPorId(prId), Objects.nonNull(oSantosService.obtenerPorId(prId))? HttpStatus.OK:  HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void guardar(@RequestBody Santos oSantos) {
        oSantosService.guardar(oSantos);
    }

    @PostMapping(value = "/guardarJdbc",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Santos> guardarJdbc(@RequestBody Santos oSantos) {
        return new ResponseEntity<>(oSantosService.guardarJdbc(oSantos), HttpStatus.OK);
    }

    @PostMapping(value = "/guardarSimpleJdbc",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Santos>>  guardarSimpleJdbc(@RequestBody Santos oSantos) {
        return new ResponseEntity<>(oSantosService.guardarSimpleJdbcInsert(oSantos), HttpStatus.OK);
    }

    @PutMapping(value = "/actualizarJdbc",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Santos> actualizarJdbc(@RequestBody Santos oSantos) {
        return new ResponseEntity<>(oSantosService.actualizarJdbc(oSantos), HttpStatus.OK);
    }

    @DeleteMapping(value = "/borrarJdbc", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String borrarJdbc(@RequestBody Santos oSantos) {
        String oText = oSantos.getId().toString().concat(" ").concat(oSantos.getNombre()).concat(" ").concat(oSantos.getApellido());
        oSantosService.borrarJdbc(oSantos);
        return "Registro (" + oText + ") Borrado con Éxito.";
    }
}
