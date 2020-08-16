package com.example.cermatiandroidtest.model

import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("login")
    var login : String? = null,
    @SerializedName("avatar_url")
    var avatarUrl : String? = null
)