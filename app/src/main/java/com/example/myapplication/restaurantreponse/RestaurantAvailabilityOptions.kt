package com.example.myapplication.restaurantreponse

data class RestaurantAvailabilityOptions(
    val datestring: String,
    val day: String,
    val hour: String,
    val is_default: Boolean,
    val is_set: Boolean,
    val minute: String,
    val month: String,
    val people: String,
    val people_options: PeopleOptions,
    val racable: Boolean,
    val time_options: TimeOptions,
    val year: String
)