package com.psenchanka.comant.auth

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute
import javax.servlet.http.HttpServletRequest

@ControllerAdvice(basePackages = arrayOf("com.psenchanka.comant"))
class AuthenticationControllerAdvice {

    @ModelAttribute
    fun addAuthenticatedUserAttribute(model: Model, req: HttpServletRequest) {
        val userName = req.getAttribute("com.psenchanka.comant.authenticatedUser")
        model.addAttribute("myUsername", userName)
    }
}