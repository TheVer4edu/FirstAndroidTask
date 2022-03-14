package com.thever4.MockyBot.adapter.item

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thever4.MockyBot.R
import com.thever4.MockyBot.adapter.DividerItemDecorator
import com.thever4.MockyBot.adapter.NestedAdapter
import com.thever4.MockyBot.adapter.domain.nested.NestedElement
import java.lang.ref.WeakReference

class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: WeakReference<View> = WeakReference(itemView)
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: NestedAdapter

    private var cardRecycler: RecyclerView? = null
    private var cardRecyclerConstraint: ConstraintLayout? = null

    var data: MutableList<NestedElement> = mutableListOf()
    var color = "#FFFFFF"

    init {
        findView()
        initList()
    }

    private fun findView() {
        cardRecycler = view.get()?.findViewById(R.id.card_recycler)
        cardRecyclerConstraint = view.get()?.findViewById(R.id.card_recycler_constraint)
    }

    fun updateView() {
        cardRecycler?.post {
            adapter.reload(data)
        }
    }

    private fun initList() {
        adapter = NestedAdapter()

        layoutManager = LinearLayoutManager(view.get()?.context)
        cardRecycler?.layoutManager = layoutManager
        cardRecycler?.adapter = adapter

        val dividerItemDecoration =
            DividerItemDecorator(view.get()?.resources!!.getDrawable(R.drawable.divider_drawable))
        cardRecycler?.addItemDecoration(dividerItemDecoration)

        cardRecyclerConstraint?.background?.setColorFilter(Color.parseColor(color), PorterDuff.Mode.DARKEN)
        cardRecyclerConstraint?.invalidate()
    }
}