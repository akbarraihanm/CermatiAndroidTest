package com.example.cermatiandroidtest.ui

import com.example.cermatiandroidtest.model.SearchData

interface SearchContract {
    fun showLoading()
    fun hideLoading()
    fun whenFailureRequest(msg : String)
    fun whenSuccessGetData(listUser : ArrayList<SearchData>)
}