package com.upiiz.entrenadores.controller;

import com.upiiz.entrenadores.assembler.EntrenadorModelAssembler;
import com.upiiz.entrenadores.entity.Entrenador;
import com.upiiz.entrenadores.model.EntrenadorModel;
import com.upiiz.entrenadores.service.EntrenadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/entrenadores")
@Tag(name = "Entrenadores", description = "Operaciones CRUD para la gestión de entrenadores con soporte HATEOAS")
public class EntrenadorController {

    @Autowired
    private EntrenadorService service;

    @Autowired
    private EntrenadorModelAssembler assembler;

    @Operation(summary = "Listar todos los entrenadores", description = "Retorna una lista completa de entrenadores con enlaces HATEOAS")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public ResponseEntity<CollectionModel<EntrenadorModel>> listarTodos() {
        List<Entrenador> lista = service.listarTodos();
        CollectionModel<EntrenadorModel> collectionModel = assembler.toCollectionModel(lista);
        collectionModel.add(linkTo(methodOn(EntrenadorController.class).listarTodos()).withSelfRel());
        return ResponseEntity.ok(collectionModel);
    }

    @Operation(summary = "Obtener entrenador por ID", description = "Busca un entrenador por su identificador único")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entrenador encontrado"),
            @ApiResponse(responseCode = "404", description = "Entrenador no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntrenadorModel> obtenerPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Registrar un nuevo entrenador", description = "Crea un registro de entrenador en la base de datos")
    @ApiResponse(responseCode = "201", description = "Entrenador creado exitosamente")
    @PostMapping
    public ResponseEntity<EntrenadorModel> crear(@RequestBody Entrenador entrenador) {
        Entrenador nuevo = service.guardar(entrenador);
        EntrenadorModel model = assembler.toModel(nuevo);
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @Operation(summary = "Actualizar un entrenador", description = "Actualiza los datos de un entrenador existente por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entrenador actualizado"),
            @ApiResponse(responseCode = "404", description = "Entrenador no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntrenadorModel> actualizar(@PathVariable Long id, @RequestBody Entrenador entrenador) {
        return service.actualizar(id, entrenador)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Eliminar un entrenador", description = "Elimina el registro de un entrenador por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Entrenador eliminado"),
            @ApiResponse(responseCode = "404", description = "Entrenador no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}