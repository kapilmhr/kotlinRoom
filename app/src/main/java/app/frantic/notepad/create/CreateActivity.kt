package app.frantic.notepad.create

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import app.frantic.notepad.R
import app.frantic.notepad.model.Note
import app.frantic.notepad.view_model.NotesViewModel
import kotlinx.android.synthetic.main.activity_create.*
import java.util.*

class CreateActivity : AppCompatActivity() {

    lateinit var editText : EditText
    private var viewModel: NotesViewModel? = null
    lateinit var note:Note
    var isUpdate:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(NotesViewModel::class.java)
        editText = edit_text
        isUpdate = intent.getBooleanExtra("UPDATE",false)
        if (isUpdate){
            note = intent.getSerializableExtra("NOTE") as Note
            editText.setText(note.text)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_accept,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
         when(item?.itemId){
            R.id.action_accept -> save()
            else -> super.onOptionsItemSelected(item)
        }

        return true
    }

    private fun save() {
        if (editText.text.length>0){
            if (isUpdate){
                viewModel!!.updateNote(Note(note.id,editText.text.toString()))
                finish()
            }else{
                viewModel!!.addNote(Note(UUID.randomUUID().toString(),editText.text.toString()))
                Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show()
                finish()
            }
        }else{
            Toast.makeText(applicationContext,"Please write a note",Toast.LENGTH_LONG).show()
        }
    }
}
