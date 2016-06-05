package com.psenchanka.comant.dto

import com.psenchanka.comant.model.Class
import java.time.LocalDateTime

class InstructorClassDto(
        id: Int,
        startsOn: LocalDateTime,
        endsOn: LocalDateTime,
        name: String?,
        description: String?,
        course: BasicCourseDto,
        links: List<LinkDto>,
        var absentListeners: List<BasicUserDto>,
        var grades: List<GradeDto>)
    : DetailedClassDto(id, startsOn, endsOn, name, description, course, links) {

    companion object {
        fun from(class_: Class) = InstructorClassDto(
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