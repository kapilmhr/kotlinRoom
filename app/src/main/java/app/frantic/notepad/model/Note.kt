package app.frantic.notepad.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "notes")
data class Note(@PrimaryKey val id : String = UUID.randomUUID().toString(),
                val text: String?):Serializable{

}