package com.example.mvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm.databinding.CardViewBinding

class MyAdapter(private val email: Array<String?>?,
              private val name: Array<String?>?,
              private val imageList : Array<String?>?): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private lateinit var context: Context

    class MyViewHolder(binding: CardViewBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val name1=binding.text1
        val email1=binding.text2
        val image=binding.img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(CardViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    }

    override fun getItemCount(): Int {
        return name?.size ?:0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name1.text=name?.get(position)
        holder.email1.text=email?.get(position)
        Glide.with(context).load(imageList?.get(position)).centerCrop().into(holder.image)

    }


}