package samantha.com.actividad5.exception

class ProductoNoExisteException(nombre: String) :
    RuntimeException("El producto '$nombre' no existe")

class PedidoNoExisteException(codigo: String) :
    RuntimeException("El pedido '$codigo' no existe")

class StockInsuficienteException(nombre: String, disponible: Int, solicitado: Int) :
    RuntimeException("Stock insuficiente de '$nombre': disponible $disponible, solicitado $solicitado")

class ClienteConPedidoPendienteException(cliente: String) :
    RuntimeException("El cliente '$cliente' ya tiene un pedido PENDIENTE")

class EstadoInvalidoException(mensaje: String) : RuntimeException(mensaje)

class CodigoVerificacionIncorrectoException :
    RuntimeException("El código de verificación es incorrecto")