package com.example.profitnetworking

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.profitnetworking.networking.Hint
import kotlinx.android.synthetic.main.image_item_layout.view.*
import com.squareup.picasso.Picasso
class MyAdapter(
    private val hintList: List<Hint> = listOf(),
    private val onHintClick:(Hint) -> Unit
) :RecyclerView.Adapter<MyAdapter.HintViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HintViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item_layout, parent, false)
        return HintViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hintList.count()
    }

    override fun onBindViewHolder(holder: HintViewHolder, position: Int) {
        holder.bindHint(hintList[position])
    }


    inner class HintViewHolder(
        private val view: View
    ):RecyclerView.ViewHolder(view){
        @SuppressLint("SetTextI18n")
        fun bindHint(hint: Hint){
            view.image_tags.text ="Tags: ${hint.tags}"
            view.image_likes.text = "Likes ${hint.likes}"
            Picasso
                .get()
                .load(hint.largeImageURL)
                .into(view.item_image_url)
            view.setOnClickListener{
                onHintClick(hint)
            }
        }
    }
}