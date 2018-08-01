package app.frantic.notepad.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.frantic.notepad.R
import app.frantic.notepad.model.Note
import app.frantic.notepad.model.OnItemClickListener
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter(var notes: List<Note>, val listener: OnItemClickListener) : RecyclerView.Adapter<NotesAdapter.MyHolder>() {
    private var listenerContact: OnItemClickListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var note = notes.get(position)
        holder.text.text = note.text.toString()
        holder.card.setOnClickListener {
            listener.OnItemClick(note)
        }
        holder.card.setOnLongClickListener {
            listener.OnItemLongClick(note)
        }    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text= itemView.text
        var card = itemView.cardView
        fun bind(note: Note,listener: OnItemClickListener){

        }

    }

    fun addNotes(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }
}