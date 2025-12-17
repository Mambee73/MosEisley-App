package com.mambee73.merc_moseisleyapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mambee73.merc_moseisleyapp.model.Producto
import com.mambee73.merc_moseisleyapp.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductoViewModel(
    private val repository: ProductoRepository = ProductoRepository()
) : ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    fun fetchProductos() {
        viewModelScope.launch {
            try {
                val response = repository.getProductos()
                if (response.isSuccessful) {
                    _productos.value = response.body() ?: emptyList()
                    Log.d("API", "Productos recibidos: ${response.body()}")
                } else {
                    Log.e("API", "Error al obtener productos: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Excepción al obtener productos", e)
                _productos.value = emptyList()
            }
        }
    }

    fun addProducto(producto: Producto) {
        viewModelScope.launch {
            try {
                val response = repository.postProducto(producto)
                if (response.isSuccessful) {
                    Log.d("API", "Producto agregado: ${response.body()}")
                    fetchProductos()
                } else {
                    Log.e("API", "Error al agregar producto: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Excepción al agregar producto", e)
            }
        }
    }
}
