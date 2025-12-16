package com.mambee73.merc_moseisleyapp.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.mambee73.merc_moseisleyapp.model.Producto

// ViewModel para manejar el carrito de compras
class CarritoViewModel : ViewModel() {
    // Lista de productos en el carrito
    val carrito = mutableStateListOf<Producto>()

    // Agregar producto al carrito
    fun agregarAlCarrito(producto: Producto) {
        carrito.add(producto)
    }

    // Quitar producto del carrito
    fun quitarDelCarrito(producto: Producto) {
        carrito.remove(producto)
    }

    // Calcular el total del carrito (sumando precios)
    fun calcularTotal(): Double {
        return carrito.sumOf { it.precio }
    }

    // Vaciar el carrito (eliminar todos los productos)
    fun vaciarCarrito() {
        carrito.clear()
    }
}
