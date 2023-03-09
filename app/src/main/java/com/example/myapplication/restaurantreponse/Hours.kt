package com.example.myapplication.restaurantreponse

data class Hours(
    val timezone: String,
    val week_ranges: List<List<WeekRange>>
)