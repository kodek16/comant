package com.psenchanka.comant.service

import com.psenchanka.comant.model.Course

interface CourseService {
    fun save(course: Course)
    fun findAll(): List<Course>
    fun findById(id: Int): Course?
    fun update(course: Course)
}