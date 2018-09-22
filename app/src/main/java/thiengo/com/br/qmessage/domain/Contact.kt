package thiengo.com.br.qmessage.domain

import android.os.Parcel
import android.os.Parcelable

class Contact(
        val id: Int,
        val image: Int,
        val name: String,
        var lastMessage: LastMessage,
        var newMessages: Int
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readParcelable<LastMessage>(LastMessage::class.java.classLoader),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeInt(image)
        writeString(name)
        writeParcelable(lastMessage, 0)
        writeInt(newMessages)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Contact> = object : Parcelable.Creator<Contact> {
            override fun createFromParcel(source: Parcel): Contact = Contact(source)
            override fun newArray(size: Int): Array<Contact?> = arrayOfNulls(size)
        }
    }
}