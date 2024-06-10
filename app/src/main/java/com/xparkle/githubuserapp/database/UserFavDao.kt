package com.xparkle.githubuserapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserFavDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteUser: UserFav)
    @Query("SELECT * FROM UserFav WHERE username = :username")
    fun getFavUserByUsername(username: String): LiveData<UserFav>
    @Query("DELETE FROM UserFav WHERE username = :username")
    fun unsetFavorite(username: String)
    @Query("DELETE FROM UserFav")
    fun removeAllFavorites()
    @Query("SELECT * from UserFav")
    fun getAllFavorites(): LiveData<List<UserFav>>
}