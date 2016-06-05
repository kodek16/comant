package com.psenchanka.comant.dto

import com.psenchanka.comant.model.User

class DetailedUserDto(
        username: String,
        firstname: String,
        lastname: String,
        role: String,
        var instructedCourses: List<BasicCourseDto>,
        var listenedCourses: List<BasicCourseDto>)
    : BasicUserDto(username, firstname, lastname, role) {

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