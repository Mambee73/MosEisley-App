package com.mambee73.merc_moseisleyapp.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.mambee73.merc_moseisleyapp.model.Producto

class ProductoViewModel : ViewModel() {
    val productos = mutableStateListOf<Producto>()

    fun agregarProducto(producto: Producto) {
        productos.add(producto)
    }
}

