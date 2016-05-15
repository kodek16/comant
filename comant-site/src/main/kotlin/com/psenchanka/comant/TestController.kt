package com.psenchanka.comant

import com.psenchanka.comant.model.User
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    private val log = org.slf4j.LoggerFactory.getLogger(TestController::class.java);

    @RequestMapping("/doIt")
    fun doIt(@ModelAttribute("authenticatedUser") authenticatedUser: User?): String {
        return authenticatedUser?.username ?: "unauthorized";
    }
}