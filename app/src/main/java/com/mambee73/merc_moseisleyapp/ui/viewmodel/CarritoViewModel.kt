package com.mambee73.merc_moseisleyapp.ui.viewmodel //concentro la lógica de negocio

import androidx.lifecycle.ViewModel
import com.mambee73.merc_moseisleyapp.model.Producto //conecta a su repositorio y expone estados listos para la UI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// ViewModel para manejar el carrito de compras
class CarritoViewModel : ViewModel() { //controla el carrito

    // Lista de productos en el carrito como StateFlow
    private val _carrito = MutableStateFlow<List<Producto>>(emptyList())
    val carrito: StateFlow<List<Producto>> = _carrito

    // Agregar producto al carrito
    fun agregarAlCarrito(producto: Producto) {
        _carrito.value = _carrito.value + producto
    }

    // Quitar producto del carrito
    fun quitarDelCarrito(producto: Producto) {
        _carrito.value = _carrito.value - producto
    }

    // Calcular el total del carrito (sumando precios)
    fun calcularTotal(): Double {
        return _carrito.value.sumOf { it.precio }
    }

    // Vaciar el carrito (eliminar todos los productos)
    fun vaciarCarrito() {
        _carrito.value = emptyList()
    }
}

