package bwf.idat.topitopapp.utils

import android.content.Context
import androidx.room.Room
import bwf.idat.topitopapp.room.AppDatabase

object DatabaseUtils {

    private lateinit var room: AppDatabase

    fun initDatabase(context: Context) {
        room = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "productos").fallbackToDestructiveMigration().build()
    }

    fun getDatabase(): AppDatabase {
        return room
    }
}