package com.psenchanka.comant.dto

import com.psenchanka.comant.model.Class
import java.time.LocalDateTime

data class DetailedClassDto(
        var id: Int,
        var startsOn: LocalDateTime,
        var endsOn: LocalDateTime,
        var name: String?,
        var description: String?,
        var course: BasicCourseDto,
        var links: List<LinkDto>,
        var absentListeners: List<BasicUserDto>,
        var grades: List<GradeDto>) {

    companion object {
        fun from(class_: Class) = DetailedClassDto(
                class_.id!!,
                class_.startsOn,
                class_.endsOn,
                class_.name,
                class_.description,
                BasicCourseDto.from(class_.course),
                class_.links.sortedBy { it.order }.map { LinkDto.from(it) },
                class_.absences.map { BasicUserDto.from(it) },
                class_.grades.map { GradeDto.from(it) })
    }
}