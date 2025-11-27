package com.mambee73.merc_moseisleyapp.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.mambee73.merc_moseisleyapp.model.Producto

class ProductoViewModel : ViewModel() {
    val productos = mutableStateListOf<Producto>()
    private var nextId = 1

    init {
        cargarProductosIniciales()
    }

    private fun cargarProductosIniciales() {
        productos.addAll(
            listOf(
                Producto(
                    id = nextId++,
                    nombre = "Poncho estilo Tatooine",
                    descripcion = "Tela rústica con protección solar, usado por comerciantes del Borde Exterior.",
                    precio = 150.0,
                    categoria = "Ropa"
                ),
                Producto(
                    id = nextId++,
                    nombre = "Revista 'Holo-Cultura Galáctica'",
                    descripcion = "Edición mensual con relatos del Núcleo y reseñas de cómics interplanetarios.",
                    precio = 45.0,
                    categoria = "Libros/Cómics/Revistas"
                ),
                Producto(
                    id = nextId++,
                    nombre = "Gadget de rastreo orbital",
                    descripcion = "Dispositivo real con GPS modificado para simulaciones galácticas.",
                    precio = 320.0,
                    categoria = "Artículos Tecnológicos"
                ),
                Producto(
                    id = nextId++,
                    nombre = "Set de dados de Sabacc sellado",
                    descripcion = "Juego de mesa galáctico, nuevo en caja con holograma intacto.",
                    precio = 90.0,
                    categoria = "Cosas Nuevas/Cerradas"
                ),
                Producto(
                    id = nextId++,
                    nombre = "Cantimplora de explorador de Endor",
                    descripcion = "Objeto usado en simulaciones de terreno, con marcas de uso auténticas.",
                    precio = 60.0,
                    categoria = "Artículos de Segunda Mano/Curiosos"
                ),
                Producto(
                    id = nextId++,
                    nombre = "Holo-Juego 'Batalla de Scarif'",
                    descripcion = "Juego táctico multijugador con escenarios 3D y modo historia desbloqueable.",
                    precio = 220.0,
                    categoria = "Videojuegos/Holo-Juegos"
                )
            )
        )
    }

    // Agregar producto nuevo con ID automático
    fun agregarProducto(producto: Producto) {
        productos.add(producto.copy(id = nextId++))
    }

    // Buscar producto por ID
    fun obtenerProductoPorId(id: Int): Producto? =
        productos.firstOrNull { it.id == id }
}

