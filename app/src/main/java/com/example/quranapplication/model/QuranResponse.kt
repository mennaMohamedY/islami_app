package com.example.quranapplication.model

import com.google.gson.annotations.SerializedName

data class QuranResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: String? = null
)

