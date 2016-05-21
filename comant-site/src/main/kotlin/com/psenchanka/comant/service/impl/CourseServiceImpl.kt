package com.psenchanka.comant.service.impl

import com.psenchanka.comant.dao.CourseDao
import com.psenchanka.comant.model.Course
import com.psenchanka.comant.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("courseService")
@Transactional
open class CourseServiceImpl @Autowired constructor(val courseDao: CourseDao) : CourseService {
    override fun save(course: Course) = courseDao.save(course)
    override fun findAll() = courseDao.findAll()
    override fun findById(id: Int) = courseDao.findById(id)
    override fun update(course: Course) = courseDao.update(course)
}