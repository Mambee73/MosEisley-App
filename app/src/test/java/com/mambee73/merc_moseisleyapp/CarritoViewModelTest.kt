import com.mambee73.merc_moseisleyapp.model.Producto
import com.mambee73.merc_moseisleyapp.ui.viewmodel.CarritoViewModel
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CarritoViewModelTest : StringSpec({

    "agregarAlCarrito debe añadir producto" {
        val carritoVM = CarritoViewModel()
        val producto = Producto(1, "Poncho", "Desc", 150.0, "Ropa")

        carritoVM.agregarAlCarrito(producto)

        carritoVM.carrito.value.size shouldBe 1
        carritoVM.carrito.value.first().nombre shouldBe "Poncho"
    }

    "quitarDelCarrito debe eliminar producto" {
        val carritoVM = CarritoViewModel()
        val producto = Producto(1, "Poncho", "Desc", 150.0, "Ropa")

        carritoVM.agregarAlCarrito(producto)
        carritoVM.quitarDelCarrito(producto)

        carritoVM.carrito.value.isEmpty() shouldBe true
    }

    "vaciarCarrito debe limpiar todos los productos" {
        val carritoVM = CarritoViewModel()
        carritoVM.agregarAlCarrito(Producto(1, "Poncho", "Desc", 150.0, "Ropa"))
        carritoVM.agregarAlCarrito(Producto(2, "Sable", "Desc", 300.0, "Armas"))

        carritoVM.vaciarCarrito()

        carritoVM.carrito.value.isEmpty() shouldBe true
    }

    "calcularTotal debe sumar precios correctamente" {
        val carritoVM = CarritoViewModel()
        carritoVM.agregarAlCarrito(Producto(1, "Poncho", "Desc", 150.0, "Ropa"))
        carritoVM.agregarAlCarrito(Producto(2, "Sable", "Desc", 300.0, "Armas"))

        carritoVM.calcularTotal() shouldBe 450.0
    }
})
