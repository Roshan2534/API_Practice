package com.example.assigment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val TAG = "MainActivity"
const val  BASE_URL = "https://gist.githubusercontent.com/"
class MainActivity : AppCompatActivity(),  OnItemClickListener{

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: Inside oncreate before everything")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recyclerview_movies)
        recycler.setHasFixedSize(true)

        linearLayoutManager = LinearLayoutManager(this)
        recycler.layoutManager = linearLayoutManager


        Log.d(TAG, "onCreate: Inside oncreate before getMovies")
        getMovies(this)

    }


    private fun getMovies(listener: OnItemClickListener) {
        Log.d(TAG, "getMovies: Inside getMovies first line")
        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(ApiInterface::class.java)


        Log.d(TAG, "getMovies: Inside getMovies before get data")
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MovieDataItem>?> {
            override fun onResponse(
                call: Call<List<MovieDataItem>?>,
                response: Response<List<MovieDataItem>?>
            ) {
                val responseBody = response.body()!!
                Log.d(TAG, "onResponse: Inside GetMovies and onResponse")
                myAdapter = MyAdapter(baseContext, responseBody, listener)
                myAdapter.notifyDataSetChanged()

                val recycler = findViewById<RecyclerView>(R.id.recyclerview_movies)
                recycler.adapter = myAdapter
                Log.d(TAG, "onResponse: At the end of onresponse")
            }

            override fun onFailure(call: Call<List<MovieDataItem>?>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
            }
        })
    }

    override fun onItemClick(movie: MovieDataItem) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("textViewTitle",movie.title)
        intent.putExtra("imageViewMovie", movie.image)
        intent.putExtra("ratings", movie.rating.toFloat())
        Log.d(TAG, "onCreate: Rating Datype is ${movie.rating::class.simpleName}")
        intent.putExtra("textViewReleaseYear", movie.releaseYear)
        intent.putExtra("genres", movie.genre.toTypedArray())
        startActivity(intent)

    }
}