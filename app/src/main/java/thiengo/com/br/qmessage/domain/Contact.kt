package thiengo.com.br.qmessage.domain

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.os.Parcel
import android.os.Parcelable

class Contact(
        val id: ObservableInt,
        val image: ObservableInt,
        val name: ObservableField<String>,
        var lastMessage: LastMessage,
        var newMessages: ObservableInt
    ) : Parcelable {

    constructor( source: Parcel ) : this(
        source.readParcelable<ObservableInt>(ObservableInt::class.java.classLoader),
        source.readParcelable<ObservableInt>(ObservableInt::class.java.classLoader),
        source.readSerializable() as ObservableField<String>,
        source.readParcelable<LastMessage>(LastMessage::class.java.classLoader),
        source.readParcelable<ObservableInt>(ObservableInt::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel( dest: Parcel, flags: Int ) = with( dest ) {
        writeParcelable( id, 0 )
        writeParcelable( image, 0 )
        writeSerializable( name )
        writeParcelable( lastMessage, 0 )
        writeParcelable( newMessages, 0 )
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Contact> = object : Parcelable.Creator<Contact> {
            override fun createFromParcel( source: Parcel ): Contact =
                Contact( source )
            override fun newArray( size: Int ): Array<Contact?> =
                arrayOfNulls( size )
        }
    }
}
