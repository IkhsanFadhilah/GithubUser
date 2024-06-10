package com.xparkle.githubuserapp.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xparkle.githubuserapp.data.response.UserDetailRes
import com.xparkle.githubuserapp.data.retrofit.ApiConfig
import com.xparkle.githubuserapp.database.UserFav
import com.xparkle.githubuserapp.repository.UserFavRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): ViewModel() {
    private val _detailUser = MutableLiveData<UserDetailRes>()
    val detailUser: LiveData<UserDetailRes> = _detailUser
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    companion object{
        private const val TAG = "DetailViewModel"
    }
    init {
        findDetailUser("")
    }
    private val mUserFavoriteRepository = UserFavRepo(application)
    val userData = MutableLiveData<UserDetailRes>()
    val userFavorite = MutableLiveData<UserFav>()
    val error = MutableLiveData<String>()
    val isFavorite = MutableLiveData<Boolean>()
    fun setFavorite(favorite: UserFav) {
        mUserFavoriteRepository.setFavorite(favorite)
        isFavorite.postValue(true)
    }
    fun unsetFavorite(username:String) {
        mUserFavoriteRepository.unsetFavorite(username)
        isFavorite.postValue(false)
    }
    fun initFavorite(favorite: UserFav) {
        userFavorite.postValue(favorite)
    }
    fun checkFavorite(username: String): LiveData<UserFav> =
        mUserFavoriteRepository.checkFavorite(username)
    fun findDetailUser(username : String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<UserDetailRes> {
            override fun onResponse(
                call: Call<UserDetailRes>,
                response: Response<UserDetailRes>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _detailUser.value = response.body()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<UserDetailRes>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}