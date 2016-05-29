package com.psenchanka.comant.dao

import java.io.Serializable

interface AbstractDao<T, Id : Serializable> {
    fun save(t: T)
    fun findAll(): List<T>
    fun findById(id: Id): T?
    fun update(t: T)
}