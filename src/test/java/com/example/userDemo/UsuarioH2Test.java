package com.example.userDemo;

import com.example.userDemo.model.Direccion;
import com.example.userDemo.model.Usuario;
import com.example.userDemo.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class UsuarioH2Test {

    @Autowired
    private UsuarioRepository repositorio;

    @Test
    void testGuardarUsuarioYDirecciones() {
        System.out.println("[-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-]");
        System.out.println("probando test para crear/guardar");

        Usuario usuario = new Usuario();
        usuario.setNombre("CarlosRob");
        usuario.setNumCuenta(1001L);
        usuario.setNumTelefono(555999L);

        Direccion direccion = new Direccion();
        direccion.setCalle("Circuito Col");
        direccion.setCiudad("Merida");

        usuario.agregarDireccion(direccion);

        System.out.println("ya metimos la direccion al usuario");

        repositorio.guardar(usuario);
        System.out.println("ahora metimos el usuario completo al repositorio");

        System.out.println("verificamos con id");
        if (usuario.getId() == null) {
            Assertions.fail("El ID del usuario no debería ser nulo");
        }

        Usuario usuarioEncontrado = repositorio.buscarPorId(usuario.getId());

        Assertions.assertEquals("CarlosRob", usuarioEncontrado.getNombre());
        Assertions.assertEquals("Merida", usuarioEncontrado.getDirecciones().get(0).getCiudad());

        System.out.println("si se guardó");
        System.out.println("[-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-]");
    }

    @Test
    void testBorrarUsuario() {
        System.out.println("[-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-]");
        System.out.println("prueba para borrar: ");

        Usuario usuario = new Usuario();
        usuario.setNombre("Carlos Roberto");
        usuario.setNumCuenta(987654321L);
        usuario.setNumTelefono(5511223344L);

        Direccion direccion = new Direccion();
        direccion.setCalle("Circuito Col");
        direccion.setCiudad("Mérida");

        System.out.println("Relación P-H");
        usuario.agregarDireccion(direccion);

        System.out.println("Guardando usuario y su direccion");
        repositorio.guardar(usuario);

        Long idUsuario = usuario.getId();
        Long idDireccion = direccion.getId();

        System.out.println("id del usuario: " + idUsuario);
        System.out.println("id de Dirección: " + idDireccion);

        System.out.println("Aquí borramos");
        repositorio.borrar(idUsuario);

            Usuario usuarioBorrado = repositorio.buscarPorId(idUsuario);
            if (usuarioBorrado == null) {
                System.out.println("El usuario ya no existe.");
            } else {
                Assertions.fail("Algo no anda bien JAJAJAJ.");
            }
        System.out.println("[-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-]");
    }
}