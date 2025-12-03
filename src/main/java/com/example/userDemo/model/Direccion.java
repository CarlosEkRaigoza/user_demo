package com.example.userDemo.model;

import jakarta.persistence.*;

// Importación para evitar el bucle infinito del JSON
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "direcciones")

public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String calle;
    private String ciudad;

    // @ManyToOne: Relación "Muchos a Uno". Muchas calles -> un usuario.
    @ManyToOne



    /* Le dice a la db: "En la tabla 'direcciones', crea una columna llamada 'usuario_id'".
    Ahí es donde se guardará el número (ID) del dueño de la casa.
    */
    @JoinColumn(name = "usuario_id")


    /*
    Cuando Postman pida los atributos del objeto Dirección, Spring intentará mostrar al Usuario dueño.
    Pero el Usuario tiene Direcciones, que tienen Usuario, que tiene Direcciones... (Bucle Infinito pues).
    Esto dice: "Corta aquí. No incluyas al usuario cuando imprimas la dirección en JSON".
     */
    @JsonIgnore
    private Usuario usuario;



    public Direccion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setciudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
