package com.techlab.proyectofinal.service;

import com.techlab.proyectofinal.exception.StockInsuficienteException;
import com.techlab.proyectofinal.model.LineaPedido;
import com.techlab.proyectofinal.model.Pedido;
import com.techlab.proyectofinal.model.Producto;
import com.techlab.proyectofinal.repository.PedidoRepository;
import com.techlab.proyectofinal.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // 1. Listar todos los pedidos
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // 2. Buscar un pedido por ID
    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    // 3. Crear un pedido validando y descontando stock
    @Transactional // Asegura que si algo falla, se revierte toda la operación (rollback)
    public Pedido crearPedido(Pedido pedido) {

        for (LineaPedido linea : pedido.getLineas()) {
            // Buscamos el producto real en la base de datos
            Producto producto = productoRepository.findById(linea.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + linea.getProducto().getId()));

            // Validamos si hay stock suficiente
            if (producto.getStock() < linea.getCantidad()) {
                throw new StockInsuficienteException(
                        "Stock insuficiente para el producto: " + producto.getNombre() +
                                ". Stock disponible: " + producto.getStock() +
                                ", solicitado: " + linea.getCantidad()
                );
            }

            // Descontamos el stock y lo actualizamos
            producto.setStock(producto.getStock() - linea.getCantidad());
            productoRepository.save(producto);

            // Asociamos el producto completo a la línea
            linea.setProducto(producto);
        }

        // Calculamos y seteamos el total antes de guardar
        pedido.setTotal(pedido.calcularTotal());

        return pedidoRepository.save(pedido);
    }
}