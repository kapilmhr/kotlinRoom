package app.frantic.notepad

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import app.frantic.notepad.adapter.NotesAdapter
import app.frantic.notepad.create.CreateActivity
import app.frantic.notepad.model.Note
import app.frantic.notepad.database.NoteDatabase
import app.frantic.notepad.view_model.NotesViewModel
import app.frantic.notepad.model.OnItemClickListener

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import java.util.Arrays.asList



class MainActivity : AppCompatActivity(),OnItemClickListener {


    var noteDatabase : NoteDatabase? = null
    private var viewModel: NotesViewModel? = null
    var adapter:NotesAdapter?=null
    var list = Arrays.asList("Edit","Delete")
    var cs = list.toTypedArray()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        noteDatabase = NoteDatabase.getDataBase(this)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(NotesViewModel::class.java)
        adapter = NotesAdapter(arrayListOf(),this)
        recycler.layoutManager = LinearLayoutManager(applicationContext)
        recycler.adapter = adapter
        viewModel!!.getNoteList().observe(this, Observer {notes ->
            adapter!!.addNotes(notes!!)
        })

        fab.setOnClickListener { view ->
            startActivity(Intent(this,CreateActivity::class.java))
        }
    }

    override fun OnItemLongClick(note: Note): Boolean {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Actions")
                .setCancelable(true)
                .setItems(cs, DialogInterface.OnClickListener { dialogInterface, i ->
                    if (i==0){
                        //edit
                        val i = Intent(this,CreateActivity::class.java)
                        i.putExtra("UPDATE",true);
                        i.putExtra("NOTE",note);
                        startActivity(i)
                    }else{
                        //delete
                        viewModel!!.deleteNote(note)
                    }
                })
        val alert = dialog.create()
        alert.show()

        return true
    }

    override fun OnItemClick(note: Note) {
        Toast.makeText(this,note.text,Toast.LENGTH_LONG).show()
    }

}
