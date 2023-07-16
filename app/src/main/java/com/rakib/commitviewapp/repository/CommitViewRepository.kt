package com.rakib.commitviewapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rakib.commitviewapp.base.ErrorResponse
import com.rakib.commitviewapp.base.Resource
import com.rakib.commitviewapp.data.source.CommitViewApi
import com.rakib.commitviewapp.paging.CommitViewPagingSource
import com.rakib.commitviewapp.repository.model.UserModel
import javax.inject.Inject

class CommitViewRepository @Inject constructor(private val apiService: CommitViewApi) {
    val data = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
    ) {
        CommitViewPagingSource(apiService)
    }.flow


    suspend fun getUserProfile(userName: String): Resource<UserModel, ErrorResponse> {
        val response = apiService.getUserProfile(userName)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(
                null,
                error = ErrorResponse(
                    success = false,
                    message = response.message(),
                    code = response.code()
                )
            )
        }
    }
}