package com.example.makemytripapp
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout

class CurvedShapeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val arcColor = Color.parseColor("#FFE5E4E2") // Color for the arc
    private val arcOutlineColor = Color.parseColor("#FFF0F0F0") // Color for the arc outline

    private val paint = Paint().apply {
        color = arcColor
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val outlinePaint = Paint().apply {
        color = arcOutlineColor
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    private val arcRadius = 750f // Radius of the arc

    init {
        inflate(context, R.layout.curved_shape, this)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = measuredWidth.toFloat()
        val height = measuredHeight.toFloat()

        val path = Path().apply {
            moveTo(0f, height)
            lineTo(0f, height - arcRadius)
            quadTo(width / 2, height, width, height - arcRadius)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }
        canvas.drawPath(path, paint)

        // Draw outline for visual aid
        canvas.drawPath(path, outlinePaint)
    }
}
