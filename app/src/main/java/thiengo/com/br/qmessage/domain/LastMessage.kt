package thiengo.com.br.qmessage.domain

import android.content.Context
import android.databinding.ObservableField
import android.databinding.ObservableLong
import android.os.Parcel
import android.os.Parcelable
import thiengo.com.br.qmessage.R
import java.util.*

class LastMessage(
        var time: ObservableLong,
        var message: ObservableField<String> ) : Parcelable {

    fun lastMessageQuoted() =
        String.format( "\"%s\"", message.get() )

    /*
     * Método responsável por retornar o valor de
     * lastTimeAnswer em um formato humano, pois está
     * propriedade tem o valor em milissegundos.
     * */
    fun lastTimeAnswerFormatted( context: Context ): String {
        var howLong: Int
        val labelType: Int
        val lastTime = Calendar.getInstance()
        val timeNow = Calendar.getInstance()
        val label: String

        lastTime.timeInMillis = time.get()

        if( lastTime.get(Calendar.YEAR) != timeNow.get(Calendar.YEAR) ) {
            howLong = timeNow.get(Calendar.YEAR) - lastTime.get(Calendar.YEAR)
            labelType = Calendar.YEAR
        }
        else if( lastTime.get(Calendar.MONTH) != timeNow.get(Calendar.MONTH) ) {
            howLong = timeNow.get(Calendar.MONTH) - lastTime.get(Calendar.MONTH)
            labelType = Calendar.MONTH
        }
        else if( lastTime.get(Calendar.WEEK_OF_MONTH) != timeNow.get(Calendar.WEEK_OF_MONTH) ) {
            howLong = timeNow.get(Calendar.WEEK_OF_MONTH) - lastTime.get(Calendar.WEEK_OF_MONTH)
            labelType = Calendar.WEEK_OF_MONTH
        }
        else if( lastTime.get(Calendar.DAY_OF_MONTH) != timeNow.get(Calendar.DAY_OF_MONTH) ) {
            howLong = timeNow.get(Calendar.DAY_OF_MONTH) - lastTime.get(Calendar.DAY_OF_MONTH)
            labelType = Calendar.DAY_OF_MONTH
        }
        else if( lastTime.get(Calendar.HOUR_OF_DAY) != timeNow.get(Calendar.HOUR_OF_DAY) ) {
            howLong = timeNow.get(Calendar.HOUR_OF_DAY) - lastTime.get(Calendar.HOUR_OF_DAY)
            labelType = Calendar.HOUR_OF_DAY
        }
        else if( lastTime.get(Calendar.MINUTE) != timeNow.get(Calendar.MINUTE) ) {
            howLong = timeNow.get(Calendar.MINUTE) - lastTime.get(Calendar.MINUTE)
            labelType = Calendar.MINUTE
        }
        else {
            howLong = timeNow.get(Calendar.SECOND) - lastTime.get(Calendar.SECOND)
            howLong = if( howLong == 0 ) 1 else howLong
            labelType = Calendar.SECOND
        }

        label = getLastTimeAnswerLabel( context, labelType, howLong )

        return String
            .format(
                "%s %d %s",
                context.getString( R.string.a_time ),
                howLong,
                label
            )
    }

    /*
     * Método responsável por retornar o rótulo correto
     * de acordo com o tempo já corrido desde a última
     * mensagem.
     * */
    private fun getLastTimeAnswerLabel(
        context: Context,
        type: Int,
        howLong: Int ): String {

        val labelId = when ( type ) {
            Calendar.YEAR -> if( howLong > 1 ) R.string.years else R.string.years
            Calendar.MONTH -> if( howLong > 1 ) R.string.months else R.string.month
            Calendar.WEEK_OF_MONTH -> if( howLong > 1 ) R.string.weeks else R.string.week
            Calendar.DAY_OF_MONTH -> if( howLong > 1 ) R.string.days else R.string.day
            Calendar.HOUR_OF_DAY -> if( howLong > 1 ) R.string.hours else R.string.hour
            Calendar.MINUTE -> if( howLong > 1 ) R.string.minutes else R.string.minute
            else -> if( howLong > 1 ) R.string.seconds else R.string.second
        }

        return context.getString( labelId )
    }

    constructor( source: Parcel ) : this(
        source.readParcelable<ObservableLong>( ObservableLong::class.java.classLoader ),
        source.readSerializable() as ObservableField<String>
    )

    override fun describeContents() = 0

    override fun writeToParcel( dest: Parcel, flags: Int ) = with( dest ) {
        writeParcelable( time, 0 )
        writeSerializable( message )
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<LastMessage> = object : Parcelable.Creator<LastMessage> {
            override fun createFromParcel( source: Parcel ): LastMessage =
                LastMessage( source )
            override fun newArray( size: Int ): Array<LastMessage?> =
                arrayOfNulls( size )
        }
    }
}
