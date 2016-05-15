package com.psenchanka.comant.auth

import com.psenchanka.comant.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController @Autowired constructor(val userService: UserService) {

    @RequestMapping("/api/login")
    fun login(@RequestParam username: String, @RequestParam password: String): String {
        val user = userService.findByUsername(username);

        if (user != null && user.passwordMatches(password)) {
            return userService.generateAccessToken(user);
        } else {
            throw BadCredentialsException()
        }
    }
}