package samantha.com.actividad5.repository

import org.springframework.data.jpa.repository.JpaRepository
import samantha.com.actividad5.model.EstadoPedido
import samantha.com.actividad5.model.Pedido

interface PedidoJpaRepository : JpaRepository<Pedido, Long> {
    fun existsByClienteAndEstado(cliente: String, estado: EstadoPedido): Boolean
    fun findByCodigoPedido(codigoPedido: String): List<Pedido>
}