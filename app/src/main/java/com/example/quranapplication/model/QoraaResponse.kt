package com.example.quranapplication.model

import com.google.gson.annotations.SerializedName

data class QoraaResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItem(

	@field:SerializedName("identifier")
	val identifier: String? = null,

	@field:SerializedName("englishName")
	val englishName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("format")
	val format: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("direction")
	val direction: Any? = null
)