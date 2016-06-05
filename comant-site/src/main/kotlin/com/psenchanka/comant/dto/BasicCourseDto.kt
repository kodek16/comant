package com.psenchanka.comant.dto

import com.psenchanka.comant.model.Course
import java.time.LocalDate

open class BasicCourseDto(
        var id: Int?,
        var name: String,
        var description: String,
        var startsOn: LocalDate,
        var endsOn: LocalDate) {

    companion object {
        fun from(course: Course) = BasicCourseDto(
                course.id,
                course.name,
                course.description,
                course.startsOn,
                course.endsOn)
    }
}