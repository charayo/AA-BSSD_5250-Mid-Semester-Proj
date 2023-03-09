package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.example.myapplication.restaurantreponse.Data
import com.example.myapplication.restaurantreponse.RestaurantResponse
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.concurrent.thread

class SearchActivity : AppCompatActivity() {
    private lateinit var searchField: TextView
    private lateinit var searchResponse:String
    private val API_KEY = "21213a78ddmshd3fbadf293e12d8p181de5jsnde4e4df0843d"
    private val service = "https://travel-advisor.p.rapidapi.com/restaurants/list-by-latlng?latitude="
//    private val imageBasePath = "https://image.tmdb.org/t/p/w500"
    private lateinit var prevLocation:String
   private lateinit var location_resonse:LocationResponse
    private lateinit var locationLat: String
    private lateinit var locationLong: String
    lateinit var img:String

    private lateinit var restaurantResponse: List<Data>
//    private lateinit var restaurantNames: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchField = EditText(this).apply {
            hint = "Enter Location"

        }
        val editText = EditText(this).apply {
            hint = "Enter Name"

        }
        val fragmentContainerView = FragmentContainerView(this).apply {
            id = View.generateViewId()
        }

//        val linearLayout = LinearLayout(this).apply {
//            addView(editText)
//            id = View.generateViewId()
//        }

        val searchButton = MaterialButton(this).apply {
            text = "search"
            setBackgroundColor(Color.BLACK)
            setOnClickListener{
                latLong(searchField.text.toString())
            }
        }
        val mainView = LinearLayout(this).apply {
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
            )
            orientation = LinearLayout.VERTICAL
            addView(searchField)
            addView(searchButton)
            addView(fragmentContainerView)
        }
        setContentView(mainView)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(fragmentContainerView.id, CardFragment.newInstance(img), null)
        }
    }

    fun latLong(location:String){
        thread(true) {
            try{
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://nominatim.openstreetmap.org/search.php?q=$location&format=json")
                    .build()
                val response = client.newCall(request).execute()
                Log.d("SearchActivity", response.body.toString())
                val gson =  Gson()
                val entity:LocationResponse = gson.fromJson(response.body?.charStream(), LocationResponse::class.java)
                locationLat = entity[0].lat
                locationLong = entity[0].lon
                println("$locationLat $locationLong")
                if (locationLat.isNotEmpty() && locationLong.isNotEmpty()){
                    makeRequest(locationLat, locationLong)
                }
            } catch (err: Error) {
                print("Error when executing get request:" + err.localizedMessage)

            }
        }
    }

    private fun makeRequest(lat:String, long:String,){
//        lat 35.106766 long -106.629181
        thread(true) {
            try{
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("$service$lat&longitude=$long&limit=30&currency=USD&distance=2&open_now=false&lunit=km&lang=en_US")
                    .header("X-RapidAPI-Key", API_KEY)
                    .header("X-RapidAPI-Host", "travel-advisor.p.rapidapi.com")
                    .build()

                val response = client.newCall(request).execute()
//                Log.d("SearchActivity", response.body.toString())
                val gson =  Gson()
                val restaurantResult:RestaurantResponse = gson.fromJson(response.body?.charStream(), RestaurantResponse::class.java)
//                println(restaurantResult.data.)
                restaurantResponse = restaurantResult.data
                restaurantResponse.forEachIndexed{index, restaurant ->
                    if(index < 20){
                        println("-------------------------")
                        println(restaurant.name)

                            println(restaurant.address_obj?.street1)

//                        if(restaurant?.photo){
                            println(restaurant.photo?.images?.medium?.url)
//                        }
//
//                            println(restaurant)
                    }


                }
//                var firstResponse = restaurantResponse[0].photo.images.
//                println(firstResponse)

            } catch (err: Error) {
                print("Error when executing get request:" + err.localizedMessage)

            }
        }
    }

}