package com.example.assigment2

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("AntoninoAN/f3fa4b2260c51a5f80904c747009289e/raw/a1403b33a4c0d9a40d1901f6aeba065abc748a38/MovieGist")
    fun getData():
            Call<List<MovieDataItem>>
}