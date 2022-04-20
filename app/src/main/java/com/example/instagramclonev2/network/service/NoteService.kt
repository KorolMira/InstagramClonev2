package com.example.instagramclonev2.network.service

import com.example.instagramclonev2.model.FCMNote
import com.example.instagramclonev2.model.FCMResp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NoteService {

    companion object {
        private const val SERVER_KEY =
            "AAAAp_vnjhw:APA91bF3ytFP57NS7OnQpMrTS8X0ZI9s0AwxoiyfUr08qb-RILRRVnaa8R6KtwGwnqsfQDXP469gLd4V98Y95DFkVQ22b-eLWjLcJQBMBS8KswWJr6ipeKl1YV0ZgRMZ7cAud8WYZUXV"
    }

    @Headers("Authorization:key=$SERVER_KEY")
    @POST("/fcm/send")
    fun sendNote(@Body fcmNote: FCMNote): Call<FCMResp>
}