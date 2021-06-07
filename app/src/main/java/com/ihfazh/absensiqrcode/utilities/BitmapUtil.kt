package com.ihfazh.absensiqrcode.utilities

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View

class BitmapUtil {
    fun loadBitmapFromView(view: View): Bitmap {
        val specWidth = View.MeasureSpec.makeMeasureSpec(900, View.MeasureSpec.EXACTLY)
        view.measure(specWidth, specWidth)
        val questionWidth = view.measuredWidth

        val b = Bitmap.createBitmap(questionWidth, questionWidth, Bitmap.Config.ARGB_8888)
        val c = Canvas(b)
        c.drawColor(Color.WHITE)
        view.layout(view.left, view.top, view.right, view.bottom)
        view.draw(c)
        return b
    }
}