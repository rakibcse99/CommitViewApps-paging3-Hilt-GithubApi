package com.rakib.commitviewapp.data.source

import com.rakib.commitviewapp.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    @Inject
    lateinit var tokenManager: TokenManager

    val token = "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"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        //   val token = tokenManager.getToken()
        request.addHeader("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }
}