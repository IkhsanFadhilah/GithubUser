package com.xparkle.githubuserapp.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xparkle.githubuserapp.database.UserFav
import com.xparkle.githubuserapp.databinding.RowUserBinding
import com.xparkle.githubuserapp.helper.UserFavCallback
import com.xparkle.githubuserapp.ui.main.DetailActivity

class UserFavAdapt : RecyclerView.Adapter<UserFavAdapt.FavoriteViewHolder>(){
    private val listFavorites = ArrayList<UserFav>()
    fun setListFavorites(listFavorites: List<UserFav>) {
        val diffCallback = UserFavCallback(this.listFavorites, listFavorites)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFavorites.clear()
        this.listFavorites.addAll(listFavorites)
        diffResult.dispatchUpdatesTo(this)
    }
    fun getSwipedUsername(position: Int): String {
        return listFavorites[position].username
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding =
            RowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorites[position])
    }
    override fun getItemCount(): Int {
        return listFavorites.size
    }
    inner class FavoriteViewHolder(private val binding: RowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: UserFav) {
            with(binding) {
                tvUsername.text = favorite.username
                Glide.with(itemView)
                    .load(favorite.avatarUrl)
                    .into(imageView)
                itemView.setOnClickListener {
                    val moveDetailActivity = Intent(
                        it.context,
                        DetailActivity::class.java
                    )
                    moveDetailActivity.putExtra("USERNAME", favorite.username)
                    it.context.startActivity(moveDetailActivity)
                }
            }
        }
    }
}