package com.psenchanka.comant.service

import com.psenchanka.comant.model.User

interface UserService {
    fun save(user: User);
    fun findAll(): List<User>;
    fun findByUsername(username: String): User?;
    fun update(user: User);

    fun generateAccessToken(user: User): String;
}