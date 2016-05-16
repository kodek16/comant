package com.psenchanka.comant.dao

import com.psenchanka.comant.model.User

interface UserDao {
    fun save(user: User)
    fun findAll(): List<User>
    fun findByUsername(username: String): User?
    fun update(user: User)
}