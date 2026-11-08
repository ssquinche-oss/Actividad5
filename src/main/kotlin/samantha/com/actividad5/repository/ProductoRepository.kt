package samantha.com.actividad5.repository

import org.springframework.data.jpa.repository.JpaRepository
import samantha.com.actividad5.model.Producto

interface ProductoRepository : JpaRepository<Producto, Long> {
    fun findByNombre(nombre: String): Producto?
}