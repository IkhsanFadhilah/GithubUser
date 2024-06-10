package com.xparkle.githubuserapp.helper

import androidx.recyclerview.widget.DiffUtil
import com.xparkle.githubuserapp.database.UserFav

class HelperFav(private val mOldFavUserList: List<UserFav>, private val mNewFavUserList: List<UserFav>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return mOldFavUserList.size
        }
        override fun getNewListSize(): Int {
            return mNewFavUserList.size
        }
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return mOldFavUserList[oldItemPosition].username == mNewFavUserList[newItemPosition].username
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldUser = mOldFavUserList[oldItemPosition]
            val newUser = mNewFavUserList[newItemPosition]
            return oldUser.username == newUser.username && oldUser.avatarUrl == newUser.avatarUrl
        }
}