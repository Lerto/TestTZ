package com.example.testapp.api

import android.content.Context
import com.example.testapp.model.Model
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface APIService {

    @Headers("Content-Type: application/json")
    @GET("pokemon/{id}")
    suspend fun getPoke(
        @Path("id") id: Int
    ): Response<Model.Poke>

    companion object {

        private const val BASE_URI = "https://pokeapi.co/api/v2/"

        fun api(mContext: Context): APIService = Retrofit.Builder()
            .baseUrl(BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(OkHttpClient().newBuilder().build())
            .build()
            .create(APIService::class.java)
    }
}
