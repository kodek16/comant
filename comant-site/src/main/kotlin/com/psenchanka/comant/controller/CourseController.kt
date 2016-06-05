package com.psenchanka.comant.controller

import com.psenchanka.comant.dto.*
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
import springfox.documentation.annotations.ApiIgnore

@RestController
@RequestMapping("/api/courses")
open class CourseController @Autowired constructor(val courseService: CourseService, val userService: UserService) {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No course with given id")
    class CourseNotFoundException : RuntimeException()


    @RequestMapping("", method = arrayOf(RequestMethod.GET))
    @ApiOperation("Search courses",
                  notes = "Returns all courses satisfying given criteria. If detailed is 'true', detailed data is returned")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK")
    )
    @Transactional
    open fun getAllCourses(
            @ApiParam("Search by state (ongoing/past/new)") @RequestParam(required = false) state: String?,
            @ApiParam("Search by related user (listener or instructor)") @RequestParam(required = false) user: String?,
            @ApiParam("Return detailed information?") @RequestParam(required = false) detailed: String?
    ): List<BasicCourseDto> {
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
                    if (user != null) {
                        it.instructors.contains(userService.findById(user))
                                || it.listeners.contains(userService.findById(user))
                    } else {
                        true
                    }
                }
                .map {
                    if (detailed == "true") {
                        DetailedCourseDto.from(it)
                    } else {
                        BasicCourseDto.from(it)
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
                  notes = "Returns an array of classes of given course." +
                          " If detailed is 'true', detailed data is returned." +
                          " For course instructors also includes additional protected data.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "No course with given id")
    )
    @Transactional
    open fun getClasses(
            @ApiIgnore @ModelAttribute("myUsername") myUsername: String?,
            @ApiParam("Id of target course") @PathVariable id: String,
            @ApiParam("Return detailed information?") @RequestParam(required = false) detailed: String?
    ): List<BasicClassDto> {
        //Returns either List<BasicClassDto>, List<DetailedClassDto> or List<InstructorClassDto>
        val course = try {
            courseService.findById(id.toInt())
        } catch(e: NumberFormatException) {
            throw CourseNotFoundException()
        }

        if (course != null) {
            return if (detailed == "true" && course.instructors.any { it.username == myUsername }) {
                course.classes.map { InstructorClassDto.from(it) }
            } else if (detailed == "true") {
                course.classes.map { DetailedClassDto.from(it) }
            } else {
                course.classes.map { BasicClassDto.from(it) }
            }
        } else {
            throw CourseNotFoundException()
        }
    }
}