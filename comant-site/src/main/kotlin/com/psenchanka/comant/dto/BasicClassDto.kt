package com.psenchanka.comant.dto

import com.psenchanka.comant.model.Class
import java.time.LocalDateTime

open class BasicClassDto(
        var id: Int?,
        var startsOn: LocalDateTime,
        var endsOn: LocalDateTime,
        var name: String?,
        var description: String?) {

    companion object {
        fun from(class_: Class) = BasicClassDto(
                class_.id,
                class_.startsOn,
                class_.endsOn,
                class_.name,
                class_.description)
    }
}