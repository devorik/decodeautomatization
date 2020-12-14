package com.example.myapplication.adapter
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.topuser_item.view.*
import java.lang.reflect.Array.get


class TopUserAdapter (
    private val items: List<User> = listOf(),
    private val onItemClick: (User) -> Unit
) : RecyclerView.Adapter<TopUserAdapter.TopUserViewHolder>(){
    inner class TopUserViewHolder(private val view:View) :RecyclerView.ViewHolder(view) {
        fun bindItem(item : User) {
            when(item.rank) {
                "алтын"-> view.rank_color.setBackgroundColor(Color.parseColor("#FFD700"))
                "күміс"-> view.rank_color.setBackgroundColor(Color.parseColor("#c0c0c0"))
                "мыс"-> view.rank_color.setBackgroundColor(Color.parseColor("#cd7f32"))
                "темір"-> view.rank_color.setBackgroundColor(Color.parseColor("#454545"))
            }
            if (item.profilePhoto.isEmpty()) {
                view.profile_image.setImageResource(R.drawable.profile);
            } else{
                Picasso.with(view.context).load(item.profilePhoto).into(view.profile_image)
            }
            view.user_name.text = item.firstName+" "+item.lastName
            view.user_position.text = item.position+" "+item.department
            view.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopUserViewHolder =
        TopUserViewHolder( LayoutInflater.from(parent.context)
            .inflate(R.layout.topuser_item,parent,false));


    override fun onBindViewHolder(holder: TopUserViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount() = items.size
}