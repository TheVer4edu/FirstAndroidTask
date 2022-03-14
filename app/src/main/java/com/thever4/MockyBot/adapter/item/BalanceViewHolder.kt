package com.thever4.MockyBot.adapter.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.thever4.MockyBot.R
import java.lang.ref.WeakReference

class BalanceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private var view: WeakReference<View> = WeakReference(itemView)

    private var accountId: MaterialTextView? = null
    private var accountNextMonthAmount: MaterialTextView? = null
    private var accountBalance: MaterialTextView? = null

    var balance = ""
    var personalAccountId = ""
    var billNextMonth = ""

    init {
        findView()
    }

    private fun findView() {
        accountId = view.get()?.findViewById(R.id.account_id)
        accountNextMonthAmount = view.get()?.findViewById(R.id.account_next_month_amount)
        accountBalance = view.get()?.findViewById(R.id.account_balance)
    }

    fun updateView() {
        accountId?.text = personalAccountId
        accountNextMonthAmount?.text = billNextMonth
        accountBalance?.text = balance
    }
}