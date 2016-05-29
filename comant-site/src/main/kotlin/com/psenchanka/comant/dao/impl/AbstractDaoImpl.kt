package com.psenchanka.comant.dao.impl

import com.psenchanka.comant.dao.AbstractDao
import org.hibernate.Session
import org.hibernate.SessionFactory
import java.io.Serializable

open class AbstractDaoImpl<T, Id : Serializable>(val sessionFactory: SessionFactory,
                                                 val clazz: java.lang.Class<*>) : AbstractDao<T, Id> {

    protected val session: Session
        get() = sessionFactory.currentSession

    override fun save(t: T) = session.persist(t)

    @Suppress("UNCHECKED_CAST")
    override fun findAll() = session.createCriteria(clazz).list() as List<T>

    @Suppress("UNCHECKED_CAST")
    override fun findById(id: Id) = session.get(clazz, id) as T?

    override fun update(t: T) = session.update(t)
}