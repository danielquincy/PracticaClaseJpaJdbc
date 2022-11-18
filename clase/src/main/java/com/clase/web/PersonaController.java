package com.clase.web;

import com.clase.modelo.entity.Persona;
import com.clase.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    private final PersonaService oPersonaService;

    @Autowired
    public PersonaController(PersonaService oPersonaService) {
        this.oPersonaService = oPersonaService;
    }

    @GetMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Persona>> obtenerTodo() {
        return new ResponseEntity<>(oPersonaService.obtenerTodo(), HttpStatus.OK);
    }

//    @GetMapping(value = "/obtenerPorId/{prId}", produces = MediaType.APPLICATION_JSON_VALUE)
//        public ResponseEntity<Persona> obtenerPorId(@PathVariable Integer prId){
//        return new ResponseEntity<>(oPersonaService.obtenerPorId(prId), Objects.nonNull(oPersonaService.obtenerPorId(prId))? HttpStatus.OK:  HttpStatus.NOT_FOUND);
//    }

    @GetMapping(value = "/obtenerPorId/{prId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Persona>> obtenerPorId(@PathVariable Integer prId){
        return new ResponseEntity<>(oPersonaService.obtenerPorId(prId), Objects.nonNull(oPersonaService.obtenerPorId(prId))? HttpStatus.OK:  HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void guardar(@RequestBody Persona oPersona) {
        oPersonaService.guardar(oPersona);
    }

//    @PostMapping(value = "/guardarJdbc",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Persona> guardarJdbc(@RequestBody Persona oPersona) {
//       return new ResponseEntity<>(oPersonaService.guardarJdbc(oPersona), HttpStatus.OK);
//    }

//    @PostMapping(value = "/guardarSimpleJdbc",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Persona>>  guardarSimpleJdbc(@RequestBody Persona oPersona) {
//       return new ResponseEntity<>(oPersonaService.guardarSimpleJdbcInsert(oPersona), HttpStatus.OK);
//    }
//
//    @PutMapping(value = "/actualizarJdbc",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Persona> actualizarJdbc(@RequestBody Persona oPersona) {
//       return new ResponseEntity<>(oPersonaService.actualizarJdbc(oPersona), HttpStatus.OK);
//    }

    @DeleteMapping(value = "/borrar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String borrarJdbc(@RequestBody Persona oPersona) {
        String oText = oPersona.getId().toString().concat(" ").concat(oPersona.getNombre1()).concat(" ").concat(oPersona.getApellido1());
        oPersonaService.borrar(oPersona);
        return "Registro (" + oText + ") Borrado con Éxito.";
    }
}
