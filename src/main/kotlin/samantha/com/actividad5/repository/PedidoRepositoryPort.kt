package samantha.com.actividad5.repository

import samantha.com.actividad5.model.Pedido

interface PedidoRepositoryPort {
    fun existePedidoPendiente(cliente: String): Boolean
    fun guardar(pedido: Pedido): Pedido
    fun buscarPorCodigo(codigoPedido: String): List<Pedido>
}