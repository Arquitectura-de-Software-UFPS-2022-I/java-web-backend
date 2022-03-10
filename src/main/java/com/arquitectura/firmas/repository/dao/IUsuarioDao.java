package com.arquitectura.firmas.repository.dao;

import com.arquitectura.firmas.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioDao extends JpaRepository<Usuario,Integer> {
    @Query("select u from Usuario u where u.email=?1")
    public Usuario findByEmail(String email);
}
