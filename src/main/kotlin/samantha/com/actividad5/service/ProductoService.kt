package samantha.com.actividad5.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import samantha.com.actividad5.dto.CrearProductoRequest
import samantha.com.actividad5.model.Producto
import samantha.com.actividad5.repository.ProductoRepository

@Service
class ProductoService(
    private val productos: ProductoRepository
) {
    @Transactional
    fun crear(request: CrearProductoRequest): Producto =
        productos.save(
            Producto(
                nombre = request.nombre,
                descripcion = request.descripcion,
                stock = request.stock
            )
        )
}