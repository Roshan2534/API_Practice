package com.example.assigment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlin.math.log

private const val TAG = "MovieDetailActivity"
class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)


        val title = intent.getStringExtra("textViewTitle")
        val image = intent.getStringExtra("imageViewMovie")
        val rating = intent.getFloatExtra("ratings", 0.0f)
        Log.d(TAG, "onCreate: Rating is ${rating}")
        val releaseYear = intent.getIntExtra("textViewReleaseYear", 0)
        val genres = intent.getStringArrayExtra("genres")


        val imageViewMovie: ImageView = findViewById(R.id.imageViewMovie)
        val textViewTitle: TextView = findViewById(R.id.textViewTitle)
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        val textViewReleaseYear: TextView = findViewById(R.id.textViewReleaseYear)
        val textViewGenre: TextView = findViewById(R.id.textViewGenre)


        textViewTitle.text = title
        ratingBar.rating = rating
        textViewReleaseYear.text = "Release in ${releaseYear}"
        Glide.with(this).load(image).into(imageViewMovie)

        if (genres != null) {
            val genreText = "Genre: ${genres.joinToString(", ")}"
            textViewGenre.text = genreText
        } else {
            textViewGenre.text = "Genre: Not available"
        }

    }
}