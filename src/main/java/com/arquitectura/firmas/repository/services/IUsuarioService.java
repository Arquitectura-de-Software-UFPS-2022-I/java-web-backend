package com.arquitectura.firmas.repository.services;

import com.arquitectura.firmas.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario save(Usuario user);
    List<Usuario> findAll();
    void delete(int id);
    Usuario findById(int id);
    Usuario update(Usuario user, int id);
}
