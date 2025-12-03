package com.example.userDemo.controller;

import com.example.userDemo.model.Usuario;
import com.example.userDemo.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.buscarTodos();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.guardar(usuario);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void borrarUsuario(@PathVariable Long id) {
        usuarioRepository.borrar(id);
    }
}