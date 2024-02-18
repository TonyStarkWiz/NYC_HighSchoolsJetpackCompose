package com.example.nyc_highschools.data.network

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NYCApiService {

    @GET("resource/s3k6-pzi2.json")

    suspend fun getSchools(
        @Query("\$limit") limit: Int,
        @Query("\$offset") offset: Int
    ): List<SchoolDetail>


    companion object {
        private const val BASE_URL = "https://data.cityofnewyork.us/"

        fun create(): NYCApiService {
            val  logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = okhttp3.OkHttpClient.Builder().addInterceptor(logger).build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NYCApiService::class.java)
        }
    }
}
