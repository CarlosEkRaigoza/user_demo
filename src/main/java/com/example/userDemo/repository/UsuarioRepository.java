package com.example.userDemo.repository;

import com.example.userDemo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
Le dice a Spring: "Esta pieza de código es la encargada de hablar con la Base de Datos".
Spring la detectará y creará una instancia (un objeto) en memoria automáticamente.
De ahí es el Autowired que hay en el controller
*/

@Repository

// NO es una "class". Es una "interface".
// Significa que solo definimos "el qué" (el contrato), y Spring se encarga del "cómo" (el código real).

/*
extends JpaRepository<Usuario, Long>:
aquí entra la Herencia:
Al extender de 'JpaRepository', heredamos automáticamente métodos para guardar, buscar y borrar.
<Usuario, Long> significa:
1. Usuario: Esta caja maneja datos de la tabla de 'Usuario'.
2. Long: El ID de esos usuarios es de tipo número largo (Long).
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /*
    JpaRepository ya tiene escritos los métodos:
    // .save()
    // .findAll()
    // .findById()
    // .deleteById()
    */

}