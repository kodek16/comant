package com.psenchanka.comant.dto

import com.psenchanka.comant.model.Course
import java.time.LocalDate

data class DetailedCourseDto(
        var id: Int?,
        var name: String,
        var description: String,
        var startsOn: LocalDate,
        var endsOn: LocalDate,
        var instructors: List<BasicUserDto>,
        var listeners: List<BasicUserDto>) {

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