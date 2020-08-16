package com.example.cermatiandroidtest.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cermatiandroidtest.R
import com.example.cermatiandroidtest.model.SearchData
import com.example.cermatiandroidtest.utils.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SearchContract {

    lateinit var searchPresenter: SearchPresenter
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var listSearchData : ArrayList<SearchData>
    private lateinit var searchAdapter: SearchAdapter

    companion object{
        var page = 1
        var query = "QUERY"
        var isProcessing = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchPresenter = SearchPresenter(this)
        layoutManager = LinearLayoutManager(this)
        rv_list_username.layoutManager = layoutManager
        listSearchData = arrayListOf()
    }

    override fun onResume() {
        super.onResume()
        queryChangeEvent()
        paginationScroll()
    }

    override fun showLoading() {
        tv_the_data.visibility = INVISIBLE
        pb_loading.visibility = VISIBLE
        rv_list_username.adapter = null
    }

    override fun hideLoading() {
        pb_loading.visibility = INVISIBLE
    }

    override fun whenFailureRequest(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    override fun whenSuccessGetData(listUser: ArrayList<SearchData>) {
        if(listUser.isNotEmpty()) {
            listSearchData.addAll(listUser)
            searchAdapter = SearchAdapter(listSearchData)
            rv_list_username.adapter = searchAdapter
            searchAdapter.notifyDataSetChanged()
            layoutManager.scrollToPosition(searchAdapter.itemCount-listUser.size)
            isProcessing = false
        } else {
            tv_the_data.visibility = VISIBLE
            tv_the_data.text = "User not found"
            isProcessing = false
        }
    }

    private fun paginationScroll() {
        val isLastPage = false
        rv_list_username.addOnScrollListener(object : PaginationScrollListener(layoutManager){
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun loadMoreItem() {
                if (!isProcessing) {
                    isProcessing = true
                    page++
                    searchPresenter.getSearchData(query, "$page")
                }
            }

        })
    }

    private fun queryChangeEvent() {
        et_search.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE){
                if(et_search.text.isNotEmpty()) {
                    showLoading()
                    query = "${et_search.text}"
                    searchPresenter.getSearchData(query,"$page")
                    return@setOnEditorActionListener true
                } else {
                    return@setOnEditorActionListener false
                }
            } else {
                return@setOnEditorActionListener false
            }
        }
    }
}
