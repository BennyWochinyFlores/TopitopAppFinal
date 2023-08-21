package bwf.idat.topitopapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import bwf.idat.topitopapp.model.Producto

@Dao
interface ProductDao {
/*    @Query("SELECT * FROM productos")
    suspend fun getAllProducts(): LiveData<List<Producto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Producto>)*/

    @Query("SELECT * FROM productos")
    suspend fun getAllProducts(): List<Producto>

    @Insert
    suspend fun agregarUsuario(producto: Producto)

    @Update
    suspend fun actualizarProducto(producto: Producto)

    @Query("DELETE FROM productos")
    suspend fun borrarTodosLosProductos()

    @Query("SELECT * FROM productos where idMeal=:idMeal")
    suspend fun getProductById(idMeal: String): Producto
}