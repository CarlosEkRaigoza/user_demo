package com.example.userDemo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Long numCuenta;
    private Long numTelefono;

    @OneToMany(mappedBy = "usuario")
    private List<Direccion> direcciones = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nombre, Long numCuenta, Long numTelefono) {
        this.nombre = nombre;
        this.numCuenta = numCuenta;
        this.numTelefono = numTelefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(Long numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Long getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(Long numTelefono) {
        this.numTelefono = numTelefono;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
        if(this.direcciones != null) {
            for(Direccion dir : this.direcciones) {
                dir.setUsuario(this);
            }
        }
    }

    // Método opcional pero muy "estudiante aplicado":
    // Sirve para agregar una sola dirección a la lista existente
    public void agregarDireccion(Direccion direccion) {
        if(direccion != null) {
            this.direcciones.add(direccion);
            direccion.setUsuario(this);
        }
    }
}