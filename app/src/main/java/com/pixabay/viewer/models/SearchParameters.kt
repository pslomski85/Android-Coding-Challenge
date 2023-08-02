package com.pixabay.viewer.models

class SearchParameters {

    val changed: Boolean = true
    var searchQuery : String = DEFAULT_QUERY
    var page : Int = DEFAULT_PAGE_INDEX
    var limit : Int = DEFAULT_PAGE_LIMIT

    fun addPage(totalHits: Int) {
        if(page * limit < totalHits){
            page++
        }
    }

    companion object {
        const val DEFAULT_QUERY: String= "Fruits"
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_LIMIT = 20
    }
}
