package com.example.v26

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.v26.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val retrofitBuilder = Retrofit.Builder()
//        https://dummyjson.com/
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getProductData()  //method created in interface

        retrofitData.enqueue(object : Callback<MyData?> {
//                           (Ctrl+Shift+Space) below code is generated

            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
//            if api call is a success ,then Use the data from API and show in your APP

                val responseBody = response.body()
//              for this api response.body() returns 4 variables as they are created in api
//                products, total, skip, limit

                val productList = responseBody?.products!!
//                !! those two exclamation mark check if products is not null

                myAdapter = MyAdapter(this@MainActivity,productList)

                binding.recyclerView.adapter = myAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)


            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
//                if api call fails then show whatever
                Log.d("Main Activity" , "onFailure: "+t.message)
            }
        })
    }
}
