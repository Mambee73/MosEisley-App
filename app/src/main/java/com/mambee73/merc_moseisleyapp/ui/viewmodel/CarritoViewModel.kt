package com.mambee73.merc_moseisleyapp.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.mambee73.merc_moseisleyapp.model.Producto

class CarritoViewModel : ViewModel() {
    val carrito = mutableStateListOf<Producto>()

    fun agregarAlCarrito(producto: Producto) {
        carrito.add(producto)
    }

    fun quitarDelCarrito(producto: Producto) {
        carrito.remove(producto)
    }

    fun calcularTotal(): Double {
        return carrito.sumOf { it.precio }
    }

    fun vaciarCarrito() {
        carrito.clear()
    }
}
