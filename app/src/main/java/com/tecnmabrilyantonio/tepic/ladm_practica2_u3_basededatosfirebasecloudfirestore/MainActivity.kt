package com.tecnmabrilyantonio.tepic.ladm_practica2_u3_basededatosfirebasecloudfirestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startTimer()
    }

    fun startTimer(){
        object : CountDownTimer(3000,1000){
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                val intent1 = Intent(applicationContext, MainActivity2::class.java).apply {}
                startActivity(intent1)
            }
        }.start()
    }
}