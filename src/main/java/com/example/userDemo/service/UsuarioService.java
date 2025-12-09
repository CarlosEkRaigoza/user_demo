package com.example.userDemo.service;

import com.example.userDemo.model.Usuario;
import com.example.userDemo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Usuario registrarUsuario(Usuario usuario) {

        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new RuntimeException("Error: El nombre del usuario es obligatorio.");
        }

        if (usuario.getNumTelefono() == null) {
            throw new RuntimeException("Error: Se requiere un número de teléfono.");
        }
        usuario.setNombre(usuario.getNombre().toUpperCase());
        return usuarioRepository.guardar(usuario);
    }


    public List<Usuario> obtenerTodos() {
        return usuarioRepository.buscarTodos();
    }

    public Usuario buscarPorId(Long id) {
        Usuario u = usuarioRepository.buscarPorId(id);
        if (u == null) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        return u;
    }

    public void borrarUsuario(Long id) {

        Usuario u = usuarioRepository.buscarPorId(id);
        if (u == null) {
            throw new RuntimeException("No se puede borrar. El usuario con ID " + id + " no existe.");
        }

        usuarioRepository.borrar(id);
    }
}