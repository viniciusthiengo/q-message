package thiengo.com.br.qmessage

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import thiengo.com.br.qmessage.domain.Contact

/*
 * LocalBroadcast que permitirá a fácil comunicação entre
 * CustomApplication e ContactsActivity.
 * */
class BroadcastNotification(
    val activity: ContactsActivity ): BroadcastReceiver() {

    companion object {
        const val FILTER = "bn_filter"
        const val DATA = "bn_data"
    }

    override fun onReceive( context: Context?, intent: Intent? ) {
        /*
         * Como sabemos que getParcelableExtra() nunca retonará
         * null para este trecho do projeto, seguramente podemos
         * trabalhar o operador force, !!, para que o IDE permita
         * a continuidade na compilação.
         * */
        val contact = intent!!.getParcelableExtra<Contact>( DATA )

        activity.updateContactsList( contact )
    }
}