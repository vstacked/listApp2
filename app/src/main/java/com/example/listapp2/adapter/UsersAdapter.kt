package com.example.listapp2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.listapp2.R
import com.example.listapp2.activity.DetailActivity
import com.example.listapp2.model.UsersModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.list_item.view.*

class UsersAdapter(private var listItem: List<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list: List<Any> = listItem

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatar: CircleImageView = itemView.civ_avatar
        var name: TextView = itemView.tv_name
        var username: TextView = itemView.tv_username
        var company: TextView = itemView.tv_company
        var location: TextView = itemView.tv_location
        var repo:TextView = itemView.tv_repo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listViewHolder: ListViewHolder = holder as ListViewHolder
        val model: UsersModel = list[position] as UsersModel

        Glide.with(holder.itemView.context)
            .load(holder.itemView.context.resources.getIdentifier(model.avatar, "", holder.itemView.context.packageName))
            .apply(RequestOptions().override(60, 60))
            .into(listViewHolder.avatar)

        listViewHolder.name.text = model.name
        listViewHolder.username.text = "( ${model.username} )"
        listViewHolder.company.text = model.company
        listViewHolder.location.text = model.location
        listViewHolder.repo.text = "Repository\n${model.repository}"

        holder.itemView.setOnClickListener {
            val usersModel = UsersModel(
                model.username,
                model.name,
                model.avatar,
                model.company,
                model.location,
                model.repository,
                model.follower,
                model.following
            )
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_USER, usersModel)
            holder.itemView.context.startActivity(intent)
        }
    }

}
