package com.example.cermatiandroidtest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cermatiandroidtest.R
import com.example.cermatiandroidtest.model.SearchData
import kotlinx.android.synthetic.main.list_username.view.*

class SearchAdapter(private val listUser: ArrayList<SearchData>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_username, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(lu: SearchData) {
            with(itemView) {
                with(lu) {
                    Glide.with(itemView)
                        .load(avatarUrl)
                        .into(iv_user_photo)
                    tv_name.text = login
                }
            }
        }
    }
}