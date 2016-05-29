package com.psenchanka.comant.service

import com.psenchanka.comant.model.User

interface UserService : AbstractService<User, String> {
    fun generateAccessToken(user: User): String
}