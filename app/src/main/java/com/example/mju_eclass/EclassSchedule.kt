package com.example.mju_eclass

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

data class ClassData(
    val letureName : String,
    val lectureSchedule: ArrayList<String>,
    val noticeCount: String,
    val documentCount: String
)

interface EclassSchedule {
    @Headers("content-type: application/x-www-form-urlencoded")
    @POST("user/classInfo")
    @FormUrlEncoded
    fun addFriend(@Field("id") id: String,
                  @Field("pwd") pwd: String)
            : Call<List<ClassData>>
}

