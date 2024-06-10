package com.xparkle.githubuserapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.xparkle.githubuserapp.database.UserFav
import com.xparkle.githubuserapp.database.UserFavDao
import com.xparkle.githubuserapp.database.UserFavDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserFavRepo(application : Application) {
    private val mFavUserDao: UserFavDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = UserFavDatabase.getDatabase(application)
        mFavUserDao = db.favUserDao()
    }
    fun getAllFavorites(): LiveData<List<UserFav>> = mFavUserDao.getAllFavorites()
    fun setFavorite(favorite: UserFav) {
        executorService.execute { mFavUserDao.insert(favorite) }
    }
    fun unsetFavorite(username:String) {
        executorService.execute { mFavUserDao.unsetFavorite(username) }
    }
    fun checkFavorite(username:String):LiveData<UserFav> = mFavUserDao.getFavUserByUsername(username)
    fun removeAllFavorites() {
        executorService.execute { mFavUserDao.removeAllFavorites() }
    }
}