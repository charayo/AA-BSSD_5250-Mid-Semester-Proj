package com.example.myapplication.restaurantreponse

data class OpenHoursOptions(
    val closed_count: String,
    val current_value: String,
    val is_set: Boolean,
    val low_coverage_primary_message: String,
    val low_coverage_secondary_message: String,
    val open_count: String,
    val timezone: String,
    val unsure_count: String
)