package samantha.com.actividad5.model

import jakarta.persistence.*

@Entity
@Table(name = "pedidos")
class Pedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "codigo_pedido", nullable = false)
    val codigoPedido: String = "",

    @Column(nullable = false)
    val cliente: String = "",

    @Column(nullable = false)
    val producto: String = "",

    @Column(nullable = false)
    val cantidad: Int = 0,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var estado: EstadoPedido = EstadoPedido.PENDIENTE,

    @Column(name = "codigo_verificacion", nullable = false)
    val codigoVerificacion: String = ""
)