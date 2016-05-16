package com.psenchanka.comant.auth

import com.psenchanka.comant.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute
import javax.servlet.http.HttpServletRequest

@ControllerAdvice(basePackages = arrayOf("com.psenchanka.comant"))
class AuthenticationControllerAdvice {

    @Autowired
    private lateinit var userService: UserService

    @ModelAttribute
    fun addAuthenticatedUserAttribute(model: Model, req: HttpServletRequest) {
        val userName = req.getAttribute("com.psenchanka.comant.authenticatedUser")

        val user = if (userName != null) userService.findByUsername(userName as String) else null
        model.addAttribute("authenticatedUser", user)
    }
}