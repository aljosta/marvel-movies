package com.example.marvelmovies.data.network

import com.example.marvelmovies.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest

class ApiServiceProvider {
    fun <T> create(apiService: Class<T>, baseUrl: String): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiService)
    }

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            interceptors().add(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            addInterceptor { chain ->
                val timestamp = System.currentTimeMillis().toString()
                val initialKey = timestamp +
                    BuildConfig.MARVEL_API_PRIVATE_KEY +
                    BuildConfig.MARVEL_API_PUBLIC_KEY
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("apikey", "2b756feb464727fc081683c4a4b6c344")
                    .addQueryParameter("hash", generateMd5Hash(initialKey))
                    .addQueryParameter("ts", timestamp)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
        }.build()
    }

    private fun generateMd5Hash(initialKey: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(initialKey.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }
}
