package com.psenchanka.comant.controller

import com.psenchanka.comant.dto.CourseDto
import com.psenchanka.comant.dto.UserDto
import com.psenchanka.comant.service.CourseService
import com.psenchanka.comant.service.UserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
open class CourseController @Autowired constructor(val courseService: CourseService, val userService: UserService) {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No course with given id")
    class CourseNotFoundException : RuntimeException()


    @RequestMapping("/api/courses", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Returns courses list",
                  notes = "Returns all courses satisfying given criteria.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK")
    )
    @Transactional
    open fun getAllCourses(
            @ApiParam("Include instructors?") @RequestParam(required = false) withInstructors: String?,
            @ApiParam("Include listeners?") @RequestParam(required = false) withListeners: String?,
            @ApiParam("Search by state (ongoing/past/new)") @RequestParam(required = false) state: String?,
            @ApiParam("Search by related user (listener or instructor)") @RequestParam(required = false) ofUser: String?
    ): List<CourseDto> {
        return courseService.findAll()
                .filter {
                    when (state) {
                        "ongoing" -> it.isOngoing()
                        "past" -> it.isPast()
                        "new" -> it.isNew()
                        else -> true
                    }
                }
                .filter {
                    if (ofUser != null) {
                        it.instructors.contains(userService.findByUsername(ofUser))
                                || it.listeners.contains(userService.findByUsername(ofUser))
                    } else {
                        true
                    }
                }
                .map {
                    val course = CourseDto.from(it);
                    if (withInstructors == "true") {
                        course.instructors = it.instructors.map { user -> UserDto.from(user) }
                    }
                    if (withListeners == "true") {
                        course.listeners = it.listeners.map { user -> UserDto.from(user) }
                    }

                    course
                }
    }

    @RequestMapping("/api/courses/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Returns course data",
                  notes = "Returns basic information about target course.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "No course with given id")
    )
    @Transactional
    open fun getCourse(
            @ApiParam("Id of target course") @PathVariable id: String,
            @ApiParam("Include instructors?") @RequestParam(required = false) withInstructors: String?,
            @ApiParam("Include listeners?") @RequestParam(required = false) withListeners: String?
    ): CourseDto {
        val course = try {
            courseService.findById(id.toInt())
        } catch(e: NumberFormatException) {
            throw CourseNotFoundException()
        }

        if (course != null) {
            val result = CourseDto.from(course)
            if (withInstructors == "true") {
                result.instructors = course.instructors.map { UserDto.from(it) }
            }
            if (withListeners == "true") {
                result.listeners = course.listeners.map { UserDto.from(it) }
            }
            return result
        } else {
            throw CourseNotFoundException()
        }
    }

    @RequestMapping("/api/courses/{id}/instructors", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Returns course instructors",
                  notes = "Return an array of users instructing target course.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "No course with given id")
    )
    @Transactional
    open fun getCourseInstructors(
            @ApiParam("Id of target course") @PathVariable id: String
    ): List<UserDto> {
        val course = try {
            courseService.findById(id.toInt())
        } catch(e: NumberFormatException) {
            throw CourseNotFoundException()
        }

        if (course != null) {
            return course.instructors.map { UserDto.from(it) }
        } else {
            throw CourseNotFoundException()
        }
    }

    @RequestMapping("/api/courses/{id}/listeners", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Returns course listeners",
            notes = "Return an array of users listening to target course.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "No course with given id")
    )
    @Transactional
    open fun getCourseListeners(
            @ApiParam("Id of target course") @PathVariable id: String
    ): List<UserDto> {
        val course = try {
            courseService.findById(id.toInt())
        } catch(e: NumberFormatException) {
            throw CourseNotFoundException()
        }

        if (course != null) {
            return course.listeners.map { UserDto.from(it) }
        } else {
            throw CourseNotFoundException()
        }
    }
}