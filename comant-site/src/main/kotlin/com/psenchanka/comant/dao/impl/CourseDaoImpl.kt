package com.psenchanka.comant.dao.impl

import com.psenchanka.comant.dao.CourseDao
import com.psenchanka.comant.model.Course
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository("courseDao")
open class CourseDaoImpl @Autowired constructor(sessionFactory: SessionFactory)
         : CourseDao, AbstractDaoImpl<Course, Int>(sessionFactory, Course::class.java)