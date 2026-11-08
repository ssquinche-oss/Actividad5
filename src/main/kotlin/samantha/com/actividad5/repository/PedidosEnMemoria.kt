package samantha.com.actividad5.repository

import samantha.com.actividad5.model.EstadoPedido
import samantha.com.actividad5.model.Pedido

class PedidosEnMemoria : PedidoRepositoryPort {

    private val filas = mutableListOf<Pedido>()

    override fun existePedidoPendiente(cliente: String): Boolean =
        filas.any { it.cliente == cliente && it.estado == EstadoPedido.PENDIENTE }

    override fun guardar(pedido: Pedido): Pedido {
        filas.add(pedido)
        return pedido
    }

    override fun buscarPorCodigo(codigoPedido: String): List<Pedido> =
        filas.filter { it.codigoPedido == codigoPedido }
}