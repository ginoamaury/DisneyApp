package com.co.gino.domain.builder

import com.co.gino.domain.models.Guest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GuestBuilder {
    companion object{

        fun getFlowListGuest (): Flow<List<Guest>> {
            val guests = listOf(BuilderGuestSpecific.getGuest(),BuilderGuestSpecific.getGuest("Jhean"))
            return flow { emit(guests) }
        }

        fun getGuest (): Guest = BuilderGuestSpecific.getGuest()

        fun getListGuest (): List<Guest> = listOf(BuilderGuestSpecific.getGuest(),BuilderGuestSpecific.getGuest("Jhean"))

    }

    private class BuilderGuestSpecific {
        companion object {
            fun getGuest (name:String = "Gino Amaury"): Guest {
                return if(name == "Gino Amaury"){
                    Guest (
                        name,
                        true
                    )
                }else{
                    Guest(
                        name,
                        false
                    )
                }
            }
        }
    }
}