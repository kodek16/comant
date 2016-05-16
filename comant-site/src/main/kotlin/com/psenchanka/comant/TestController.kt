package com.psenchanka.comant

import com.psenchanka.comant.model.User
import com.psenchanka.comant.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController @Autowired constructor(val userService: UserService) {

    @RequestMapping("/doIt")
    fun doIt(@ModelAttribute("authenticatedUser") authenticatedUser: User?): String {
        return authenticatedUser?.username ?: "unauthorized"
    }

    @RequestMapping("devscripts/addAdmin")
    fun addAdmin() {
        val admin = User()
        admin.username = "admin"
        admin.password = User.encryptPassword("password")
        admin.firstname = "Admin"
        admin.lastname = "Smith"
        admin.role = User.Role.ADMIN
        userService.save(admin)
    }
}