package samantha.com.actividad5.dto

data class CrearProductoRequest(
    val nombre: String,
    val descripcion: String? = null,
    val stock: Int
)