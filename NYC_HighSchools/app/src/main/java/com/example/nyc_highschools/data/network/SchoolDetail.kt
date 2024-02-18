package com.example.nyc_highschools.data.network

import com.google.gson.annotations.SerializedName

data class SchoolDetail(
    @field:SerializedName("dbn") val dbn : String,
    @field:SerializedName("school_name") val schoolName : String,
    @field:SerializedName("overview_paragraph") val overviewParagraph : String,
    @field:SerializedName("phone_number") val phoneNumber: String,
    @field:SerializedName("school_email") val schoolEmail: String,
    @field:SerializedName("website") val website: String,
    @field:SerializedName("location") val location: String,
    @field:SerializedName("fax_number") val faxNumber: String,
    @field:SerializedName("primary_address_line_1") val primaryAddress: String,
    @field:SerializedName("city") val city: String,
    @field:SerializedName("state_code") val stateCode: String,
    @field:SerializedName("zip") val zip: String,
    @field:SerializedName("lat") val latitude: String,
    @field:SerializedName("lon") val longitude: String,
    @field:SerializedName("total_students") val totalStudents: Int,
    @field:SerializedName("start_time") val startTime: String,
    @field:SerializedName("end_time") val endTime: String
) {
    fun getAddress(): String {
        return "$primaryAddress\n$city, $stateCode, $zip"
    }
}
