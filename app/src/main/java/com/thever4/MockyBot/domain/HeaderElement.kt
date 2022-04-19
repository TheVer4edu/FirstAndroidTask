package com.thever4.MockyBot.domain

import com.thever4.MockyBot.adapter.Adapter

data class HeaderElement(
    val text: String,
    val type: HeaderType,
) : RootElement(Adapter.ViewHolderType.TYPE_HEADER)

enum class HeaderType {
    HEADER_CAPITAL,
    HEADER_CATEGORY,
}