package com.example.flags.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val BASE_URL = "https://countriesnow.space/api/v0.1/countries/flag/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

class FlagsApiService {

    interface FlagsApiService {
        @GET("images")
        suspend fun getPhotos(): FlagContainer // List<Flag>

    }

    object FlagsApi {
        val retrofitService: FlagsApiService by lazy{ retrofit.create(FlagsApiService::class.java) }
    }
}