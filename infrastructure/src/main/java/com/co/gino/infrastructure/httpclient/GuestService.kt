package com.co.gino.infrastructure.httpclient

import com.co.gino.infrastructure.guests.httpclient.dto.GuestDto
import retrofit2.http.GET

private const val QUERY_GUESTS = "guest/all_guests"
interface GuestService {
    @GET(QUERY_GUESTS)
    suspend fun getGuests ():List<GuestDto>
}