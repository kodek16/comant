package com.psenchanka.comant.controller

import com.psenchanka.comant.auth.NotAuthenticatedException
import com.psenchanka.comant.dto.CourseDto
import com.psenchanka.comant.dto.UserDto
import com.psenchanka.comant.service.UserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.hibernate.Hibernate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore

@RestController
open class UserController @Autowired constructor(val userService: UserService) {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No user with given username")
    class UserNotFoundException : RuntimeException()

    @RequestMapping("/api/users/{username}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Returns user data",
                  notes = "Returns basic information about target user.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "No user with given username")
    )
    open fun getUser(
            @ApiParam("Username of target user") @PathVariable username: String
    ): UserDto {
        val user = userService.findByUsername(username)

        if (user != null) {
            return UserDto.from(user)
        } else {
            throw UserNotFoundException()
        }
    }

    @RequestMapping("/api/users/me", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Returns logged user data",
                  notes = "Returns basic information about authenticated user. Same as /users/{myUsername}/.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 401, message = "Not authenticated")
    )
    open fun getMe(
            @ApiIgnore @ModelAttribute("myUsername") myUsername: String?
    ): UserDto {
        val me = if (myUsername != null) userService.findByUsername(myUsername) else null

        if (me != null) {
            return UserDto.from(me)
        } else {
            throw NotAuthenticatedException()
        }
    }

    @RequestMapping("/api/users/{username}/instructedCourses", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Returns courses instructed by target user",
                  notes = "Returns an array of courses instructed by target user.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "No user with given username")
    )
    @Transactional
    open fun getInstructedCourses(
            @ApiParam("Username of target user") @PathVariable username: String
    ): List<CourseDto> {
        val user = userService.findByUsername(username)

        if (user != null) {
            Hibernate.initialize(user.coursesInstructed)
            return user.coursesInstructed.map { CourseDto.from(it) }
        } else {
            throw UserNotFoundException()
        }
    }

    @RequestMapping("/api/users/me/instructedCourses", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Returns courses instructed by logged user",
            notes = "Returns an array of courses instructed by authenticated user.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 401, message = "Not authenticated")
    )
    @Transactional
    open fun getMyInstructedCourses(
            @ApiIgnore @ModelAttribute("myUsername") myUsername: String?
    ): List<CourseDto> {
        val me = if (myUsername != null) userService.findByUsername(myUsername) else null

        if (me != null) {
            Hibernate.initialize(me.coursesInstructed)
            return me.coursesInstructed.map { CourseDto.from(it) }
        } else {
            throw NotAuthenticatedException()
        }
    }

    @RequestMapping("/api/users/{username}/listenedCourses", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Returns courses that target user listens to",
            notes = "Returns an array of courses that target user listens to.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "No user with given username")
    )
    @Transactional
    open fun getListenedCourses(
            @ApiParam("Username of target user") @PathVariable username: String
    ): List<CourseDto> {
        val user = userService.findByUsername(username)

        if (user != null) {
            Hibernate.initialize(user.coursesListened)
            return user.coursesListened.map { CourseDto.from(it) }
        } else {
            throw UserNotFoundException()
        }
    }

    @RequestMapping("/api/users/me/listenedCourses", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Returns courses that logged user listens to",
            notes = "Returns an array of courses that authenticated user listens to.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 401, message = "Not authenticated")
    )
    @Transactional
    open fun getMyListenedCourses(
            @ApiIgnore @ModelAttribute("myUsername") myUsername: String?
    ): List<CourseDto> {
        val me = if (myUsername != null) userService.findByUsername(myUsername) else null

        if (me != null) {
            Hibernate.initialize(me.coursesListened)
            return me.coursesListened.map { CourseDto.from(it) }
        } else {
            throw NotAuthenticatedException()
        }
    }
}