package com.example.v26
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    /*
    *      endpoint of api here is product https://dummyjson.com/products
    *      |||||||
    *      VVVVVVV*/
    @GET("products")
    fun getProductData():Call<MyData>
}