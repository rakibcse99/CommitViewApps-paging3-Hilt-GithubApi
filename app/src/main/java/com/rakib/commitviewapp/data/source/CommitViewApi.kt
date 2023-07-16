package com.rakib.commitviewapp.data.source

import com.rakib.commitviewapp.repository.model.CommitModel
import com.rakib.commitviewapp.repository.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommitViewApi {
    @GET("/repos/cocogitto/cocogitto/commits?per_page=20&sort=author-date")
    suspend fun getCommitList(@Query("page") pageNumber: Int): Response<CommitModel>

    @GET("/users/{user}")
    suspend fun getUserProfile(@Path("user") userName:String): Response<UserModel>


}