package com.pixabay.viewer.screens.imageslist

import com.pixabay.viewer.models.Hit

interface ImageOnClickListener {
    fun onImageItemClicked( hit: Hit)
}