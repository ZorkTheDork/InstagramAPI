package com.example.anotherapiproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.anotherapiproject.R
import com.example.anotherapiproject.models.MediaResponse
import com.squareup.picasso.Picasso

class DataAdapter(private var dataList: MediaResponse?, private val context: Context): RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var imageView: ImageView = itemLayoutView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (dataList != null) {
            val picasso = Picasso.get()
            picasso.load(dataList!!.data[position].media_url).into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        if (dataList != null) {
            return dataList!!.data.count()
        }
        return 0
    }


}