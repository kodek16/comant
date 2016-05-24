package com.psenchanka.comant.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.psenchanka.comant.model.Course
import java.time.LocalDate

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CourseDto(
        var name: String,
        var description: String,
        var startsOn: LocalDate,
        var endsOn: LocalDate,
        var instructors: List<UserDto>? = null,
        var listeners: List<UserDto>? = null
) {
    companion object {
        fun from(course: Course) = CourseDto(course.name, course.description, course.startsOn, course.endsOn)
    }
}