package com.rest.api.service;



import com.rest.api.entity.Usuario;
import com.rest.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Usuario> getAllUsers(){ return usuarioRepository.findAll(); }

    public Usuario getUserById(Integer id) { return usuarioRepository.getUserById(id); } 
}
