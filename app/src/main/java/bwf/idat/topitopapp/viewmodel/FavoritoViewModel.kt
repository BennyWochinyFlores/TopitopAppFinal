import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bwf.idat.topitopapp.model.Producto
import bwf.idat.topitopapp.utils.DatabaseUtils
import kotlinx.coroutines.launch

class FavoritoViewModel : ViewModel() {

    fun toggleFavoriteState(product: Producto) {
        val room = DatabaseUtils.getDatabase()

        viewModelScope.launch {
            val productId = room.productDao().getProductById(product.idMeal)
            productId.isFavorite = !productId.isFavorite
            room.productDao().actualizarProducto(productId)
        }
    }
}