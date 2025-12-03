package com.example.userDemo.controller;

import com.example.userDemo.model.Usuario;
import com.example.userDemo.model.Direccion;
import com.example.userDemo.repository.UsuarioRepository;

//Usamos este para la inyeccion
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
Convierte esta clase en un "Controlador REST".
Significa que sabrá responder con datos JSON (texto) en lugar de páginas HTML.
*/
@RestController

/*
URL: http://localhost:8080/usuarios
*/
@RequestMapping("/usuarios")
public class UsuarioController {

    //Aquí usamos la inyeccion pq no hacemos instancia, lo hace spring
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {

        if(usuario.getDirecciones() != null) {
            for(Direccion direccion : usuario.getDirecciones()) {
                direccion.setUsuario(usuario);
            }
        }

        return usuarioRepository.save(usuario);
    }


    @DeleteMapping("/{id}")
    // Busca en la url la parte donde pusimos "{id}" y guarda ese número en la variable 'id'.
    public String borrarUsuario(@PathVariable Long id) {

        // .existsById(): Verifica si el ID existe antes de intentar borrarlo.
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Usuario con ID: " + id + " ha sido eliminado correctamente.";
        } else {
            return "Error: No se encontró el usuario con ID: " + id;
        }
    }

    @GetMapping("/version")
    public String getVersion() {
        return "Esta es la nueva version 0.0.1 :)";
    }

    /*
    Con este pruebo el post

    {
        "nombre": "Carlos Ek",
    "numCuenta": 12345678,
        "numTelefono": 9999682992,
        "direcciones": [
            {
                "calle": "51 Avila C",
                "ciudad": "Merida"
            },
            {
                "calle": "Circuito Colonias",
                "ciudad": "Merida"
            }
        ]
    }


     */
}
