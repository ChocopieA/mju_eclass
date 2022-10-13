package com.example.mju_eclass


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

data class Data(val error: String, val error_message: String)


interface EclassLogin {
    @Headers("content-type: application/x-www-form-urlencoded")
    @POST("user/checkLogin")
    @FormUrlEncoded
    fun addFriend(@Field("id") id: String,
                @Field("pwd") pwd: String)
        : Call<String>
}