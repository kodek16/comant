package com.psenchanka.comant.auth

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Bad credentials")
class BadCredentialsException : RuntimeException()