package thiengo.com.br.qmessage

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import thiengo.com.br.qmessage.databinding.ContactBinding
import thiengo.com.br.qmessage.domain.Contact

class ContactsAdapter(
    private val context: Context,
    private val contacts: List<Contact> ) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int ) : ContactsAdapter.ViewHolder {

        val inflater = LayoutInflater.from( context )

        val binding = ContactBinding
                .inflate( inflater, parent, false )

        return ViewHolder( binding )
    }

    override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int ) {

        holder.setData( contacts[ position ] )
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ViewHolder( val binding: ContactBinding) :
            RecyclerView.ViewHolder( binding.root ) {

        fun setData( contact: Contact ) {
            binding.contact = contact
            binding.executePendingBindings()
        }
    }
}