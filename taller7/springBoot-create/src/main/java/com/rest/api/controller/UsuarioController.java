package com.rest.api.controller;

import com.rest.api.entity.Usuario;
import com.rest.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping("/create")
    public Usuario createUser(@RequestBody Usuario categoriaDTO) {
        return usuarioService.createUser(categoriaDTO);
    }    
}
