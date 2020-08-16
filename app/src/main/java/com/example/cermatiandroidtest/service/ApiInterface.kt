package com.example.cermatiandroidtest.service

import com.example.cermatiandroidtest.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("users")
    fun getUserByQuery(@Query("q") query: String,
                       @Query("page") page : String) : Call<SearchResponse>
}