package com.upiiz.entrenadores.service;

import com.upiiz.entrenadores.entity.Entrenador;
import java.util.List;
import java.util.Optional;

public interface EntrenadorService {
    List<Entrenador> listarTodos();
    Optional<Entrenador> buscarPorId(Long id);
    Entrenador guardar(Entrenador entrenador);
    Optional<Entrenador> actualizar(Long id, Entrenador entrenador);
    boolean eliminar(Long id);
}