package com.xparkle.githubuserapp.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xparkle.githubuserapp.database.UserFav
import com.xparkle.githubuserapp.repository.UserFavRepo

class UserFavViewModel(application: Application): ViewModel() {
    private val mFavUserRepository: UserFavRepo = UserFavRepo(application)
    fun getAllFavUser(): LiveData<List<UserFav>> = mFavUserRepository.getAllFavorites()
    fun unsetFavorite(username:String) {
        mFavUserRepository.unsetFavorite(username)
    }
    fun removeAllFavorites() {
        mFavUserRepository.removeAllFavorites()
    }
}