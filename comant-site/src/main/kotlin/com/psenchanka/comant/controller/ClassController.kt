package com.psenchanka.comant.controller

import com.psenchanka.comant.dto.DetailedClassDto
import com.psenchanka.comant.dto.InstructorClassDto
import com.psenchanka.comant.service.ClassService
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
@RequestMapping("/api/classes")
open class ClassController @Autowired constructor(val classService: ClassService) {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No class with given id")
    class ClassNotFoundException : RuntimeException()


    @RequestMapping("/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation("Get class data",
                  notes = "Returns detailed data for for target class." +
                          " For course instructors also includes additional protected data")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "No class with given id")
    )
    @Transactional
    open fun getClass(
            @ApiIgnore @ModelAttribute("myUsername") myUsername: String?,
            @ApiParam("Id of target class") @PathVariable id: String
    ): DetailedClassDto {
        val class_ = try {
            classService.findById(id.toInt())
        } catch (e: NumberFormatException) {
            throw ClassNotFoundException()
        }

        if (class_ != null) {
            return if (class_.course.instructors.any { it.username == myUsername }) {
                InstructorClassDto.from(class_)
            } else {
                DetailedClassDto.from(class_)
            }
        } else {
            throw ClassNotFoundException()
        }
    }
}