package app.frantic.notepad.model

interface OnItemClickListener {
    fun OnItemClick(note: Note)

    fun OnItemLongClick(note: Note):Boolean
}