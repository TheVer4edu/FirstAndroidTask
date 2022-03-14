package com.thever4.MockyBot.adapter.domain.nested

import com.thever4.MockyBot.adapter.NestedAdapter

data class UserdataElement(
    var image: Int,
    var content: String,
) : NestedElement(NestedAdapter.ViewHolderType.TYPE_USERDATA)