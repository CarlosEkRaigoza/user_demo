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

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    @ResponseBody
    public String version() {
        return "si sirve";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.guardar(usuario);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.buscarTodos();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void buscarPorId(@PathVariable Long id) {
        usuarioRepository.buscarPorId(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void borrarUsuario(@PathVariable Long id) {
        usuarioRepository.borrar(id);
    }
}