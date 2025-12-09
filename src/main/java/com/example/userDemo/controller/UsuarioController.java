package com.example.userDemo.controller;

import com.example.userDemo.model.Usuario;
import com.example.userDemo.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    @ResponseBody
    public String version() {
        return "si sirve";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void buscarPorId(@PathVariable Long id) {
        usuarioService.buscarPorId(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void borrarUsuario(@PathVariable Long id) {
        usuarioService.borrarUsuario(id);
    }
}