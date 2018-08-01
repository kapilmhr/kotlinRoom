package app.frantic.notepad.view_model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import app.frantic.notepad.model.Note
import app.frantic.notepad.database.NoteDatabase
import kotlinx.coroutines.experimental.async

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    var notesList: LiveData<List<Note>>
    private val noteDb : NoteDatabase

    init {
        noteDb = NoteDatabase.getDataBase(this.getApplication())
        notesList = noteDb.noteDao().getAllNotesLive()
    }

    fun getNoteList(): LiveData<List<Note>>{
        return notesList
    }

    fun addNote(note: Note){
        async {
            noteDb.noteDao().insertNote(note)
        }
    }

    fun deleteNote(note: Note){
        async {
            noteDb.noteDao().deleteNote(note)
        }
    }

    fun updateNote(note: Note){
        async {
            noteDb.noteDao().updateNote(note)
        }
    }

}