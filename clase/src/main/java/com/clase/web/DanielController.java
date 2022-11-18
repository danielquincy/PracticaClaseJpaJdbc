package com.clase.web;

import com.clase.modelo.entity.Daniel;
import com.clase.servicio.DanielService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/Daniel")
public class DanielController {
    private final DanielService oDanielService;

    @Autowired
    public DanielController(DanielService oDanielService) {
        this.oDanielService = oDanielService;
    }


    @GetMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Obtiene todos los registros de la tabla DANIEL")
    @ApiParam("No obtiene ningun parametro")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estatus: Satisfactorio"),
            @ApiResponse(code = 404, message = "Estatus: No Encontrado")})
    public ResponseEntity<List<Daniel>> obtenerTodo() {
        return new ResponseEntity<>(oDanielService.obtenerTodo(), HttpStatus.OK);
    }

    @GetMapping(value = "/obtenerPorId/{prId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Daniel> obtenerPorId(@PathVariable Integer prId){
        return new ResponseEntity<>(oDanielService.obtenerPorId(prId), Objects.nonNull(oDanielService.obtenerPorId(prId))? HttpStatus.OK:  HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void guardar(@RequestBody Daniel oDaniel) {
        oDanielService.guardar(oDaniel);
    }

    @PostMapping(value = "/guardarJdbc",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Daniel> guardarJdbc(@RequestBody Daniel oDaniel) {
        return new ResponseEntity<>(oDanielService.guardarJdbc(oDaniel), HttpStatus.OK);
    }


    @PostMapping(value = "/guardarCall",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Daniel>> guardarCall(@RequestBody Daniel oDaniel) {
        return new ResponseEntity<>(oDanielService.callProcedure(oDaniel), HttpStatus.OK);
    }


    @PostMapping(value = "/guardarSimpleJdbc",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Daniel>>  guardarSimpleJdbc(@RequestBody Daniel oDaniel) {
        return new ResponseEntity<>(oDanielService.guardarSimpleJdbcInsert(oDaniel), HttpStatus.OK);
    }

    @PutMapping(value = "/actualizarJdbc",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Daniel> actualizarJdbc(@RequestBody Daniel oDaniel) {
        return new ResponseEntity<>(oDanielService.actualizarJdbc(oDaniel), HttpStatus.OK);
    }

    @DeleteMapping(value = "/borrarJdbc", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String borrarJdbc(@RequestBody Daniel oDaniel) {
        String oText = oDaniel.getId().toString().concat(" ").concat(oDaniel.getNombre1()).concat(" ").concat(oDaniel.getApellido1());
        oDanielService.borrarJdbc(oDaniel);
        return "Registro (" + oText + ") Borrado con Éxito.";
    }
    
}
