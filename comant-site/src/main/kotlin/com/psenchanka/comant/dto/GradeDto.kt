package com.psenchanka.comant.dto

import com.psenchanka.comant.model.ClassGrade

data class GradeDto(
        var listener: BasicUserDto,
        var grade: String) {

    companion object {
        fun from(grade: ClassGrade) = GradeDto(
                BasicUserDto.from(grade.listener),
                grade.grade)
    }
}