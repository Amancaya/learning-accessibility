package com.example.accesibility

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.graphics.PixelFormat
import android.media.AudioManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintSet

class MyAccessibilityService : AccessibilityService() {

    private lateinit var layout: FrameLayout

    override fun onInterrupt() { }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) { }

    override fun onServiceConnected() {

        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val layoutParams = WindowManager.LayoutParams().apply {
            type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
            format = PixelFormat.TRANSLUCENT
            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            width = WindowManager.LayoutParams.WRAP_CONTENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
            gravity = Gravity.START
        }

        layout = FrameLayout(applicationContext)
        val menu = LayoutInflater.from(applicationContext).inflate(R.layout.view_lateral_menu, layout)
        windowManager.addView(menu, layoutParams)

        setUpPowerButton()
        setUpScreenShot()
        setUpVolumeDown()
        setUpVolumeUp()
    }

    private fun setUpPowerButton() {
        layout.findViewById<Button>(R.id.btn_power).setOnClickListener {
            performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
        }
    }

    private fun setUpVolumeUp() {
        layout.findViewById<Button>(R.id.btn_volume_up).setOnClickListener {
            controlVolume(AudioManager.ADJUST_RAISE)
        }
    }

    private fun setUpVolumeDown() {
        layout.findViewById<Button>(R.id.btn_volume_down).setOnClickListener {
            controlVolume(AudioManager.ADJUST_LOWER)
        }
    }

    private fun setUpScreenShot() {
        layout.findViewById<Button>(R.id.btn_screen_shot).setOnClickListener {
            performGlobalAction(GLOBAL_ACTION_TAKE_SCREENSHOT)
        }
    }

    private fun controlVolume(suggestedTypeStream: Int) {
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
            suggestedTypeStream,
            AudioManager.FLAG_SHOW_UI)
    }
}