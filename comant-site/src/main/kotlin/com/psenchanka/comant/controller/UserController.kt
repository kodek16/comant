package com.psenchanka.comant.controller

import com.psenchanka.comant.auth.NotAuthenticatedException
import com.psenchanka.comant.dto.DetailedUserDto
import com.psenchanka.comant.service.UserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore

@RestController
@RequestMapping("/api/users")
open class UserController @Autowired constructor(val userService: UserService) {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No user with given username")
    class UserNotFoundException : RuntimeException()

    @RequestMapping("/{username}", method = arrayOf(RequestMethod.GET))
    @ApiOperation("Get user data",
                  notes = "Returns detailed information about target user.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "No user with given username")
    )
    @Transactional
    open fun getUser(
            @ApiParam("Username of target user") @PathVariable username: String
    ): DetailedUserDto {
        val user = userService.findById(username)

        if (user != null) {
            return DetailedUserDto.from(user)
        } else {
            throw UserNotFoundException()
        }
    }

    @RequestMapping("/me", method = arrayOf(RequestMethod.GET))
    @ApiOperation("Get logged user data",
                  notes = "Returns detailed information about authenticated user. Same as /users/{myUsername}/.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 401, message = "Not authenticated")
    )
    @Transactional
    open fun getMe(
            @ApiIgnore @ModelAttribute("myUsername") myUsername: String?
    ): DetailedUserDto {
        val me = if (myUsername != null) userService.findById(myUsername) else null

        if (me != null) {
            return DetailedUserDto.from(me)
        } else {
            throw NotAuthenticatedException()
        }
    }
}