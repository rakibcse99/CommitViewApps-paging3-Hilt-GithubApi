package com.rakib.commitviewapp.repository.model

data class Verification(
    val payload: String,
    val reason: String,
    val signature: String,
    val verified: Boolean
)