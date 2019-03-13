package com.android.kotlinapp.liun.utils

import android.graphics.Camera
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * Created by Caojing on 2018/9/17.
 * 你不是一个人在战斗
 */
class Rotate3dAnimation
/**
 * 创建3D旋转动画
 *
 * @param fromDegrees the start angle of the 3D rotation
 * @param toDegrees   the end angle of the 3D rotation
 * @param centerX     the X center of the 3D rotation
 * @param centerY     the Y center of the 3D rotation
 * @param depthZ      the Z depth of the 3D rotation
 * @param rotateAxis  the rotate axis of the 3D rotation
 * @param reverse     true if the translation should be reversed, false otherwise
 */
(private val mFromDegrees: Float, private val mToDegrees: Float,
 private val mCenterX: Float, private val mCenterY: Float, private val mDepthZ: Float, private val mRotateAxis: Byte?  // 0：X轴  1：Y轴  2：Z轴
 , private val mReverse: Boolean) : Animation() {
    private var mCamera: Camera? = null

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        mCamera = Camera()
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val fromDegrees = mFromDegrees
        val degrees = fromDegrees + (mToDegrees - fromDegrees) * interpolatedTime

        val centerX = mCenterX
        val centerY = mCenterY
        val camera = mCamera

        val matrix = t.matrix
        // 将当前的摄像头位置保存下来，以便变换进行完成后恢复成原位
        camera!!.save()
        if (mReverse) {
            // z的偏移会越来越大。这就会形成这样一个效果，view从近到远
            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime)
        } else {
            // z的偏移会越来越小。这就会形成这样一个效果，我们的View从一个很远的地方向我们移过来，越来越近，最终移到了我们的窗口上面
            camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime))
        }
        // 是给我们的View加上旋转效果，在移动的过程中，视图还会以XYZ轴为中心进行旋转。
        if (ROTATE_X_AXIS == mRotateAxis) {
            camera.rotateX(degrees)
        } else if (ROTATE_Y_AXIS == mRotateAxis) {
            camera.rotateY(degrees)
        } else {
            camera.rotateZ(degrees)
        }

        // 这个是将我们刚才定义的一系列变换应用到变换矩阵上面，调用完这句之后，我们就可以将camera的位置恢复了，以便下一次再使用。
        camera.getMatrix(matrix)
        // camera位置恢复
        camera.restore()

        // 下面两句是为了动画是以View中心为旋转点
        matrix.preTranslate(-centerX, -centerY)
        matrix.postTranslate(centerX, centerY)
    }

    companion object {

        val ROTATE_X_AXIS: Byte = 0x00
        val ROTATE_Y_AXIS: Byte = 0x01
        val ROTATE_Z_AXIS: Byte = 0x02
    }
}

