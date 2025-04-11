package com.deekho.app.utils

import android.content.Intent
import android.text.Html
import android.text.Spanned
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.bumptech.glide.Glide

object ViewExtensions {

    fun ImageView.loadImage(
        imageUrl: Any?,
        errorResId: Int?
    ) {

        if (context == null) return
        if (imageUrl == null) return
        if (errorResId == null) return

        Glide.with(context)
            .load(imageUrl)
            .thumbnail(0.3f)
            .placeholder(errorResId)
            .error(errorResId)
            .into(this)
    }


    fun setLayoutEdgeTop(view: View, addOnMargin: Int = 0){
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top + addOnMargin
            }
            WindowInsetsCompat.CONSUMED
        }
    }

    fun setLayoutEdgeBottom(view: View, addOnMargin: Int = 0){
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = insets.bottom + addOnMargin
            }
            WindowInsetsCompat.CONSUMED
        }
    }

    fun AppCompatActivity.switchActivityWithIntent(
        targetActivity: AppCompatActivity,
        intentExtras: Intent.() -> Unit = {}
    ) {
        val intent = Intent(this, targetActivity::class.java)
        intent.apply(intentExtras) // Apply any additional data
        startActivity(intent)
    }

    fun formatTitleValue(title: String, value: String): Spanned {
        val formatted = "<b>$title:</b> $value"
        return Html.fromHtml(formatted, Html.FROM_HTML_MODE_LEGACY)
    }
}