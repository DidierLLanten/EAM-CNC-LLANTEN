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


    // Crear una nueva categoría
    @PostMapping("/create")
    public Usuario createCategoria(@RequestBody Usuario categoriaDTO) {
        return usuarioService.createUser(categoriaDTO);
    }    
}
