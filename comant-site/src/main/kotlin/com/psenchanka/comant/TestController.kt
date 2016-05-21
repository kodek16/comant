package com.psenchanka.comant

import com.psenchanka.comant.model.Course
import com.psenchanka.comant.model.User
import com.psenchanka.comant.service.CourseService
import com.psenchanka.comant.service.UserService
import org.hibernate.Hibernate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
open class TestController @Autowired constructor(val userService: UserService, val courseService: CourseService) {

    @RequestMapping("devscripts/addAdmin")
    open fun addAdmin() {
        val admin = User()
        admin.username = "admin"
        admin.password = User.encryptPassword("password")
        admin.firstname = "Admin"
        admin.lastname = "Smith"
        admin.role = User.Role.ADMIN
        userService.save(admin)
    }

    @RequestMapping("devscripts/addCourse")
    open fun addCourse() {
        val course = Course()
        course.name = "Spring"
        course.description = "Introduction to Spring"
        course.startsOn = LocalDate.now()
        course.endsOn = LocalDate.now().plusDays(30)
        courseService.save(course)
    }

    @RequestMapping("devscripts/addListener")
    @Transactional
    open fun addListener() {
        val user = userService.findByUsername("admin")
        val course = courseService.findAll()[0]
        Hibernate.initialize(course.listeners)
        course.listeners.add(user!!)
        courseService.update(course)
        print(user.coursesListened)
    }
}