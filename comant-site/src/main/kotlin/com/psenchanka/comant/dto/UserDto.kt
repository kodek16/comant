package com.psenchanka.comant.dto

import com.psenchanka.comant.model.User

data class UserDto(var username: String, var firstname: String, var lastname: String, var role: String) {

    companion object {
        fun from(user: User): UserDto {
            return UserDto(user.username, user.firstname, user.lastname, user.role.toString().toLowerCase())
        }
    }
}