package com.psenchanka.comant.controller

import com.psenchanka.comant.dto.SiteSettingsDto
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/site-settings")
class LocalSettingsController {

    @Value("\${comant.sitename}")
    private lateinit var siteName: String;

    @RequestMapping("", method = arrayOf(RequestMethod.GET))
    @ApiOperation("Get site local settings",
                  notes = "Returns preferences that differ between comant installations like site name or icon.")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK")
    )
    fun getSiteSettings(): SiteSettingsDto = SiteSettingsDto(siteName)
}