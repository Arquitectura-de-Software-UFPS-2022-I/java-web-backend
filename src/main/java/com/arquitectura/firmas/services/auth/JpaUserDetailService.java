package com.arquitectura.firmas.services.auth;

import com.arquitectura.firmas.repository.dao.IUsuarioDao;
import com.arquitectura.firmas.entities.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("jpaUserDetailService")
public class JpaUserDetailService implements UserDetailsService {

    @Autowired
    private IUsuarioDao usuarioDao;
    private Logger logger = LoggerFactory.getLogger(JpaUserDetailService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        logger.info("EMAIL EN JPA: "+ email);
        Usuario usuario = usuarioDao.findByEmail(email);

        if(usuario == null) {
            logger.error("Error en el Login: no existe el email '" + email + "' en el sistema!");
            throw new UsernameNotFoundException("email: " + email + " no existe en el sistema!");
        }

        List<GrantedAuthority> role = new ArrayList<GrantedAuthority>();

        role.add(new SimpleGrantedAuthority(usuario.getRol()));

        return new User(usuario.getEmail(), usuario.getPassword(),role);
    }
}
