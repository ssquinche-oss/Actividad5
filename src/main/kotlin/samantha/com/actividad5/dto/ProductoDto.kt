package samantha.com.actividad5.dto

import samantha.com.actividad5.model.EstadoPedido

data class ItemRequest(
    val producto: String,
    val cantidad: Int
)

data class CrearPedidoRequest(
    val cliente: String,
    val items: List<ItemRequest>
)

data class RecibirRequest(
    val codigoVerificacion: String
)

data class PedidoCreadoResponse(
    val codigoPedido: String,
    val codigoVerificacion: String
)

data class PedidoResponse(
    val codigoPedido: String,
    val cliente: String,
    val estado: EstadoPedido,
    val items: List<ItemRequest>
)