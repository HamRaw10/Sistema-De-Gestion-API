package com.techlab.proyectofinal.service;

import com.techlab.proyectofinal.model.Producto;
import com.techlab.proyectofinal.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // 1. Listar todos los productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // 2. Buscar un producto por ID
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    // 3. Guardar o actualizar un producto
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // 4. Eliminar un producto por ID
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}