package com.example.userDemo;

import com.example.userDemo.controller.UsuarioController;
import com.example.userDemo.model.Usuario;
import com.example.userDemo.repository.UsuarioRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class UsuarioMockTest {

    @Test
    void testBuscarTodosConMock() {
        System.out.println("[-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-]");
        System.out.println("Buscar con Mocks");

        UsuarioRepository repositorioFalso = Mockito.mock(UsuarioRepository.class);
        UsuarioController controller = new UsuarioController(repositorioFalso);

        List<Usuario> listaFalsa = new ArrayList<>();
        Usuario usuarioFalso = new Usuario();
        usuarioFalso.setId(1L);
        usuarioFalso.setNombre("Usuario Falso");
        listaFalsa.add(usuarioFalso);

        Mockito.when(repositorioFalso.buscarTodos()).thenReturn(listaFalsa);

        List<Usuario> resultado = controller.obtenerTodos();

        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("Usuario Falso", resultado.get(0).getNombre());
        Mockito.verify(repositorioFalso, Mockito.times(1)).buscarTodos();

        System.out.println("Test Mock buscarTodos Exitoso.");
        System.out.println("[-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-]");
    }

    @Test
    void testBorrarConMock() {
        System.out.println("[-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-]");
        System.out.println("Delete con Mocks");

        UsuarioRepository repositorioFalso = Mockito.mock(UsuarioRepository.class);
        UsuarioController controller = new UsuarioController(repositorioFalso);
        Long id = 500L;

        controller.borrarUsuario(id);

        Mockito.verify(repositorioFalso, Mockito.times(1)).borrar(id);
        System.out.println("Test Mock Delete Exitoso.");
        System.out.println("[-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-]");
    }

    @Test
    void testGuardarConMock() {
        System.out.println("[-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-]");
        System.out.println("Save con Mocks)");

        UsuarioRepository repositorioFalso = Mockito.mock(UsuarioRepository.class);
        UsuarioController controller = new UsuarioController(repositorioFalso);

        Usuario entradaFalsa = new Usuario();
        entradaFalsa.setNombre("NuevoUsuario");

        Usuario salidaFalsa = new Usuario();
        salidaFalsa.setId(99L);
        salidaFalsa.setNombre("NuevoUsuario");

        Mockito.when(repositorioFalso.guardar(entradaFalsa)).thenReturn(salidaFalsa);

        Usuario resultado = controller.crearUsuario(entradaFalsa);

        if(resultado.getId() == 99L) {
            System.out.println("ID Generado correctamente por el mock.");
        } else {
            Assertions.fail("Fallo al simular guardado");
        }
        System.out.println("[-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-][-]");
    }
}