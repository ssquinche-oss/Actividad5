package samantha.com.actividad5.repository

import org.springframework.stereotype.Component
import samantha.com.actividad5.model.EstadoPedido
import samantha.com.actividad5.model.Pedido

@Component
class PedidoJpaAdapter(
    private val jpa: PedidoJpaRepository
) : PedidoRepositoryPort {

    override fun existePedidoPendiente(cliente: String): Boolean =
        jpa.existsByClienteAndEstado(cliente, EstadoPedido.PENDIENTE)

    override fun guardar(pedido: Pedido): Pedido = jpa.save(pedido)

    override fun buscarPorCodigo(codigoPedido: String): List<Pedido> =
        jpa.findByCodigoPedido(codigoPedido)
}