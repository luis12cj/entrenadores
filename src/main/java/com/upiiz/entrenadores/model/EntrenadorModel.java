package com.upiiz.entrenadores.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(itemRelation = "entrenador", collectionRelation = "entrenadores")
public class EntrenadorModel extends RepresentationModel<EntrenadorModel> {

    private Long id;
    private String nombre;
    private String especialidad;
    private Integer experiencia;
    private String correo;

    public EntrenadorModel() {
    }

    public EntrenadorModel(Long id, String nombre, String especialidad, Integer experiencia, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.experiencia = experiencia;
        this.correo = correo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}