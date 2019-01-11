package com.myandroid.utils.collections

import android.graphics.*
import android.widget.ImageView
import android.net.Uri
import com.squareup.picasso.Picasso
import id.zelory.compressor.Compressor
import java.io.File
import android.graphics.Paint.FILTER_BITMAP_FLAG
import com.myandroid.utils.BaseApp
import com.myandroid.utils.R

class ImageUtil {

    companion object {
        val thumbSize = 256
    }

    fun load(url: String?, imageView: ImageView?) {
        Picasso.get()
            .load(url)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView)
    }

    fun load(uri: Uri?, imageView: ImageView?) {
        Picasso.get()
            .load(uri)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView)
    }

    fun thumbLoad(url: String?, imageView: ImageView?) {
        Picasso.get()
            .load(url)
            .resize(thumbSize, thumbSize)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView)
    }

    fun thumbLoad(uri: Uri?, imageView: ImageView?) {
        Picasso.get()
            .load(uri)
            .resize(thumbSize, thumbSize)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView)
    }

    fun getCompressedImage(file: File?, maxSize: Int): File {
        return Compressor(BaseApp.context)
            .setMaxWidth(maxSize)
            .setMaxHeight(maxSize)
            .setDestinationDirectoryPath(BaseApp.context.externalCacheDir?.toString())
            .compressToFile(file)
    }

    fun scaleBitmap(bitmap: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        val scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888)

        val scaleX = newWidth / bitmap.width.toFloat()
        val scaleY = newHeight / bitmap.height.toFloat()
        val pivotX = 0f
        val pivotY = 0f

        val scaleMatrix = Matrix()
        scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY)

        val canvas = Canvas(scaledBitmap)
        canvas.matrix = scaleMatrix
        canvas.drawBitmap(bitmap, 0F, 0F, Paint(FILTER_BITMAP_FLAG))

        return scaledBitmap
    }
}