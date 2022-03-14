package com.thever4.MockyBot.adapter.item.nested

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.thever4.MockyBot.R
import java.lang.ref.WeakReference

class UserdataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: WeakReference<View> = WeakReference(itemView)

    private var imageView: ImageView? = null
    private var contentTextView: MaterialTextView? = null

    var content = ""
    var drawableId = -1

    init {
        findView()
    }

    private fun findView() {
        contentTextView = view.get()?.findViewById(R.id.userdata_content)
        imageView = view.get()?.findViewById(R.id.userdata_picture)
    }

    fun updateView() {
        contentTextView?.text = content
        imageView?.setImageResource(drawableId)
    }
}