package com.mambee73.merc_moseisleyapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mambee73.merc_moseisleyapp.model.Producto
import com.mambee73.merc_moseisleyapp.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel para manejar productos
// Se conecta al repositorio y expone el estado a la UI
class ProductoViewModel(
    private val repository: ProductoRepository = ProductoRepository()
) : ViewModel() {

    // Estado con la lista de productos (observable en Compose)
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    // Cargar productos desde el backend
    fun fetchProductos() {
        viewModelScope.launch {
            try {
                val lista = repository.getProductos()
                _productos.value = lista
            } catch (e: Exception) {
                // Si hay error, dejamos la lista vacía
                _productos.value = emptyList()
            }
        }
    }

    // Subir un producto nuevo al backend
    fun addProducto(producto: Producto) {
        viewModelScope.launch {
            try {
                val nuevo = repository.postProducto(producto)
                // Actualizamos la lista agregando el nuevo producto
                _productos.value = _productos.value + nuevo
            } catch (e: Exception) {
                // Si falla, no se actualiza la lista
            }
        }
    }
}
