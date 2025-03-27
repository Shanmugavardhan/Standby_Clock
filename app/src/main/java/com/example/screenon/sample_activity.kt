package com.example.screenon

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class sample_activity : AppCompatActivity() {
    private var x1 = 0f
    private var x2 = 0f
    private var seekBar: SeekBar? = null
    private var textView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_sample)


        //Below code is for seekbar
        seekBar = findViewById<SeekBar>(R.id.seekBar)
        textView = findViewById<TextView>(R.id.textView)


        //set initial value
        // Create stroke effect using SpannableString
        textView!!.text = "HelloWorld!"


        //Listen for seekBar changes
        seekBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, FromUser: Boolean) {
                textView!!.setTextColor(interpolateColor(progress))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //calling when user starts interacting with seekbar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //calling when user stops interacting with seekbar
            }
        })
    }

    fun interpolateColor(a: Int): Int {
        // Calculate the grayscale intensity (inverted from white to black)

        val intensity = 255 - ((a * 255) / 100)

        // Format the color as a hex string with alpha = FF
        return (0xFF shl 24) or (intensity shl 16) or (intensity shl 8) or 225
    }


    // Below code is for Swipe
    //    float deltaX = x2 - x1;
    //    float deltaY = y2 - y1;
    //    deltaX > MIN_DISTANCE         //Swipe Right
    //    deltaX < -MIN_DISTANCE        // Swipe Left
    //    deltaY > MIN_DISTANCE         // Swipe Down
    //    deltaY < -MIN_DISTANCE        // Swipe Up
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.getAction()) {
            MotionEvent.ACTION_DOWN -> x1 = event.getX()
            MotionEvent.ACTION_UP -> {
                x2 = event.getX()
                val deltaX = x2 - x1
                if (deltaX < -MIN_DISTANCE) {  // Swipe Right detected
                    val i = Intent(this@sample_activity, MainActivity::class.java)
                    startActivity(i)
                }
            }
        }
        return super.onTouchEvent(event)
    }

    companion object {
        private const val MIN_DISTANCE = 150
    }
}
