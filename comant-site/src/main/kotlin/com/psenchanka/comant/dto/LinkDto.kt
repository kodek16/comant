package com.psenchanka.comant.dto

import com.psenchanka.comant.model.Link

data class LinkDto(
        var url: String,
        var name: String,
        var comment: String?
) {
    companion object {
        fun from(link: Link) = LinkDto(
                link.url,
                link.name,
                link.comment)
    }
}