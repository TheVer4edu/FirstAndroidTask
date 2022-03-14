package com.thever4.MockyBot.adapter.item

import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.thever4.MockyBot.R
import java.lang.ref.WeakReference

class HeaderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private var view: WeakReference<View> = WeakReference(itemView)

    private var textView: MaterialTextView? = null

    var content = ""
    var headerSize: HeaderSize = HeaderSize.HEADER_CATEGORY

    init {
        findView()
    }

    private fun findView() {
        textView = view.get()?.findViewById(R.id.header_title)
    }

    fun updateView() {
        textView?.text = content
        textView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, headerSize.size)
    }

    enum class HeaderSize(val size: Float) {
        HEADER_CAPITAL(28f),
        HEADER_CATEGORY(20f),
    }
}