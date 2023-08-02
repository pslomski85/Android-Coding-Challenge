package com.pixabay.viewer.screens.imageslist

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixabay.viewer.models.ImagesListResponseSchema
import com.pixabay.viewer.models.SearchParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesListViewModel @Inject constructor(
    private val fetchImagesListUseCase : FetchImagesListUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _images: MutableLiveData<ImagesListResponseSchema> = savedStateHandle.getLiveData("images")
    val images: LiveData<ImagesListResponseSchema> get() = _images

    private var parameters: MutableLiveData<SearchParameters> = savedStateHandle.getLiveData("params")

    init{
        parameters.value = SearchParameters()
    }

    private fun fetchImages() = viewModelScope.launch {
        val result = fetchImagesListUseCase.fetchImages(parameters.value!!)
        if (result is FetchImagesListUseCase.Result.Success) {
            if(_images.value == null || parameters.value!!.changed) {
                _images.value = result.images
            } else {
                val oldArticles = _images.value?.hits
                val newArticles = result.images.hits
                oldArticles?.addAll(newArticles)
            }
            _images.postValue(_images.value)
        } else {
            throw RuntimeException("fetch failed")
        }
    }

    fun addPage() {
        parameters.value?.let {
            it.addPage(images.value!!.totalHits)
            fetchImages()
        }
    }

    fun setupQueryParameters(newText: String) {
        parameters.value?.searchQuery = newText
        fetchImages()
    }

    fun getQuery(): String? {
        if (parameters.value == null) parameters.value = SearchParameters()
        return parameters.value?.searchQuery
    }
}
