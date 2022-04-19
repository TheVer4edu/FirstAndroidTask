package com.thever4.MockyBot.domain.nested

import com.thever4.MockyBot.adapter.NestedAdapter

data class TariffElement(
    var name: String,
    var description: String,
    var amount: String,
) : NestedElement(NestedAdapter.ViewHolderType.TYPE_TARIFF)