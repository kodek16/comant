package com.psenchanka.comant.service.impl

import com.psenchanka.comant.dao.AbstractDao
import com.psenchanka.comant.service.AbstractService
import java.io.Serializable

open class AbstractServiceImpl<T, Id : Serializable>(val dao: AbstractDao<T, Id>) : AbstractService<T, Id> {

    override fun save(t: T) = dao.save(t)
    override fun findAll() = dao.findAll()
    override fun findById(id: Id) = dao.findById(id)
    override fun update(t: T) = dao.update(t)
}