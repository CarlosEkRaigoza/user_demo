package com.example.userDemo.repository;

import com.example.userDemo.model.Direccion;
import com.example.userDemo.model.Usuario;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    private final DataSource dataSource;

    public UsuarioRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Usuario guardar(Usuario usuario) {

        if (usuario.getId() == null) {
            insertar(usuario);
        } else {
            actualizar(usuario);
        }

        if (usuario.getDirecciones() != null && !usuario.getDirecciones().isEmpty()) {
            guardarDirecciones(usuario);
        }

        return usuario;
    }

    private Usuario insertar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre, num_cuenta, num_telefono) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement prestat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            prestat.setString(1, usuario.getNombre());
            prestat.setObject(2, usuario.getNumCuenta());
            prestat.setObject(3, usuario.getNumTelefono());

            prestat.executeUpdate();

            try (ResultSet generatedKeys = prestat.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar usuario", e);
        }
        return usuario;
    }

    private Usuario actualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre = ?, num_cuenta = ?, num_telefono = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement prestat = connection.prepareStatement(sql)) {

            prestat.setString(1, usuario.getNombre());
            prestat.setObject(2, usuario.getNumCuenta());
            prestat.setObject(3, usuario.getNumTelefono());
            prestat.setLong(4, usuario.getId());

            prestat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario", e);
        }
        return usuario;
    }

    private void guardarDirecciones(Usuario usuario) {
        String sql = "INSERT INTO direccion (calle, ciudad, usuario_id) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement prestat = connection.prepareStatement(sql)) {

            for (Direccion direccion : usuario.getDirecciones()) {

                if (direccion.getId() == null) {
                    prestat.setString(1, direccion.getCalle());
                    prestat.setString(2, direccion.getCiudad());
                    prestat.setLong(3, usuario.getId());
                    prestat.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar direcciones", e);
        }
    }

    public List<Usuario> buscarTodos() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> lista = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statem = connection.createStatement();
             ResultSet resul = statem.executeQuery(sql)) {

            while (resul.next()) {

                Usuario usuario = new Usuario();
                usuario.setId(resul.getLong("id"));
                usuario.setNombre(resul.getString("nombre"));
                usuario.setNumCuenta(resul.getLong("num_cuenta"));
                usuario.setNumTelefono(resul.getLong("num_telefono"));

                String sqlDir = "SELECT * FROM direccion WHERE usuario_id = ?";
                try (PreparedStatement prestatDir = connection.prepareStatement(sqlDir)) {
                    prestatDir.setLong(1, usuario.getId());
                    try (ResultSet rsDir = prestatDir.executeQuery()) {
                        while (rsDir.next()) {
                            Direccion direccion = new Direccion();
                            direccion.setId(rsDir.getLong("id"));
                            direccion.setCalle(rsDir.getString("calle"));
                            direccion.setCiudad(rsDir.getString("ciudad"));
                            direccion.setUsuario(usuario);
                            usuario.getDirecciones().add(direccion);
                        }
                    }
                }
                lista.add(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuarios", e);
        }
        return lista;
    }

    public Usuario buscarPorId(Long id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        Usuario usuario = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement prestat = connection.prepareStatement(sql)) {

            prestat.setLong(1, id);

            try (ResultSet resul = prestat.executeQuery()) {
                if (resul.next()) {
                    usuario = new Usuario();
                    usuario.setId(resul.getLong("id"));
                    usuario.setNombre(resul.getString("nombre"));
                    usuario.setNumCuenta(resul.getLong("num_cuenta"));
                    usuario.setNumTelefono(resul.getLong("num_telefono"));

                    String sqlDir = "SELECT * FROM direccion WHERE usuario_id = ?";
                    try (PreparedStatement prestaDir = connection.prepareStatement(sqlDir)) {
                        prestaDir.setLong(1, usuario.getId());
                        try (ResultSet resulDir = prestaDir.executeQuery()) {
                            while (resulDir.next()) {
                                Direccion direccion = new Direccion();
                                direccion.setId(resulDir.getLong("id"));
                                direccion.setCalle(resulDir.getString("calle"));
                                direccion.setCiudad(resulDir.getString("ciudad"));
                                direccion.setUsuario(usuario);
                                usuario.getDirecciones().add(direccion);
                            }
                        }
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error buscando ID: " + id, e);
        }
        return usuario;
    }

    public void borrar(Long id) {

        String sqlHijos = "DELETE FROM direccion WHERE usuario_id = ?";
        String sqlPadre = "DELETE FROM usuario WHERE id = ?";

        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement psHijos = connection.prepareStatement(sqlHijos)) {
                psHijos.setLong(1, id);
                psHijos.executeUpdate();
            }

            try (PreparedStatement psPadre = connection.prepareStatement(sqlPadre)) {
                psPadre.setLong(1, id);
                psPadre.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al borrar usuario", e);
        }
    }
}