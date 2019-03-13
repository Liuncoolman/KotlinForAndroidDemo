package com.android.kotlinapp.liun.utils


import android.graphics.Bitmap
import android.graphics.Matrix

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation

import java.security.MessageDigest

/**
 * 旋转变换
 */

class RotateTransformation(rotateRotationAngle: Float) : BitmapTransformation() {

    //旋转默认0
    private var rotateRotationAngle = 0f

    init {
        this.rotateRotationAngle = rotateRotationAngle
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val matrix = Matrix()
        //旋转
        matrix.postRotate(rotateRotationAngle)
        //生成新的Bitmap
        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.width, toTransform.height, matrix, true)

    }


    //    @Override
    //    public String getId() {
    //        return rotateRotationAngle + "";
    //    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {

    }
}
