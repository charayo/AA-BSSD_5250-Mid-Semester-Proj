package com.example.myapplication.restaurantreponse

data class RestaurantResponse(
    val `data`: List<Data>,
    val open_hours_options: OpenHoursOptions,
    val paging: Paging,
    val restaurant_availability_options: RestaurantAvailabilityOptions
)