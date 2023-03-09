package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.button.MaterialButton
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel

class MainActivity : AppCompatActivity() {
    private lateinit var homeContainer: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val startButton = MaterialButton(this).apply {
            text = "Start"
            setBackgroundColor(Color.TRANSPARENT)
            setStrokeColorResource(R.color.white)
            strokeWidth = 2
            setTextColor(Color.WHITE)

            id = View.generateViewId()

            // Set the button's layout parameters
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
                topToTop = ConstraintSet.PARENT_ID
                bottomToBottom = ConstraintSet.PARENT_ID
                horizontalBias = 0.5f
                verticalBias = 0.5f
            }

            (layoutParams as ConstraintLayout.LayoutParams).apply {
                setPadding(0,50,0,50)
                setMargins(10,0,10,0)
                gravity
            }
            setOnClickListener {
                val passableData = Intent(applicationContext, SearchActivity::class.java).apply {
                    putExtra("message", "page loaded")
                }
                startActivity(passableData)
            }

        }

        val overlay = View(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.parseColor("#80000000")) // black color with 50% opacity
        }
        homeContainer = ConstraintLayout(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )

            // Set the background image resource
            setBackgroundResource(R.drawable.food1)
            addView(overlay)
            addView(startButton)

        }
        setContentView(homeContainer)
    }
}