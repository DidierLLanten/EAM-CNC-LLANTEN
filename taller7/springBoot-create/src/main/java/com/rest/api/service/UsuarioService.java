package com.rest.api.service;



import com.rest.api.entity.Usuario;
import com.rest.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
        
    public Usuario createUser(Usuario user) { return usuarioRepository.save(user); }    
}
