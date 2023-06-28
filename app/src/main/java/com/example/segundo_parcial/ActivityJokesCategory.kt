package com.example.segundo_parcial


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ActivityJokesCategory : AppCompatActivity() {

    private lateinit var textViewJoker: TextView
    private lateinit var buttonBack: Button
    private lateinit var buttonHome: Button
    private lateinit var imagenChuck: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes_category)

        textViewJoker = findViewById(R.id.textViewJokers)
        buttonBack = findViewById(R.id.buttonBack)
        buttonHome = findViewById(R.id.buttonHome)
        imagenChuck = findViewById(R.id.imageViewChuck)

        val bundle = intent.extras
        val cate = bundle?.getString("name", "")

        getCategoriesJoke(cate)

        imagenChuck.setOnClickListener {
            getCategoriesJoke(cate)
        }


        buttonBack.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        buttonHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getCategoriesJoke(name: String?) {
        CoroutineScope(Dispatchers.IO).launch {

            val URL1 = "https://api.chucknorris.io/jokes/random?category="
            val okHttpClient: OkHttpClient = OkHttpClient()

            runOnUiThread {

                val request: Request = Request.Builder().url(URL1 + name).build()
                okHttpClient.newCall(request).enqueue(object : Callback {

                    override fun onFailure(call: Call, e: IOException) {
                        showError()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val json = response.body()?.string()

                        val text = (JSONObject(json).get("value")).toString()

                        runOnUiThread {
                            textViewJoker.text = text
                        }
                    }
                })
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "VAYA!!! Algo Salio Mal", Toast.LENGTH_SHORT).show()
    }
}