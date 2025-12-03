package com.example.userDemo.model;

import jakarta.persistence.*;

import java.util.List;

//Aquí transformamos esta clase .java en una Tabla de Base de Datos.
@Entity

//Aqui obligamos a que la tabla en la db se llame usuarios
@Table(name= "usuarios")


public class Usuario {

    //este @Id indica que este campo (Long id) es la Llave Primaria (el DNI del registro)
    @Id

    //el @GemeratedValue le dice a la db que genere el número ella misma (Auto-incremental).
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY: Estrategia simple (1, 2, 3...).
    private Long id;

    private String nombre;
    private Long numCuenta;
    private Long numTelefono;


    /* @OneToMany: Relación "Uno a Muchos". Un Usuario -> Muchas Direcciones.
     mappedBy = "usuario": Dice "No crees una tabla intermedia, ve a la clase Direccion
     y busca la variable 'usuario' para unirte".
     cascade = ALL: Si borro al usuario, borra sus direcciones automáticamente
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Direccion> direcciones;



    // OBLIGATORIO: JPA necesita esto para crear el objeto antes de rellenarlo.
    public Usuario() {
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

    public Long getNumCuenta(){
        return numCuenta;
    }

    public void setnumCuenta(Long numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Long getnumTelefono(){
        return numTelefono;
    }

    public void setnumTelefono(Long numTelefono) {
        this.numTelefono = numTelefono;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
}
