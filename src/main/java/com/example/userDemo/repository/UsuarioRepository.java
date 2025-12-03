package com.example.userDemo.repository;

import com.example.userDemo.model.Direccion;
import com.example.userDemo.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Usuario guardar(Usuario usuario) {
        if (usuario.getId() == null) {
            entityManager.persist(usuario);
        } else {
            entityManager.merge(usuario);
        }

        if (usuario.getDirecciones() != null) {
            for (Direccion direccionTem : usuario.getDirecciones()) {
                direccionTem.setUsuario(usuario);

                if (direccionTem.getId() == null) {
                    entityManager.persist(direccionTem);
                } else {
                    entityManager.merge(direccionTem);
                }
            }
        }

        return usuario;
    }


    public List<Usuario> buscarTodos() {
        String sql = "SELECT u FROM Usuario u";
        return entityManager.createQuery(sql, Usuario.class).getResultList();
    }

    @Transactional
    public void borrar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);

        if (usuario != null) {
            entityManager.remove(usuario);
        }
    }

    public Usuario buscarPorId(Long id) {
        return entityManager.find(Usuario.class, id);
    }
}