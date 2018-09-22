package thiengo.com.br.qmessage.data

import thiengo.com.br.qmessage.R
import thiengo.com.br.qmessage.domain.Contact
import thiengo.com.br.qmessage.domain.LastMessage

class Database {
    companion object {
        fun getContacts() =
            mutableListOf(
                Contact(
                    65,
                    R.drawable.person_01,
                    "Juliano Alves Cunha",
                    LastMessage(
                        1537561600392,
                        "Vc vai ao casamento?"
                    ),
                    3
                ),
                Contact(
                    98,
                    R.drawable.person_02,
                    "Rafaela Costa",
                    LastMessage(
                        1537561600392 - (5 * 60 * 1000),
                        "Provavelmente é o nível 2, quase sempre"
                    ),
                    1
                ),
                Contact(
                    13,
                    R.drawable.person_03,
                    "Neiliane Almeida Ferreira",
                    LastMessage(
                        1537561600392 - (8 * 60 * 1000),
                        "Somente dessa vez."
                    ),
                    2
                ),
                Contact(
                    2,
                    R.drawable.person_04,
                    "Ana barcellos",
                    LastMessage(
                        1537561600392 - (9 * 60 * 1000),
                        "Certamente ele entrará em contato solic"
                    ),
                    7
                ),
                Contact(
                    9856,
                    R.drawable.person_05,
                    "Jordão Souza",
                    LastMessage(
                        1537561600392 - (14 * 60 * 1000),
                        "Eu vou também."
                    ),
                    0
                ),
                Contact(
                    33658,
                    R.drawable.person_06,
                    "Gabriela Silveira",
                    LastMessage(
                        1537561600392 - (19 * 60 * 1000),
                        "Tudo certo então."
                    ),
                    0
                )
            )

        fun getUserLogged() =
            Contact(
                69,
                R.drawable.user_logged,
                "Thiengo Android",
                LastMessage(
                    0,
                    ""
                ),
                0
            )
    }
}