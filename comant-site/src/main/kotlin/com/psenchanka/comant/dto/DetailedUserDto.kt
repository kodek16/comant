package com.psenchanka.comant.dto

import com.psenchanka.comant.model.User

data class DetailedUserDto(
        var username: String,
        var firstname: String,
        var lastname: String,
        var role: String,
        var instructedCourses: List<BasicCourseDto>,
        var listenedCourses: List<BasicCourseDto>) {

    companion object {
        fun from(user: User) = DetailedUserDto(
                    user.username,
                    user.firstname,
                    user.lastname,
                    user.role.toString().toLowerCase(),
                    user.coursesInstructed.map { BasicCourseDto.from(it) },
                    user.coursesListened.map { BasicCourseDto.from(it) });
    }
}