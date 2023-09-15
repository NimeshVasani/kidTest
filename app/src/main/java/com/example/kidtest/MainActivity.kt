package com.example.kidtest

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.kidtest.services.BackgroundSoundServices
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private var mInterstitialAd: InterstitialAd? = null
    private var TAG = "MainActivity"

    var name: Intent? = null
//    lateinit var interstitialAd: InterstitialAd

    companion object {
        @JvmField
        var t1: TextToSpeech? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppCompat_NoActionBar)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        name = Intent(this, BackgroundSoundServices::class.java)
        runBlocking {
            launch {
                //@JvmField
                t1 = TextToSpeech(applicationContext) { status: Int ->
                    if (status != TextToSpeech.ERROR) {
                        t1!!.setSpeechRate(0.5f)
                        t1!!.setPitch(0.4f)
                    }
                }
                t1!!.speak("", TextToSpeech.QUEUE_FLUSH, null, null)

            }
            delay(2000)

        }
//        interstitialAd = InterstitialAd(this)
//        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712")
//        interstitialAd.loadAd(AdRequest.Builder().build())
//        MobileAds.initialize(this) {
//
//        }
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError?.message)
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })


        quiz.setOnClickListener {
            //   interstitialAd.show()
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
            startActivity(
                Intent(this@MainActivity, LearnActivity::class.java).putExtra(
                    "type",
                    "quiz"
                )
            )
        }
        learn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    LearnActivity::class.java
                ).putExtra("type", "learn")
            )
        }
    }

    override fun onStart() {
        super.onStart()
        val settings = getSharedPreferences("switch", 0)
        if (settings.getBoolean("music", true)) {
            startService(name)
        }
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        stopService(name)
    }


}