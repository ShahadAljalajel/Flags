package com.example.flags

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import androidx.databinding.BindingAdapter
import com.example.flags.network.Flag
import com.example.flags.overview.ApiStatus


@BindingAdapter("flag")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Flag>?
) {
    val adapter = recyclerView.adapter as PhotoAdapter
    adapter.submitList(data)

}

@BindingAdapter("ApiStatus")
fun bindStatus(
    statusImageView: ImageView,
    status: ApiStatus?
) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)

        }

        else -> {
            statusImageView.visibility = View.GONE
        }
    }
}