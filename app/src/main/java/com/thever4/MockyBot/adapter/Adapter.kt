package com.thever4.MockyBot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thever4.MockyBot.R
import com.thever4.MockyBot.adapter.domain.*
import com.thever4.MockyBot.adapter.item.BalanceViewHolder
import com.thever4.MockyBot.adapter.item.HeaderViewHolder
import com.thever4.MockyBot.adapter.item.RecyclerViewHolder

class Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewHolderType {
        TYPE_HEADER,
        TYPE_ACCOUNT,
        TYPE_CARD_RECYCLER,
    }

    private var list: MutableList<RootElement> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ViewHolderType.TYPE_HEADER.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_header, parent, false)
                HeaderViewHolder(view)
            }
            ViewHolderType.TYPE_ACCOUNT.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_balance, parent, false)
                BalanceViewHolder(view)
            }
            ViewHolderType.TYPE_CARD_RECYCLER.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recycler, parent, false)
                RecyclerViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_header, parent, false)
                HeaderViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is HeaderViewHolder -> {
                val headerElement = list[position] as HeaderElement
                holder.content = headerElement.text
                holder.headerSize = when(headerElement.type) {
                    HeaderType.HEADER_CAPITAL -> HeaderViewHolder.HeaderSize.HEADER_CAPITAL
                    HeaderType.HEADER_CATEGORY -> HeaderViewHolder.HeaderSize.HEADER_CATEGORY
                }
                holder.updateView()
            }
            is BalanceViewHolder -> {
                val balanceElement = list[position] as BalanceElement
                holder.balance = balanceElement.balance
                holder.personalAccountId = balanceElement.accountId
                holder.billNextMonth = balanceElement.nextMonthPayment
                holder.updateView()
            }
            is RecyclerViewHolder -> {
                val recyclerElement = list[position] as RecyclerElement
                holder.data = recyclerElement.nestedList
                holder.color = recyclerElement.backgroundColor
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

    fun reload(list: MutableList<RootElement>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

}