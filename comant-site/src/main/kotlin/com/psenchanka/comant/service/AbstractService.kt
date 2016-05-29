package com.psenchanka.comant.service

import java.io.Serializable

interface AbstractService<T, Id : Serializable> {
    fun save(t: T)
    fun findAll(): List<T>
    fun findById(id: Id): T?
    fun update(t: T)
}