package com.thever4.MockyBot.adapter.item.nested

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.thever4.MockyBot.R
import java.lang.ref.WeakReference

class TariffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: WeakReference<View> = WeakReference(itemView)

    private var nameTextView: MaterialTextView? = null
    private var descriptionTextView: MaterialTextView? = null
    private var amountTextView: MaterialTextView? = null

    var name = ""
    var description = ""
    var amount = ""

    init {
        findView()
    }

    private fun findView() {
        nameTextView = view.get()?.findViewById(R.id.tariff_name)
        descriptionTextView = view.get()?.findViewById(R.id.tariff_description)
        amountTextView = view.get()?.findViewById(R.id.tariff_cost)
    }

    fun updateView() {
        nameTextView?.text = name
        descriptionTextView?.text = description
        amountTextView?.text = amount
    }
}