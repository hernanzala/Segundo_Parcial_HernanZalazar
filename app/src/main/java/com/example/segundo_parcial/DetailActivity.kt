 package com.example.segundo_parcial

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 class DetailActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
     private lateinit var adapter: Adapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        recyclerView = findViewById(R.id.RecyclerViewCategories)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = Adapter(applicationContext)
        recyclerView.adapter = adapter

        adapter.submitList(getCategoriesList())

        adapter.onItemClickListener = {categories ->
            val intent = Intent(this,ActivityJokesCategory::class.java)
            intent.putExtra("name",categories.name)
            startActivity(intent)

        }
    }

     private fun getCategoriesList(): MutableList<Categories>? {
         return mutableListOf(
             Categories("animal"),
             Categories("career"),
             Categories("celebrity"),
             Categories("dev"),
             Categories("explicit"),
             Categories("fashion"),
             Categories("food"),
             Categories("history"),
             Categories("money"),
             Categories("movie"),
             Categories("music"),
             Categories("political"),
             Categories("religion"),
             Categories("science"),
             Categories("sport"),
             Categories("travel"),
         )

     }


 }

