package com.example.quranapplication.model

import com.google.gson.annotations.SerializedName

data class QurannResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class SurahsItem(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("englishName")
	val englishName: String? = null,

	@field:SerializedName("revelationType")
	val revelationType: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("ayahs")
	val ayahs: List<AyahsItem?>? = null,

	@field:SerializedName("englishNameTranslation")
	val englishNameTranslation: String? = null
)

data class Data(

	@field:SerializedName("edition")
	val edition: Edition? = null,

	@field:SerializedName("surahs")
	val surahs: List<SurahsItem?>? = null
)

data class Edition(

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
	val type: String? = null
)

data class AyahsItem(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("hizbQuarter")
	val hizbQuarter: Int? = null,

	@field:SerializedName("ruku")
	val ruku: Int? = null,

	@field:SerializedName("manzil")
	val manzil: Int? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("numberInSurah")
	val numberInSurah: Int? = null,

	@field:SerializedName("juz")
	val juz: Int? = null
)

