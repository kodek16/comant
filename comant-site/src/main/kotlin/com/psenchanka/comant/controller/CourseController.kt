package com.psenchanka.comant.controller

import com.psenchanka.comant.dto.CourseDto
import com.psenchanka.comant.dto.UserDto
import com.psenchanka.comant.service.CourseService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.hibernate.Hibernate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
open class CourseController @Autowired constructor(val courseService: CourseService) {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No course with given id")
    class CourseNotFoundException : RuntimeException()

    @RequestMapping("/api/courses/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Returns course data",
                  notes = "Returns basic information about target course.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "No course with given id")
    )
    open fun getCourse(
            @ApiParam("Id of target course") @PathVariable id: String
    ): CourseDto {
        val course = try {
            courseService.findById(id.toInt())
        } catch(e: NumberFormatException) {
            throw CourseNotFoundException()
        }

        if (course != null) {
            return CourseDto.from(course)
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
            Hibernate.initialize(course.instructors)
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
            Hibernate.initialize(course.listeners)
            return course.listeners.map { UserDto.from(it) }
        } else {
            throw CourseNotFoundException()
        }
    }
}