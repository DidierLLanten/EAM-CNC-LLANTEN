package com.rest.api.controller;

import com.rest.api.entity.Usuario;
import com.rest.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @PutMapping("/update/{id}")
    public Optional<Usuario> updateUser(@PathVariable Integer id, @RequestBody Usuario usuarioDTO) {
        return usuarioService.updateUser(id, usuarioDTO);
    }
}
