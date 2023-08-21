package bwf.idat.topitopapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bwf.idat.topitopapp.dao.ProductDao
import bwf.idat.topitopapp.model.Producto

@Database(entities = [Producto::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao


}