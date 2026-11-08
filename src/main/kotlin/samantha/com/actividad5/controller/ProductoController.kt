package samantha.com.actividad5.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import samantha.com.actividad5.dto.CrearProductoRequest
import samantha.com.actividad5.model.Producto
import samantha.com.actividad5.service.ProductoService

@RestController
@RequestMapping("/productos")
class ProductoController(
    private val servicio: ProductoService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun crear(@RequestBody request: CrearProductoRequest): Producto =
        servicio.crear(request)
}