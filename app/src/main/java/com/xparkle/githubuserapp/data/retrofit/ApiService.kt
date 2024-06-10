package com.xparkle.githubuserapp.data.retrofit

import com.xparkle.githubuserapp.data.response.UserDetailRes
import com.xparkle.githubuserapp.data.response.GithubResponse
import com.xparkle.githubuserapp.data.response.ItemsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object{
        private const val USERNAME = "ikhsan"
    }
    @GET("search/users")
    fun getSearchUsers(
        @Query("q") query: String = USERNAME
    ): Call<GithubResponse>
    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String = USERNAME
    ): Call<UserDetailRes>
    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<ItemsItem>>
    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<ItemsItem>>
}