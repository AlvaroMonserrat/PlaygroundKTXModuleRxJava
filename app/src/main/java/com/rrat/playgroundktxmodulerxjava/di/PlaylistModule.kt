package com.rrat.playgroundktxmodulerxjava.di

import com.rrat.playgroundktxmodulerxjava.data.service.PlaylistAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PlaylistModule {

    @Provides
    @Singleton
    fun playlistAPI(retrofit: Retrofit): PlaylistAPI{
        return retrofit.create(PlaylistAPI::class.java)
    }

    @Provides
    @Singleton
    fun retrofit(): Retrofit{
        val client = OkHttpClient()
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.145:3000/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}