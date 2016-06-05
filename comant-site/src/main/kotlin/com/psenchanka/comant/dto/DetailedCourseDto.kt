package com.psenchanka.comant.dto

import com.psenchanka.comant.model.Course
import java.time.LocalDate

class DetailedCourseDto(
        id: Int?,
        name: String,
        description: String,
        startsOn: LocalDate,
        endsOn: LocalDate,
        var instructors: List<BasicUserDto>,
        var listeners: List<BasicUserDto>)
    : BasicCourseDto(id, name, description, startsOn, endsOn) {

    companion object {
        fun from(course: Course) = DetailedCourseDto(
                course.id,
                course.name,
                course.description,
                course.startsOn,
                course.endsOn,
                course.instructors.map { BasicUserDto.from(it) },
                course.listeners.map { BasicUserDto.from(it) })
    }
}