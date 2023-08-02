package com.pixabay.viewer.screens.imagedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.pixabay.viewer.R
import com.pixabay.viewer.databinding.FragmentImageDetailsBinding
import com.pixabay.viewer.util.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ImageDetailsFragment : Fragment() {

    private val args: ImageDetailsFragmentArgs by navArgs()
    private lateinit var dataBinding: FragmentImageDetailsBinding
    @Inject lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_details, container, false)
        dataBinding.hit = args.hit

        imageLoader.loadImage(args.hit.largeImageURL, dataBinding.imageViewDetail)

        return dataBinding.root
    }
}