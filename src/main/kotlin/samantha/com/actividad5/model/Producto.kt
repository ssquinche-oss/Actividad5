package samantha.com.actividad5.model

import jakarta.persistence.*

@Entity
@Table(name = "productos")
class Producto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    var nombre: String = "",

    var descripcion: String? = null,

    @Column(nullable = false)
    var stock: Int = 0
)