package com.ucal.marketing.expo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visitor_table")
data class Visitor(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "full_name") val fullName: String?,
    @ColumnInfo(name = "designation") val designation: String?,
    @ColumnInfo(name = "company_name") val companyName: String?,
    @ColumnInfo(name = "visitor_category") val visitorCategory: String?,
    @ColumnInfo(name = "phone_no") val phoneNumber: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "product_interest") val productInterest: String?,
    @ColumnInfo(name = "rating") val rating: Int?,
    @ColumnInfo(name = "timestamp") val timeStamp: String?,
)
