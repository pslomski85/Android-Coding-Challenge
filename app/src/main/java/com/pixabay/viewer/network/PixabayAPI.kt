package com.pixabay.viewer.network

import com.pixabay.viewer.Constants
import com.pixabay.viewer.models.ImagesListResponseSchema
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/?key=" + Constants.PIXABAY_API_KEY + "&image_type=photo")
    suspend fun imagesList(@Query("q") searchQuery: String?, @Query("page") page: Int, @Query("per_page") size: Int): Response<ImagesListResponseSchema>


//    https://pixabay.com/api/?key=38475857-8dd0ab2f5c64f561cdd237455&image_type=photo&q=Fruits
//    https://pixabay.com/api/?key=38475857-8dd0ab2f5c64f561cdd237455&q=Fruits&image_type=photo
}