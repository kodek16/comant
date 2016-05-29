package com.psenchanka.comant.controller

import com.psenchanka.comant.dto.BasicClassDto
import com.psenchanka.comant.dto.BasicCourseDto
import com.psenchanka.comant.dto.DetailedCourseDto
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
@RequestMapping("/api/courses")
open class CourseController @Autowired constructor(val courseService: CourseService, val userService: UserService) {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No course with given id")
    class CourseNotFoundException : RuntimeException()


    @RequestMapping("", method = arrayOf(RequestMethod.GET))
    @ApiOperation("Search courses",
                  notes = "Returns all courses satisfying given criteria. If detailed is 'true', detailed data is returned")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK", response = Array<BasicCourseDto>::class)
    )
    @Transactional
    open fun getAllCourses(
            @ApiParam("Search by state (ongoing/past/new)") @RequestParam(required = false) state: String?,
            @ApiParam("Search by related user (listener or instructor)") @RequestParam(required = false) ofUser: String?,
            @ApiParam("Return detailed information?") @RequestParam(required = false) detailed: String?
    ): List<Any> {
        //Returns either List<BasicCourseDto> or List<DetailedCourseDto>
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
                        it.instructors.contains(userService.findById(ofUser))
                                || it.listeners.contains(userService.findById(ofUser))
                    } else {
                        true
                    }
                }
                .map {
                    if (detailed == "true") {
                        DetailedCourseDto.from(it) as Any
                    } else {
                        BasicCourseDto.from(it) as Any
                    }
                }
    }

    @RequestMapping("/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation("Get course data",
                  notes = "Returns detailed information about target course.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "No course with given id")
    )
    @Transactional
    open fun getCourse(
            @ApiParam("Id of target course") @PathVariable id: String
    ): DetailedCourseDto {
        val course = try {
            courseService.findById(id.toInt())
        } catch(e: NumberFormatException) {
            throw CourseNotFoundException()
        }

        if (course != null) {
            return DetailedCourseDto.from(course)
        } else {
            throw CourseNotFoundException()
        }
    }

    @RequestMapping("/{id}/classes", method = arrayOf(RequestMethod.GET))
    @ApiOperation("Get course classes",
                  notes = "Returns an array of classes of given course.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "No course with given id")
    )
    @Transactional
    open fun getClasses(
            @ApiParam("Id of target course") @PathVariable id: String
    ): List<BasicClassDto> {
        val course = try {
            courseService.findById(id.toInt())
        } catch(e: NumberFormatException) {
            throw CourseNotFoundException()
        }

        if (course != null) {
            return course.classes.map { BasicClassDto.from(it) }
        } else {
            throw CourseNotFoundException()
        }
    }
}