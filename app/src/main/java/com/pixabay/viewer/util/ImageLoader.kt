package com.pixabay.viewer.util

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.pixabay.viewer.R
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ImageLoader @Inject constructor(private val activity: AppCompatActivity) {

    private val requestOptions = RequestOptions().centerInside().error(R.drawable.picture)

    fun loadImage(imageUrl: String, target: ImageView) {
        Glide.with(activity).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.DATA).apply(requestOptions).into(target)
    }
}


@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String?) {
    view.loadImageUrl(url)
}

fun ImageView.loadImageUrl(uri: String?) {
    val options = RequestOptions()
        .centerCrop()
        .error(R.drawable.picture)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}
