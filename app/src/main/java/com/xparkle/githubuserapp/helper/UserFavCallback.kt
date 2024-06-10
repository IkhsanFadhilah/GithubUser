package com.xparkle.githubuserapp.helper

import com.xparkle.githubuserapp.database.UserFav
import androidx.recyclerview.widget.DiffUtil

class UserFavCallback (
    private val mOldFavoriteList: List<UserFav>,
    private val mNewFavoriteList: List<UserFav>
        ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return mOldFavoriteList.size
        }
        override fun getNewListSize(): Int {
            return mNewFavoriteList.size
        }
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return mOldFavoriteList[oldItemPosition].username == mNewFavoriteList[newItemPosition].username
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldFavorite = mOldFavoriteList[oldItemPosition]
            val newFavorite = mNewFavoriteList[newItemPosition]
            return oldFavorite.username == newFavorite.username
        }
}