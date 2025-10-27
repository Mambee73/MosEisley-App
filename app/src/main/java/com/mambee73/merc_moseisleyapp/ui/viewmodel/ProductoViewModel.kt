package com.mambee73.merc_moseisleyapp.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.mambee73.merc_moseisleyapp.model.Producto

class ProductoViewModel : ViewModel() {
    val productos = mutableStateListOf<Producto>()

    init {
        cargarProductosIniciales()
    }

    private fun cargarProductosIniciales() {
        productos.addAll(
            listOf(
                // Aquí pegas los productos por categoría que te mostré antes
                Producto(
                    nombre = "Poncho estilo Tatooine",
                    descripcion = "Tela rústica con protección solar, usado por comerciantes del Borde Exterior.",
                    precio = 150.0,
                    categoria = "Ropa"
                ),
                Producto(
                    nombre = "Revista 'Holo-Cultura Galáctica'",
                    descripcion = "Edición mensual con relatos del Núcleo y reseñas de cómics interplanetarios.",
                    precio = 45.0,
                    categoria = "Libros/Cómics/Revistas"
                ),
                Producto(
                    nombre = "Gadget de rastreo orbital",
                    descripcion = "Dispositivo real con GPS modificado para simulaciones galácticas.",
                    precio = 320.0,
                    categoria = "Artículos Tecnológicos"
                ),
                Producto(
                    nombre = "Set de dados de Sabacc sellado",
                    descripcion = "Juego de mesa galáctico, nuevo en caja con holograma intacto.",
                    precio = 90.0,
                    categoria = "Cosas Nuevas/Cerradas"
                ),
                Producto(
                    nombre = "Cantimplora de explorador de Endor",
                    descripcion = "Objeto usado en simulaciones de terreno, con marcas de uso auténticas.",
                    precio = 60.0,
                    categoria = "Artículos de Segunda Mano/Curiosos"
                ),
                Producto(
                    nombre = "Holo-Juego 'Batalla de Scarif'",
                    descripcion = "Juego táctico multijugador con escenarios 3D y modo historia desbloqueable.",
                    precio = 220.0,
                    categoria = "Videojuegos/Holo-Juegos"
                )
            )
        )
    }

    fun agregarProducto(producto: Producto) {
        productos.add(producto)
    }
}


