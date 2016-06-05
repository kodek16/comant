package com.psenchanka.comant.dto

import com.psenchanka.comant.model.Class
import java.time.LocalDateTime

open class DetailedClassDto(
        id: Int,
        startsOn: LocalDateTime,
        endsOn: LocalDateTime,
        name: String?,
        description: String?,
        var course: BasicCourseDto,
        var links: List<LinkDto>)
    : BasicClassDto(id, startsOn, endsOn, name, description) {

    companion object {
        fun from(class_: Class) = DetailedClassDto(
                class_.id!!,
                class_.startsOn,
                class_.endsOn,
                class_.name,
                class_.description,
                BasicCourseDto.from(class_.course),
                class_.links.sortedBy { it.order }.map { LinkDto.from(it) })
    }
}