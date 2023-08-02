package com.pixabay.viewer.screens.imageslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pixabay.viewer.R
import com.pixabay.viewer.databinding.ItemImageBinding
import com.pixabay.viewer.models.Hit

class ImagesListAdapter(
    private val imagesList: ArrayList<Hit>,
    private val onImageClickListener: Listener
) :
    RecyclerView.Adapter<ImagesListAdapter.ImageViewHolder>(), ImageOnClickListener {

    interface Listener {
        fun onImageClicked(clickedImage: Hit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemImageBinding>(inflater, R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount() = imagesList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.view.image = imagesList[position]
        holder.view.listener = this
    }

    fun updateImageList(images: List<Hit>) {
        imagesList.clear()
        imagesList.addAll(images)
        notifyDataSetChanged()
    }

    class ImageViewHolder(var view: ItemImageBinding) : RecyclerView.ViewHolder(view.root)

    override fun onImageItemClicked(hit: Hit) {
        onImageClickListener.onImageClicked(hit)
    }
}
