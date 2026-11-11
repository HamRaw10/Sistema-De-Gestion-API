package com.techlab.proyectofinal.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación Uno a Muchos: Un pedido tiene muchas líneas de pedido
    // cascade = CascadeType.ALL hace que si guardás el pedido, se guarden sus líneas solas
    // orphanRemoval = устраняет líneas huérfanas si se borran del pedido
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id") // Crea una FK en la tabla linea_pedido
    private List<LineaPedido> lineas = new ArrayList<>();

    private double total;

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedido> lineas) {
        this.lineas = lineas;
        this.total = calcularTotal();
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
        this.total = calcularTotal();
    }

    public double calcularTotal() {
        double sumaTotal = 0;
        for (LineaPedido linea : lineas) {
            sumaTotal += linea.calcularSubtotal();
        }
        return sumaTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}