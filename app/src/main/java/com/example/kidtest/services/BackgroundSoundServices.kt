package com.example.kidtest.services

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import com.example.kidtest.R


class BackgroundSoundServices : Service() {

    var x = 100f
    var y = 100f
    private val binder: IBinder = LocalBinder()
    var player: MediaPlayer? = null
    private var  uri: Uri?=null
    override fun onBind(arg0: Intent?): IBinder? {
        return binder
    }

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): BackgroundSoundServices = this@BackgroundSoundServices
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, R.raw.bg_music)
        player!!.isLooping = true // Set looping
        player!!.setVolume(100f, 100f)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player!!.start()
        //  posi=intent?.extras?.getString("posi").toString()
        return START_NOT_STICKY
    }

    override fun onStart(intent: Intent?, startId: Int) {
        // TO DO
    }

    fun onUnBind(arg0: Intent?): IBinder? {
        // TO DO Auto-generated method
        return null
    }

    fun onStop() {}
    fun onPause() {}
    override fun onDestroy() {
        player!!.stop()
        player!!.release()
    }

    override fun onLowMemory() {}

    fun volumeSetter() {
        player?.setVolume(50f, 50f)
    }

    override fun updateServiceGroup(conn: ServiceConnection, group: Int, importance: Int) {
        super.updateServiceGroup(conn, group, importance)
        x = 50f
        y = 50f
    }
}