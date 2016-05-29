package com.psenchanka.comant.dto

import com.psenchanka.comant.model.User

data class BasicUserDto(
        var username: String,
        var firstname: String,
        var lastname: String,
        var role: String) {

    companion object {
        fun from(user: User): BasicUserDto {
            return BasicUserDto(
                    user.username,
                    user.firstname,
                    user.lastname,
                    user.role.toString().toLowerCase())
        }
    }
}