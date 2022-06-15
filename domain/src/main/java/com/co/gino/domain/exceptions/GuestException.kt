package com.co.gino.domain.exceptions

import java.lang.Exception

private const val NO_DATA_GUESTS_EXCEPTION_CODE = 404
abstract class GuestException (message:String): Exception(message)
class NoDataGuestException (codeMessage: Int = NO_DATA_GUESTS_EXCEPTION_CODE): GuestException(codeMessage.toString())