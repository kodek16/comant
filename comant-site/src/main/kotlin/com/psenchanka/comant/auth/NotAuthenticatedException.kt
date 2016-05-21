package com.psenchanka.comant.auth

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "This request cannot be performed without authentication")
class NotAuthenticatedException : RuntimeException()