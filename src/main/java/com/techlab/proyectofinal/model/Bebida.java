package com.techlab.proyectofinal.model;

import jakarta.persistence.Entity;

@Entity
public class Bebida extends Producto {

    private boolean esRetornable;
    private double litros;

    public Bebida() {
        super();
    }

    public Bebida(String nombre, double precio, int stock, boolean esRetornable, double litros) {
        super(nombre, precio, stock);
        this.esRetornable = esRetornable;
        this.litros = litros;
    }

    public boolean isEsRetornable() {
        return esRetornable;
    }

    public void setEsRetornable(boolean esRetornable) {
        this.esRetornable = esRetornable;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }
}