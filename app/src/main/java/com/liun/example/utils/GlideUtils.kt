package com.liun.example.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView
import com.android.kotlinapp.liun.utils.RotateTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.liun.example.MyApplication
import com.liun.example.R

object GlideUtils {
    lateinit var options: RequestOptions

    fun showGif(url: Any, imageView: ImageView) {
        options = RequestOptions()
                .error(R.mipmap.kakalib_scan_ray)
                .placeholder(R.mipmap.kakalib_scan_ray)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        Glide.with(MyApplication.get()!!).asGif().load(url).apply(options).into(imageView)
    }

    fun showGifOne(url: Any, imageView: ImageView) {
        options = RequestOptions()
                .error(R.mipmap.kakalib_scan_ray)
                .placeholder(R.mipmap.kakalib_scan_ray)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        Glide.with(MyApplication.get()!!).load(url).apply(options).into(object : SimpleTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                if (resource is GifDrawable) {
                    resource.setLoopCount(1)
                    imageView.setImageDrawable(resource)
                    resource.start()
                }
            }
        })
    }

    fun showImage(url: Any, imageView: ImageView) {
        options = RequestOptions()
                .error(R.mipmap.kakalib_scan_ray)
                .placeholder(R.mipmap.kakalib_scan_ray)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(MyApplication.get()!!).asBitmap().load(url).apply(options).into(imageView)
    }

    //设置指定的图片作为默认图
    fun showNormalImage(url: Any, imageView: ImageView, empty: Int) {
        options = RequestOptions()
                .error(empty)
                .placeholder(empty)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(MyApplication.get()!!).asBitmap().load(url).apply(options).into(imageView)
    }

    //带角度旋转的
    fun showNormalImageNoCache(url: Any, imageView: ImageView, empty: Int) {
        options = RequestOptions()
                .error(empty)
                .placeholder(empty)
                .centerCrop()
                .transform(RotateTransformation(0f))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
        Glide.with(MyApplication.get()!!).asBitmap().load(url).apply(options).into(imageView)
    }

    //加载圆形图片
    fun showNormalCircleImage(url: String, imageView: ImageView, empty: Int) {
        options = RequestOptions()
                .error(empty)
                .placeholder(empty)
                .centerCrop()
        Glide.with(MyApplication.get()!!).asBitmap().load(url).apply(options).into(object : BitmapImageViewTarget(imageView) {
            override fun setResource(resource: Bitmap?) {
                val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(MyApplication.get()!!.resources, resource)
                circularBitmapDrawable.isCircular = true
                imageView.setImageDrawable(circularBitmapDrawable)
            }
        })
    }
}
