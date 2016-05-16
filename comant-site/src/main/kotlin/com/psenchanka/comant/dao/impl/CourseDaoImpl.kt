package com.psenchanka.comant.dao.impl

import com.psenchanka.comant.dao.CourseDao
import com.psenchanka.comant.model.Course
import org.hibernate.SessionFactory
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository("courseDao")
open class CourseDaoImpl @Autowired constructor(val sessionFactory: SessionFactory) : CourseDao {

    override fun save(course: Course)
            = sessionFactory.currentSession
            .persist(course)

    @Suppress("UNCHECKED_CAST")
    override fun findAll()
            = sessionFactory.currentSession
            .createCriteria(Course::class.java)
            .list() as List<Course>

    override fun findById(id: Int)
            = sessionFactory.currentSession
            .createCriteria(Course::class.java)
            .add(Restrictions.eq("id", id))
            .uniqueResult() as Course?

    override fun update(course: Course)
            = sessionFactory.currentSession
            .update(course)
}
