package com.example.userDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Direccion {

    private Long id;
    private String calle;
    private String ciudad;

    @JsonIgnore
    private Usuario usuario;

    public Direccion() {
    }

    public Direccion(String calle, String ciudad, Usuario usuario) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.usuario = usuario;
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

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}