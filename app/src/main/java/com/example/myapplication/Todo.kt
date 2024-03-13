package com.example.myapplication


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String
) : Parcelable {

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Todo> {
            override fun createFromParcel(parcel: Parcel): Todo {
                return Todo(parcel.readInt(), parcel.readString() ?: "")
            }
            override fun newArray(size: Int): Array<Todo?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }
}
