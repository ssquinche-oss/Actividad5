package samantha.com.actividad5.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import samantha.com.actividad5.dto.*
import samantha.com.actividad5.service.PedidoService

@RestController
@RequestMapping("/pedidos")
class PedidoController(
    private val servicio: PedidoService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun crear(@RequestBody request: CrearPedidoRequest): PedidoCreadoResponse =
        servicio.crear(request)

    @PatchMapping("/{codigo}/enviar")
    fun enviar(@PathVariable codigo: String): PedidoResponse =
        servicio.enviar(codigo)

    @PatchMapping("/{codigo}/recibir")
    fun recibir(
        @PathVariable codigo: String,
        @RequestBody request: RecibirRequest
    ): PedidoResponse = servicio.recibir(codigo, request.codigoVerificacion)
}