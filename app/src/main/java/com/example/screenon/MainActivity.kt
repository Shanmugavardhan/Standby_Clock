package com.example.screenon

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var x1: Float = 0f
    var x2: Float = 0f
    var MIN_DISTANCE: Int = 150
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.getAction()) {
            MotionEvent.ACTION_DOWN -> x1 = event.getX() // get the x coordinate of the touch
            MotionEvent.ACTION_UP -> {
                x2 = event.getX() // get the y coordinate of the touch
                val deltaX = x2 - x1
                if (deltaX < -MIN_DISTANCE) {  // Swipe left detected
                    val i = Intent(this@MainActivity, sample_activity::class.java)
                    startActivity(i)
                }
            }
        }
        return super.onTouchEvent(event)
    }
}