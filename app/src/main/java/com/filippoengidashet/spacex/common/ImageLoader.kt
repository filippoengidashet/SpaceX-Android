package com.filippoengidashet.spacex.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.filippoengidashet.spacex.R
import javax.inject.Inject

/**
 * @author Filippo 09/11/2021
 */
class ImageLoader @Inject constructor(
    private val context: Context,
) {

    fun load(url: Any, target: ImageView) {
        Glide.with(context)
            .load(url)
            .transition(withCrossFade())
            .error(R.drawable.ic_failed_image_load)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(target)
    }
}
