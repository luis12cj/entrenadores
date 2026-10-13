package com.upiiz.entrenadores.service;

import com.upiiz.entrenadores.entity.Entrenador;
import com.upiiz.entrenadores.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorServiceImpl implements EntrenadorService {

    @Autowired
    private EntrenadorRepository repository;

    @Override
    public List<Entrenador> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Entrenador> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Entrenador guardar(Entrenador entrenador) {
        return repository.save(entrenador);
    }

    @Override
    public Optional<Entrenador> actualizar(Long id, Entrenador entrenadorActualizado) {
        return repository.findById(id).map(existente -> {
            existente.setNombre(entrenadorActualizado.getNombre());
            existente.setEspecialidad(entrenadorActualizado.getEspecialidad());
            existente.setExperiencia(entrenadorActualizado.getExperiencia());
            existente.setCorreo(entrenadorActualizado.getCorreo());
            return repository.save(existente);
        });
    }

    @Override
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}