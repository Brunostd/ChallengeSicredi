package com.example.challengentconsult.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.challengentconsult.R
import com.example.challengentconsult.databinding.EventsBinding
import com.example.challengentconsult.model.EventModel
import com.example.challengentconsult.ui.HomeFragmentDirections
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

class EventsAdapter(var listEvents: MutableList<EventModel>): RecyclerView.Adapter<EventsAdapter.MyViewHolder>() {
    class MyViewHolder(var itemBinding: EventsBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data: EventModel){
            itemBinding.run {
                this.textTitleEvent.text = data.title
                this.textPriceEvent.text = data.price.toString()

                Glide.with(itemView.context).load(data.image).transform().circleCrop().into(this.imageEvent)
                //Picasso.get().load(data.image).into(this.imageEvent)
                this.cardEvent.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToSelectedEventFragment(data)
                    this.root.findNavController().navigate(action)
                }
            }
        }
        companion object{
            fun create(parent: ViewGroup): MyViewHolder{
                var itemBinding = EventsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MyViewHolder(itemBinding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listEvents[position])
    }

    override fun getItemCount(): Int = listEvents.size
}