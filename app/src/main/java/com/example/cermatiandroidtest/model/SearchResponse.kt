package com.example.cermatiandroidtest.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total_count")
    var totalCount : Int? = 0,
    @SerializedName("items")
    var items : ArrayList<SearchData>? = null
)