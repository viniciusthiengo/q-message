package thiengo.com.br.qmessage

import android.content.IntentFilter
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu

import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.top_bar.*
import thiengo.com.br.qmessage.data.Database
import thiengo.com.br.qmessage.databinding.TopBarBinding
import thiengo.com.br.qmessage.domain.Contact

class ContactsActivity : AppCompatActivity() {

    lateinit var contacts: MutableList<Contact>
    lateinit var broadcast: BroadcastNotification
    lateinit var thread: Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        setSupportActionBar(toolbar)

        /*
         * Removendo o título padrão do app da barra
         * de topo dele.
         * */
        supportActionBar?.setDisplayShowTitleEnabled(false)

        initTopBar()

        contacts = Database.getContacts()
        initContactsList()

        initBroadcastReceiver()
    }

    private fun initTopBar(){
        val binding = TopBarBinding.bind( toolbar )
        binding.user = Database.getUserLogged()
    }

    private fun initContactsList(){
        rv_contacts.setHasFixedSize( true )

        val layoutManager = LinearLayoutManager( this )
        rv_contacts.layoutManager = layoutManager

        rv_contacts.adapter = ContactsAdapter(
                this,
                contacts
        )

        runAdapterTime()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate( R.menu.menu_contacts, menu )
        return true
    }

    /*
     * Método responsável por registrar um BroadcastReceiver
     * (BroadcastNotification) para poder receber uma comunicação
     * de CustomApplication, comunicação sobre uma nova mensagem,
     * notificação, que chegou do servidor Web (simulação).
     * */
    private fun initBroadcastReceiver(){
        broadcast = BroadcastNotification( this )
        val filter = IntentFilter( BroadcastNotification.FILTER )

        LocalBroadcastManager
                .getInstance( this )
                .registerReceiver( broadcast, filter )
    }

    override fun onDestroy() {
        super.onDestroy()
        /*
         * Liberação do BroadcastReceiver.
         * */
        LocalBroadcastManager
                .getInstance( this )
                .unregisterReceiver( broadcast )

        /*
         * Desligando Thread de timer.
         * */
        thread.interrupt()
    }

    /*
     * Método responsável por atualizar o tempo das últimas
     * mensagens enviadas para cada contato em lista.
     * */
    private fun runAdapterTime(){
        thread = Thread{
            kotlin.run {
                while( true ){

                    /*
                     * Delay de 1 minuto.
                     * */
                    SystemClock.sleep(60 * 100)

                    /*
                     * Como a atualização será refletida em
                     * tela é importante que ela ocorra na
                     * Thread principal, UI.
                     * */
                    runOnUiThread {
                        rv_contacts?.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }

        thread.start()
    }

    /*
     * Método responsável por atualizar a lista de contatos
     * assim que uma nova mensagem chega via notificação push.
     * */
    fun updateContactsList( contact: Contact ){

        val item = contacts.find {
            it.id.get() == contact.id.get()
        }

        item!!.newMessages.set( contact.newMessages.get() )
        item.lastMessage.time.set( contact.lastMessage.time.get() )
        item.lastMessage.message.set( contact.lastMessage.message.get() )

        contacts.remove( item )
        contacts.add( 0, item )

        rv_contacts.adapter?.notifyDataSetChanged()
    }
}
