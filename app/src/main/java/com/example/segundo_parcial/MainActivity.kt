package com.example.segundo_parcial


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private  lateinit var textChuck: TextView
    private  lateinit var textViewCategorieList: TextView
    private lateinit var buttonNext: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var imgChuck: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textChuck = findViewById(R.id.textViewchuck)
        textViewCategorieList = findViewById(R.id.textViewCategoriesList)
        progressBar = findViewById(R.id.progressBar)
        imgChuck = findViewById(R.id.imageChuck)
        buttonNext = findViewById(R.id.buttonNext)

        buttonNext.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        getFrase()

        imgChuck.setOnClickListener {
            getFrase()
        }
    }

    private fun getFrase() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getFrase("random")
            val response = call.body()

            runOnUiThread {
                progressBar.visibility = View.VISIBLE
                val handler = Handler()
                handler.postDelayed({
                    if (call.isSuccessful){
                        progressBar.visibility = View.GONE
                        val frasesChuck = response?.frase.toString()
                        textChuck.text  = frasesChuck
                    }
                    else{
                        showError()
                    }
                },500)
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "ALGO SALIO MAL", Toast.LENGTH_SHORT).show()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_DOGS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object{
        const val URL_DOGS = "https://api.chucknorris.io/jokes/"
    }
}


