package com.co.gino.infrastructure.httpclient

import com.co.gino.infrastructure.BuildConfig
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull


class MockInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            val responseString = when {
                uri.endsWith("guest/all_guests") -> getListGuestsJson
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    ResponseBody.create(
                        "application/json".toMediaTypeOrNull(),
                        responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
        } else {
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode")
        }
    }
}

const val getListGuestsJson = """
[
{"name":"Gino Amaury","hasReservation":true},
{"name":"Isabel Capacho","hasReservation":true},
{"name":"Jhean Acevedo","hasReservation":true},
{"name":"Kelly Capacho","hasReservation":true},
{"name":"Danna Fiorella","hasReservation":true},
{"name":"Gina Ruiz","hasReservation":true},
{"name":"Hernando Castrillon","hasReservation":true},
{"name":"Diego Acevedo","hasReservation":false},
{"name":"Yari Rodriguez","hasReservation":false}
]
"""