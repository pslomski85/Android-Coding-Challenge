package com.pixabay.viewer.screens.imageslist

import com.pixabay.viewer.models.ImagesListResponseSchema
import com.pixabay.viewer.models.SearchParameters
import com.pixabay.viewer.network.PixabayAPI
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchImagesListUseCase @Inject constructor(private val pixabayAPI: PixabayAPI) {

    sealed class Result {
        data class Success(val images: ImagesListResponseSchema) : Result()
        object Failure: Result()
    }

    suspend fun fetchImages(parameters: SearchParameters): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = pixabayAPI.imagesList(parameters.searchQuery, parameters.page, parameters.limit)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }
}