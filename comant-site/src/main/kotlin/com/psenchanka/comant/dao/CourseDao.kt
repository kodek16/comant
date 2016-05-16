package com.psenchanka.comant.dao

import com.psenchanka.comant.model.Course

interface CourseDao {
    fun save(course: Course)
    fun findAll(): List<Course>
    fun findById(id: Int): Course?
    fun update(course: Course)
}
