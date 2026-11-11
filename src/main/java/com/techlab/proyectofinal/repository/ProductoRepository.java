package com.techlab.proyectofinal.repository;

import com.techlab.proyectofinal.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // ¡Listo! Al extender de JpaRepository ya tenés métodos automáticos como:
    // .save(), .findAll(), .findById(), .deleteById(), etc.
}