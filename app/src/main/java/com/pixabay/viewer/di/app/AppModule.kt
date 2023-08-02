package com.pixabay.viewer.di.app

import com.pixabay.viewer.Constants.PIXABAY_URL
import com.pixabay.viewer.network.PixabayAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(PIXABAY_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun pixabayApi(retrofit: Retrofit): PixabayAPI = retrofit.create(PixabayAPI::class.java)

}