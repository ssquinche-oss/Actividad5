package samantha.com.actividad5.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import samantha.com.actividad5.dto.*
import samantha.com.actividad5.exception.*
import samantha.com.actividad5.model.EstadoPedido
import samantha.com.actividad5.model.Pedido
import samantha.com.actividad5.repository.PedidoRepositoryPort
import samantha.com.actividad5.repository.ProductoRepository
import java.util.UUID
import kotlin.random.Random

@Service
class PedidoService(
    private val pedidos: PedidoRepositoryPort,
    private val productos: ProductoRepository
) {

    @Transactional
    fun crear(request: CrearPedidoRequest): PedidoCreadoResponse {
        // R1
        if (pedidos.existePedidoPendiente(request.cliente)) {
            throw ClienteConPedidoPendienteException(request.cliente)
        }

        // Si un producto se repite, se suman sus cantidades
        val pedidoPorProducto = request.items
            .groupBy({ it.producto }, { it.cantidad })
            .mapValues { it.value.sum() }

        // R3
        val encontrados = pedidoPorProducto.keys.associateWith { nombre ->
            productos.findByNombre(nombre) ?: throw ProductoNoExisteException(nombre)
        }

        // R2: se valida todo antes de descontar
        pedidoPorProducto.forEach { (nombre, cantidad) ->
            val producto = encontrados.getValue(nombre)
            if (producto.stock < cantidad) {
                throw StockInsuficienteException(nombre, producto.stock, cantidad)
            }
        }

        val codigoPedido = "PED-" + UUID.randomUUID().toString().take(8).uppercase()
        // R7
        val codigoVerificacion = Random.nextInt(100000, 1000000).toString()

        // R6
        pedidoPorProducto.forEach { (nombre, cantidad) ->
            val producto = encontrados.getValue(nombre)
            producto.stock -= cantidad
            productos.save(producto)
            pedidos.guardar(
                Pedido(
                    codigoPedido = codigoPedido,
                    cliente = request.cliente,
                    producto = nombre,
                    cantidad = cantidad,
                    estado = EstadoPedido.PENDIENTE,
                    codigoVerificacion = codigoVerificacion
                )
            )
        }

        return PedidoCreadoResponse(codigoPedido, codigoVerificacion)
    }

    @Transactional
    fun enviar(codigoPedido: String): PedidoResponse {
        val filas = buscar(codigoPedido)
        // R4
        if (filas.first().estado != EstadoPedido.PENDIENTE) {
            throw EstadoInvalidoException("Solo un pedido PENDIENTE se puede enviar")
        }
        filas.forEach {
            it.estado = EstadoPedido.ENVIADO
            pedidos.guardar(it)
        }
        return respuesta(filas)
    }

    @Transactional
    fun recibir(codigoPedido: String, codigoVerificacion: String): PedidoResponse {
        val filas = buscar(codigoPedido)
        // R4
        if (filas.first().estado != EstadoPedido.ENVIADO) {
            throw EstadoInvalidoException("Solo un pedido ENVIADO se puede recibir")
        }
        // R5
        if (filas.first().codigoVerificacion != codigoVerificacion) {
            throw CodigoVerificacionIncorrectoException()
        }
        filas.forEach {
            it.estado = EstadoPedido.RECIBIDO
            pedidos.guardar(it)
        }
        return respuesta(filas)
    }

    private fun buscar(codigoPedido: String): List<Pedido> {
        val filas = pedidos.buscarPorCodigo(codigoPedido)
        if (filas.isEmpty()) throw PedidoNoExisteException(codigoPedido)
        return filas
    }

    private fun respuesta(filas: List<Pedido>): PedidoResponse =
        PedidoResponse(
            codigoPedido = filas.first().codigoPedido,
            cliente = filas.first().cliente,
            estado = filas.first().estado,
            items = filas.map { ItemRequest(it.producto, it.cantidad) }
        )
}