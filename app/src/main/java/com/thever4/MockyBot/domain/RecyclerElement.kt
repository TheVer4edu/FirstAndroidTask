package com.thever4.MockyBot.domain

import com.thever4.MockyBot.adapter.Adapter
import com.thever4.MockyBot.domain.nested.NestedElement

data class RecyclerElement(
    var nestedList: MutableList<NestedElement>,
    var backgroundColor: String = "#FFFFFF"
) : RootElement(Adapter.ViewHolderType.TYPE_CARD_RECYCLER)
