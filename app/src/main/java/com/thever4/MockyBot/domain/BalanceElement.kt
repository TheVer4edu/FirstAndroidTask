package com.thever4.MockyBot.domain

import com.thever4.MockyBot.adapter.Adapter

data class BalanceElement(
    var accountId: String,
    var balance: String,
    var nextMonthPayment: String,
) : RootElement(Adapter.ViewHolderType.TYPE_ACCOUNT)
