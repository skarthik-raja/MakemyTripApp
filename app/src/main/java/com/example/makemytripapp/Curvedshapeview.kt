package com.example.makemytripapp  // Replace with your actual package name

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

class CurvedShapeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) { // Extend FrameLayout

    private val paint = Paint().apply {
        color = 0xFFF5F5F5.toInt() // Sandal with orange color in ARGB format
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val curveRadius = 800f // Adjust curve radius as desired

    private val imageView: ImageView
    private val textView: TextView

    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.curved_shape_content, this, true) as FrameLayout // Inflate with the FrameLayout


        imageView = view.findViewById(R.id.image_view)
        textView = view.findViewById(R.id.text_view)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = measuredWidth.toFloat()
        val height = measuredHeight.toFloat()

        // Draw a curved rectangle with rounded bottom corners
        val path = Path().apply {
            moveTo(0f, height)
            lineTo(0f, height - curveRadius)
            quadTo(width / 2, height, width, height - curveRadius)  // Use quadTo for quadratic Bézier curve
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }
        canvas.drawPath(path, paint)
    }
}
