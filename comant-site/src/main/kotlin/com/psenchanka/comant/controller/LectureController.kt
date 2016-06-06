package com.psenchanka.comant.controller

import com.psenchanka.comant.dto.BasicLectureDto
import com.psenchanka.comant.dto.DetailedLectureDto
import com.psenchanka.comant.service.LectureService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/lectures")
open class LectureController @Autowired constructor(val lectureService: LectureService) {

    @ResponseStatus(HttpStatus.NOT_FOUND, reason = "No lecture with given id")
    class LectureNotFoundException : RuntimeException()

    @RequestMapping("", method = arrayOf(RequestMethod.GET))
    @ApiOperation("Search lectures",
                  notes = "Returns all lectures satisfying given criteria.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK")
    )
    @Transactional
    open fun search(
            @ApiParam("Search by author") @RequestParam(required = false) author: String?
    ): List<BasicLectureDto> {
        return lectureService.findAll()
            .filter { author == null || it.author.username == author }
            .map { BasicLectureDto.from(it) }
    }

    @RequestMapping("/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation("Get lecture data",
                  notes = "Returns detailed information about target lecture, including its text.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "No lecture with given id")
    )
    @Transactional
    open fun getById(
            @ApiParam("Lecture id") @PathVariable id: Int
    ): DetailedLectureDto {
        val lecture = lectureService.findById(id) ?: throw LectureNotFoundException()
        return DetailedLectureDto.from(lecture)
    }
}