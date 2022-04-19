package com.thever4.MockyBot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thever4.MockyBot.R
import com.thever4.MockyBot.domain.nested.NestedElement
import com.thever4.MockyBot.domain.nested.TariffElement
import com.thever4.MockyBot.domain.nested.UserdataElement
import com.thever4.MockyBot.adapter.item.HeaderViewHolder
import com.thever4.MockyBot.adapter.item.nested.TariffViewHolder
import com.thever4.MockyBot.adapter.item.nested.UserdataViewHolder

class NestedAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewHolderType {
        TYPE_TARIFF,
        TYPE_USERDATA,
    }

    private var list: MutableList<NestedElement> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ViewHolderType.TYPE_TARIFF.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_tariff, parent, false)
                TariffViewHolder(view)
            }
            ViewHolderType.TYPE_USERDATA.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_userdata, parent, false)
                UserdataViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_header, parent, false)
                HeaderViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is TariffViewHolder -> {
                val tariffElement = list[position] as TariffElement
                holder.name = tariffElement.name
                holder.description = tariffElement.description
                holder.amount = tariffElement.amount
                holder.updateView()
            }
            is UserdataViewHolder -> {
                val userdataElement = list[position] as UserdataElement
                holder.content = userdataElement.content
                holder.drawableId = userdataElement.image
                holder.updateView()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].basicType.ordinal
    }

    fun reload(list: MutableList<NestedElement>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

}