package app.frantic.notepad.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import app.frantic.notepad.model.Note

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Query("SELECT * FROM NOTES")
    fun getAllNotes():List<Note>

    @Query("SELECT * FROM NOTES")
    fun getAllNotesLive(): LiveData<List<Note>>
}