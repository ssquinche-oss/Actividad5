package samantha.com.actividad5.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ManejadorExcepciones {

    private fun respuesta(estado: HttpStatus, mensaje: String?): ResponseEntity<Map<String, Any?>> =
        ResponseEntity.status(estado).body(
            mapOf("estado" to estado.value(), "mensaje" to mensaje)
        )

    @ExceptionHandler(ProductoNoExisteException::class, PedidoNoExisteException::class)
    fun noEncontrado(e: RuntimeException) = respuesta(HttpStatus.NOT_FOUND, e.message)

    @ExceptionHandler(
        StockInsuficienteException::class,
        ClienteConPedidoPendienteException::class,
        EstadoInvalidoException::class
    )
    fun conflicto(e: RuntimeException) = respuesta(HttpStatus.CONFLICT, e.message)

    @ExceptionHandler(CodigoVerificacionIncorrectoException::class)
    fun codigoIncorrecto(e: RuntimeException) = respuesta(HttpStatus.BAD_REQUEST, e.message)
}