package thiengo.com.br.qmessage

import android.app.Application
import android.content.Intent
import android.os.SystemClock
import android.support.v4.content.LocalBroadcastManager
import thiengo.com.br.qmessage.domain.Contact
import thiengo.com.br.qmessage.domain.LastMessage

class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        /*
         * Algoritmo responsável por simular a chegada de uma
         * notificação onde os dados, um objeto Contact, serão
         * enviados a atividade principal para processamento e
         * reflexo em tela.
         * */
        Thread{
            kotlin.run {
                /*
                 * Delay de 3 segundos para simular o delay de
                 * rede para a chegada de notificação push.
                 * */
                SystemClock.sleep(3000)

                /*
                 * É importante que o objeto em notificação tenha
                 * o id do contato, a nova mensagem e o número de
                 * novas mensagens não lidas.
                 * */
                val contact = Contact(
                    9856,
                    0,
                    "",
                    LastMessage(
                        System.currentTimeMillis(),
                        "Eu vou também."
                    ),
                    2
                )

                val intent = Intent( BroadcastNotification.FILTER )
                intent.putExtra( BroadcastNotification.DATA, contact )

                LocalBroadcastManager
                    .getInstance( this )
                    .sendBroadcast( intent )
            }
        }.start()
    }
}