package com.mert.countries.utils

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.mert.countries.R

fun ImageView.loadUrl(url: String?) {

    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(500)
        .placeholder(R.drawable.ic_baseline_image)
        .error(R.drawable.ic_baseline_error)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}
