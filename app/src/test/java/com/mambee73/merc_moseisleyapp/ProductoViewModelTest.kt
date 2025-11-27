import com.mambee73.merc_moseisleyapp.model.Producto
import com.mambee73.merc_moseisleyapp.ui.viewmodels.ProductoViewModel
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ProductoViewModelTest : StringSpec({

    "agregarProducto debe asignar un ID único" {
        val vm = ProductoViewModel()
        val nuevo = Producto(0, "Test", "Desc", 100.0, "Ropa")

        vm.agregarProducto(nuevo)

        vm.productos.last().id shouldBe 7 // porque ya hay 6 iniciales
    }
})
