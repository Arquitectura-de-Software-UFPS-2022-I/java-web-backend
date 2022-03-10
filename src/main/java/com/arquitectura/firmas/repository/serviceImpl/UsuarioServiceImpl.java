package com.arquitectura.firmas.repository.serviceImpl;

import com.arquitectura.firmas.entities.Usuario;
import com.arquitectura.firmas.exception.GenericException;
import com.arquitectura.firmas.repository.dao.IUsuarioDao;
import com.arquitectura.firmas.repository.services.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {


    @Autowired
    private IUsuarioDao userDao;

    //


    @Override
    public Usuario save(Usuario user) {
        Usuario userEmail = userDao.findByEmail(user.getEmail());
        if(userEmail != null){
            throw new GenericException(HttpStatus.BAD_REQUEST,"El email ya esta registrado");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) userDao.findAll();
    }

    @Override
    public void delete(int id) {
        Usuario user = userDao.findById(id).orElse(null);
        if(user==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"El usuario no existe en la BD");
        }
        userDao.deleteById(id);
    }

    @Override
    public Usuario findById(int id) {
        Usuario user = userDao.findById(id).orElse(null);
        if(user==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"El usuario no existe en la BD");
        }
        return user;
    }

    @Override
    public Usuario update(Usuario user, int id) {
        Usuario user2 = userDao.findById(id).orElse(null);
        if(user2==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"El usuario no existe en la BD");
        }
        user2.setEmail(user.getEmail());
        user2.setUsername(user.getUsername());
        user2.setName(user.getName());
        user2.setPassword( new BCryptPasswordEncoder().encode(user.getPassword()));
        return userDao.save(user2);
    }
}
