package com.example.v26

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import kotlin.math.ceil
import kotlin.math.floor

class MyAdapter (private val context:Activity, val productArrayList:List<Product>):
RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var title:TextView
        var image:ShapeableImageView
        var rating1:ImageView
        var rating2:ImageView
        var rating3:ImageView
        var rating4:ImageView
        var rating5:ImageView

        init{
            title = itemView.findViewById(R.id.productTitle)
            image = itemView.findViewById(R.id.productImage)
            rating1=itemView.findViewById(R.id.productRating1)
            rating2=itemView.findViewById(R.id.productRating2)
            rating3=itemView.findViewById(R.id.productRating3)
            rating4=itemView.findViewById(R.id.productRating4)
            rating5=itemView.findViewById(R.id.productRating5)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArrayList[position]
        holder.title.text = currentItem.title
//      using picasso because image is in url
        Picasso.get().load(currentItem.thumbnail).into(holder.image)
//                                                     ^^^^^^^^^^^^^
        //                                                 |||||||||||||
        //                                              ---location to show---
        var value = 0
        val currentItemRating = currentItem.rating
        when {
            currentItemRating>4.5-> value=ceil(currentItemRating).toInt()
            else-> value=floor(currentItemRating).toInt()
        }
        /*if(currentItem.rating>4.5)
            value =ceil(currentItem.rating).toInt()
        else
            value= floor(currentItem.rating).toInt()*/
        when (value) {

            4 -> {
                holder.rating1.alpha = 1.0f
                holder.rating2.alpha = 1.0f
                holder.rating3.alpha = 1.0f
                holder.rating4.alpha = 1.0f
                holder.rating5.alpha = 0.0f
            }
            5 -> {
                holder.rating1.alpha = 1.0f
                holder.rating2.alpha = 1.0f
                holder.rating3.alpha = 1.0f
                holder.rating4.alpha = 1.0f
                holder.rating5.alpha = 1.0f
            }
            else -> {
                holder.rating1.alpha = 1.0f
                holder.rating2.alpha = 0.0f
                holder.rating3.alpha = 0.0f
                holder.rating4.alpha = 0.0f
                holder.rating5.alpha = 0.0f
            }
        }


    }

}