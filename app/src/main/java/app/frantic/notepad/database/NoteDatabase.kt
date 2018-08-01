package app.frantic.notepad.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import app.frantic.notepad.model.Note

@Database(entities = [(Note::class)], version = 1,exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDao

    companion object {
        private var INSTANCE: NoteDatabase? = null
        fun getDataBase(context: Context): NoteDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "notes-db")
                        .allowMainThreadQueries().build()
            }
            return INSTANCE as NoteDatabase
        }
    }
}