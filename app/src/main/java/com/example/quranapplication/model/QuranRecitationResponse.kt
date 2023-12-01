package com.example.quranapplication.model

import com.google.gson.annotations.SerializedName

data class QuranRecitationResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Dataa? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Sajda(

	@field:SerializedName("obligatory")
	val obligatory: Boolean? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("recommended")
	val recommended: Boolean? = null
)

data class Dataa(

	@field:SerializedName("edition")
	val edition: Editionn? = null,

	@field:SerializedName("surahs")
	val surahs: List<SurahsItemm?>? = null
)

data class Editionn(

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

data class AyahsItemm(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("audioSecondary")
	val audioSecondary: List<String?>? = null,

	@field:SerializedName("hizbQuarter")
	val hizbQuarter: Int? = null,

	@field:SerializedName("ruku")
	val ruku: Int? = null,

	@field:SerializedName("manzil")
	val manzil: Int? = null,

	@field:SerializedName("audio")
	val audio: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("sajda")
	val sajda: Boolean? = null,

	@field:SerializedName("numberInSurah")
	val numberInSurah: Int? = null,

	@field:SerializedName("juz")
	val juz: Int? = null
)

data class SurahsItemm(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("englishName")
	val englishName: String? = null,

	@field:SerializedName("revelationType")
	val revelationType: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("ayahs")
	val ayahs: List<AyahsItemm?>? = null,

	@field:SerializedName("englishNameTranslation")
	val englishNameTranslation: String? = null
)
