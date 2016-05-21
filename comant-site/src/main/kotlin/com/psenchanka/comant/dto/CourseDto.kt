package com.psenchanka.comant.dto

import com.psenchanka.comant.model.Course
import java.time.LocalDate

data class CourseDto(var name: String, var description: String, var startsOn: LocalDate, var endsOn: LocalDate) {
    companion object {
        fun from(course: Course) = CourseDto(course.name, course.description, course.startsOn, course.endsOn)
    }
}