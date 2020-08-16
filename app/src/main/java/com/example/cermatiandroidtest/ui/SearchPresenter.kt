package com.example.cermatiandroidtest.ui

import android.util.Log
import com.example.cermatiandroidtest.model.SearchResponse
import com.example.cermatiandroidtest.service.ApiClient
import com.example.cermatiandroidtest.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter(private val searchContract: SearchContract) {

    private val apiInterface = ApiClient.getSearch().create(ApiInterface::class.java)

    fun getSearchData(query : String, page : String) {
        val call = apiInterface.getUserByQuery(query,page)
        call.enqueue(object : Callback<SearchResponse>{
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                searchContract.whenFailureRequest("$t")
            }

            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val result = response.body()
                try {
                    if(response.code() == 200) {
                        searchContract.hideLoading()
                        result?.items?.let { searchContract.whenSuccessGetData(it) }
                    } else {
                        searchContract.hideLoading()
                        searchContract.whenFailureRequest(response.message())
                    }
                } catch (e: Exception) {
                    Log.d("theException","$e")
                }
            }

        })
    }
}