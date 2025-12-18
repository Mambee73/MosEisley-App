import com.mambee73.merc_moseisleyapp.ui.viewmodel.UsuarioViewModel
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class UsuarioViewModelTest : StringSpec({

    "login debe aceptar Usuario1 con clave mos123" {
        val vm = UsuarioViewModel()
        vm.login("Usuario1", "mos123") shouldBe true
    }

    "login debe rechazar credenciales incorrectas" {
        val vm = UsuarioViewModel()
        vm.login("otroUsuario", "claveErronea") shouldBe false
    }
})
