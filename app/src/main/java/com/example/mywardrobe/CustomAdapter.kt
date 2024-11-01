package com.example.mywardrobe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val clothes: MutableList<Clothes>) : RecyclerView.Adapter<CustomAdapter.UserViewHolder>(){
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageIV: ImageView = itemView.findViewById(R.id.imageIV)
        val nameTV: TextView = itemView.findViewById(R.id.nameTV)
        val descriptionTV: TextView = itemView.findViewById(R.id.descriptionTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return  UserViewHolder(itemView)
    }

    override fun getItemCount() = clothes.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val cloth = clothes[position]
        holder.imageIV.setImageResource(cloth.image)
        holder.nameTV.text = cloth.name
        holder.descriptionTV.text = cloth.description
    }
}