package com.psenchanka.comant.controller

import com.psenchanka.comant.auth.BadCredentialsException
import com.psenchanka.comant.dto.AuthenticationTokenDto
import com.psenchanka.comant.dto.AuthorizationDto
import com.psenchanka.comant.service.UserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/login")
open class LoginController @Autowired constructor(val userService: UserService) {

    @RequestMapping("", method = arrayOf(RequestMethod.POST))
    @ApiOperation("Log in",
                  notes = "Returns a JWT token that can be used for accessing protected API methods.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 403, message = "Bad credentials")
    )
    @Transactional
    open fun login(
            @ApiParam("User credentials") @RequestBody credentials: AuthorizationDto
    ) : AuthenticationTokenDto {
        val (username, password) = credentials
        val user = userService.findById(username)

        if (user != null && user.passwordMatches(password)) {
            return AuthenticationTokenDto(userService.generateAccessToken(user))
        } else {
            throw BadCredentialsException()
        }
    }
}