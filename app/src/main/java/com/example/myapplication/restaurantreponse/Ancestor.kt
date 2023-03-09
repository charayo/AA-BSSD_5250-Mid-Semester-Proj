package com.example.myapplication.restaurantreponse

data class Ancestor(
    val abbrv: Any,
    val location_id: String,
    val name: String,
    val subcategory: List<SubcategoryX>
)