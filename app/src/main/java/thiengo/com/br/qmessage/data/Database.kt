package thiengo.com.br.qmessage.data

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.databinding.ObservableLong
import thiengo.com.br.qmessage.R
import thiengo.com.br.qmessage.domain.Contact
import thiengo.com.br.qmessage.domain.LastMessage

class Database {
    companion object {
        fun getContacts(): MutableList<Contact> {
            val time = System.currentTimeMillis()

            return mutableListOf(
                Contact(
                    ObservableInt(65),
                    ObservableInt(R.drawable.person_01),
                    ObservableField("Juliano Alves Cunha"),
                    LastMessage(
                        ObservableLong(time - (2 * 60 * 1000)),
                        ObservableField("Vc vai ao casamento?")
                    ),
                    ObservableInt(3)
                ),
                Contact(
                    ObservableInt(98),
                    ObservableInt(R.drawable.person_02),
                    ObservableField("Rafaela Costa"),
                    LastMessage(
                        ObservableLong(time - (5 * 60 * 1000)),
                        ObservableField("Provavelmente é o nível 2, quase sempre")
                    ),
                    ObservableInt(1)
                ),
                Contact(
                    ObservableInt(13),
                    ObservableInt(R.drawable.person_03),
                    ObservableField("Neiliane Almeida Ferreira"),
                    LastMessage(
                        ObservableLong(time - (8 * 60 * 1000)),
                        ObservableField("Somente dessa vez.")
                    ),
                    ObservableInt(2)
                ),
                Contact(
                    ObservableInt(2),
                    ObservableInt(R.drawable.person_04),
                    ObservableField("Ana barcellos"),
                    LastMessage(
                        ObservableLong(time - (9 * 60 * 1000)),
                        ObservableField("Certamente ele entrará em contato solic")
                    ),
                    ObservableInt(7)
                ),
                Contact(
                    ObservableInt(9856),
                    ObservableInt(R.drawable.person_05),
                    ObservableField("Jordão Souza"),
                    LastMessage(
                        ObservableLong(time - (14 * 60 * 1000)),
                        ObservableField("Eu vou também.")
                    ),
                    ObservableInt(0)
                ),
                Contact(
                    ObservableInt(33658),
                    ObservableInt(R.drawable.person_06),
                    ObservableField("Gabriela Silveira"),
                    LastMessage(
                        ObservableLong(time - (19 * 60 * 1000)),
                        ObservableField("Tudo certo então.")
                    ),
                    ObservableInt(0)
                )
            )
        }

        fun getUserLogged() =
            Contact(
                ObservableInt(69),
                ObservableInt(R.drawable.user_logged),
                ObservableField("Thiengo Android"),
                LastMessage(
                    ObservableLong(0),
                    ObservableField("")
                ),
                ObservableInt(0)
            )
    }
}
