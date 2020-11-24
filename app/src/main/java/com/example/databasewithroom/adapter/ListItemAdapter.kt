package com.example.databasewithroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.databasewithroom.R
import com.example.databasewithroom.fragments.list.ListFragmentDirections
import com.example.databasewithroom.model.Person
import kotlinx.android.synthetic.main.custom_layout.view.*

class ListItemAdapter : RecyclerView.Adapter<ListItemAdapter.MyViewHolder>() {

    private var personList = emptyList<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_layout, parent, false))
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem: Person = personList[position]

        holder.itemView.tvID.text = currentItem.id.toString()
        holder.itemView.tvFname.text = currentItem.firstName
        holder.itemView.tvLname.text = currentItem.lastName

        holder.itemView.row_layout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment2(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(person: List<Person>) {
        this.personList = person
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return personList.size
    }
}
