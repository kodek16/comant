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


    @RequestMapping("/api/courses", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Returns courses list",
                  notes = "Returns all courses satisfying given criteria.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK")
    )
    @Transactional
    open fun getAllCourses(
            @ApiParam("Include instructors?") @RequestParam(required = false) withInstructors: String?,
            @ApiParam("Include listeners?") @RequestParam(required = false) withListeners: String?
    ): List<CourseDto> {
        return courseService.findAll().map {
            val course = CourseDto.from(it);
            if (withInstructors == "true") {
                Hibernate.initialize(it.instructors)
                course.instructors = it.instructors.map { user -> UserDto.from(user) }
            }
            if (withListeners == "true") {
                Hibernate.initialize(it.listeners)
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
                Hibernate.initialize(course.instructors)
                result.instructors = course.instructors.map { UserDto.from(it) }
            }
            if (withListeners == "true") {
                Hibernate.initialize(course.listeners)
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