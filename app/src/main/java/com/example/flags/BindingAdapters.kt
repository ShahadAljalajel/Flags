package com.example.flags

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.flags.network.Flag
import com.example.flags.overview.ApiStatus


@BindingAdapter("flag")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl.let {
        val imageLoader = ImageLoader.Builder(imgView.context)
            .componentRegistry { add(SvgDecoder(imgView.context)) }
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .build()

        val request = ImageRequest.Builder(imgView.context)
            .data(imgUrl)
            .target(imgView)
            .build()

        imageLoader.enqueue(request)
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

@BindingAdapter("names")
fun bindNames(NameText: TextView, text: String?) {
    NameText.text = text

}

@BindingAdapter("ApiStatus")
fun bindStatus(
    statusImageView: ImageView,
    status: ApiStatus?,
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