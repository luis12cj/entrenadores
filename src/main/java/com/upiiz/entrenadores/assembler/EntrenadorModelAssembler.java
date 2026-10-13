package com.upiiz.entrenadores.assembler;

import com.upiiz.entrenadores.controller.EntrenadorController;
import com.upiiz.entrenadores.entity.Entrenador;
import com.upiiz.entrenadores.model.EntrenadorModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EntrenadorModelAssembler extends RepresentationModelAssemblerSupport<Entrenador, EntrenadorModel> {

    public EntrenadorModelAssembler() {
        super(EntrenadorController.class, EntrenadorModel.class);
    }

    @Override
    public EntrenadorModel toModel(Entrenador entity) {
        EntrenadorModel model = new EntrenadorModel(
                entity.getId(),
                entity.getNombre(),
                entity.getEspecialidad(),
                entity.getExperiencia(),
                entity.getCorreo()
        );

        model.add(linkTo(methodOn(EntrenadorController.class).obtenerPorId(entity.getId())).withSelfRel());
        model.add(linkTo(methodOn(EntrenadorController.class).actualizar(entity.getId(), entity)).withRel("update"));
        model.add(linkTo(methodOn(EntrenadorController.class).eliminar(entity.getId())).withRel("delete"));
        model.add(linkTo(methodOn(EntrenadorController.class).listarTodos()).withRel("entrenadores"));

        return model;
    }
}