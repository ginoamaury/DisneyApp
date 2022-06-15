package com.co.gino.domain.exceptions

import java.lang.Exception

private const val TECHNICAL_EXCEPTION_CODE = 500
class TechnicalException (codeMessage: Int = TECHNICAL_EXCEPTION_CODE) : Exception(codeMessage.toString())