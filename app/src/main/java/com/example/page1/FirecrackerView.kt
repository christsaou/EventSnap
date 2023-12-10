package com.example.page1
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class FirecrackerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()

    init {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw your firecracker here
        canvas.drawCircle(width / 2f, height / 2f, 20f, paint)
    }
    fun animateFirecracker() {
        val scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 1f, 2f)
        scaleXAnimator.duration = 1000
        scaleXAnimator.interpolator = AccelerateDecelerateInterpolator()

        val scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 1f, 2f)
        scaleYAnimator.duration = 1000
        scaleYAnimator.interpolator = AccelerateDecelerateInterpolator()

        scaleXAnimator.start()
        scaleYAnimator.start()
    }
}

