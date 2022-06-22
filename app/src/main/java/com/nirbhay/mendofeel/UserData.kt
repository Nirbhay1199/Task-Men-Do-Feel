package com.nirbhay.mendofeel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/"
const val API_URL = "e8583282-c7a5-4de2-9b7a-269d705b015a/posts.json?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220621%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220621T203528Z&X-Amz-Expires=86400&X-Amz-Signature=6907121776bdaa62b02280fea66f16d2cea13e4771c6ec13843d9dd7811f844c&X-Amz-SignedHeaders=host&x-id=GetObject"

interface UserData {

    @GET(API_URL)
    fun getUserData() :retrofit2.Call<Response>

}

object Service{
    val serviceInstance: UserData
    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        serviceInstance = retrofit.create(UserData::class.java)
    }
}