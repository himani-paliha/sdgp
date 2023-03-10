package com.example.sdgp_welcomescreen
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ThankYouScreen : AppCompatActivity() {
    var HomeButton: Button? = null
    var ExitButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thankyou_screen)
        HomeButton = findViewById<View>(R.id.HomeButton) as Button //get id of button 1
        ExitButton = findViewById<View>(R.id.ExitButton) as Button //get id of button 2
        HomeButton!!.setOnClickListener {
            Toast.makeText(applicationContext, "Home", Toast.LENGTH_LONG)
                .show() //display the text of button1
        }
        ExitButton!!.setOnClickListener {
            Toast.makeText(applicationContext, "Exit", Toast.LENGTH_LONG)
                .show() //display the text of button2
        }
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 4500) // 3000 is the delayed time in milliseconds.
    }
}