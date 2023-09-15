package com.example.kidtest

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech.QUEUE_ADD
import android.speech.tts.TextToSpeech.QUEUE_FLUSH
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kidtest.MainActivity.Companion.t1
import com.example.kidtest.services.BackgroundSoundServices
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random


class QuizActivity : AppCompatActivity() {
    private var position: Int = 0
    private lateinit var question: MutableList<String>
    private lateinit var answer: MutableList<Int>
    private lateinit var option: MutableList<String>
    private var random: Int = 0
    private var random1: Int = 0
    private var silent: Boolean = true
    private var silent2: Boolean = true
    private var hint: Int = 0
    private lateinit var settings: SharedPreferences
    private lateinit var mediaPlayer: MediaPlayer
    private var list = mutableListOf<Int>()
    private var check: Int = 0
    private lateinit var intent1: Intent
    private var highScore: Int = 0
    var clicker = true
    private var currentScore: Int = 0
    var checkScore: Boolean = true
    private var clickcheck: Boolean = false

    var mRewardedAd: RewardedAd? = null
    var TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        CoroutineScope(Dispatchers.Unconfined).launch {
            createAndLoadRewardedAd()
        }

        intent1 = Intent(
            this, BackgroundSoundServices::
            class.java
        )
        position = intent.extras?.getInt("position") ?: 0
        when (position) {
            0 -> {
                question = mutableListOf(
                    "A for ...?",
                    "B for...?",
                    "C for...?",
                    "D for..?",
                    "E for..?",
                    "F for...?",
                    "G for...?",
                    "H for...?",
                    "I for...?",
                    "J for...?",
                    "K for..?",
                    "L for...?",
                    "M for..?",
                    "N for...?",
                    "O for...?",
                    "P for..?",
                    "Q for..?",
                    "R for..?",
                    "S for..?",
                    "T for..?",
                    "U for..?",
                    "V for..?",
                    "W for..?",
                    "X for..?",
                    "Y for..?",
                    "Z for..?"
                )
                answer = mutableListOf(
                    R.drawable.alphabet_main,
                    R.drawable.b,
                    R.drawable.cat,
                    R.drawable.dog,
                    R.drawable.elephant,
                    R.drawable.f,
                    R.drawable.giraffe,
                    R.drawable.h,
                    R.drawable.i,
                    R.drawable.j,
                    R.drawable.kangaroo,
                    R.drawable.lion,
                    R.drawable.m,
                    R.drawable.n,
                    R.drawable.owl,
                    R.drawable.panda,
                    R.drawable.q,
                    R.drawable.rabbit,
                    R.drawable.s,
                    R.drawable.tiger,
                    R.drawable.u,
                    R.drawable.v,
                    R.drawable.w,
                    R.drawable.x,
                    R.drawable.y,
                    R.drawable.z,
                    R.drawable.alphabet_main,
                    R.drawable.b,
                    R.drawable.cat
                )
                option = mutableListOf("option0", "option1", "option2", "option3")
            }
            1 -> {
                question = mutableListOf(
                    "Find Zero ?",
                    "Find One ? ",
                    "Find Two ?",
                    "Find Three ?",
                    "Find Four?",
                    "Find Five ?",
                    "Find Six ?",
                    "Find Seven ?",
                    "Find Eight ?",
                    "Find Nine ?"
                )
                answer = mutableListOf(
                    R.drawable.zero,
                    R.drawable.one,
                    R.drawable.two,
                    R.drawable.three,
                    R.drawable.four,
                    R.drawable.five,
                    R.drawable.six,
                    R.drawable.seven,
                    R.drawable.eight,
                    R.drawable.nine,
                    R.drawable.one,
                    R.drawable.two,
                    R.drawable.three
                )
                option = mutableListOf("option0", "option1", "option2", "option3")

            }

            2 -> {
                question = mutableListOf(
                    "where is Cat?",
                    "where is Dog?",
                    "where is elephant?",
                    "where is fish?",
                    "where is giraffe?",
                    "where is kangaroo?",
                    "where is lion?",
                    "where is monkey?",
                    "where is panda?",
                    "where is Rabbit?",
                    "where is tiger?",
                    "where is yak?",
                    "where is zebra?"
                )
                answer = mutableListOf(
                    R.drawable.cat,
                    R.drawable.dog,
                    R.drawable.elephant,
                    R.drawable.f,
                    R.drawable.giraffe,
                    R.drawable.kangaroo,
                    R.drawable.lion,
                    R.drawable.m,
                    R.drawable.panda,
                    R.drawable.rabbit,
                    R.drawable.tiger,
                    R.drawable.y,
                    R.drawable.z,
                    R.drawable.dog,
                    R.drawable.elephant,
                    R.drawable.cat
                )
                option = mutableListOf("option0", "option1", "option2", "option3")
            }

            3 -> {
                question = mutableListOf(
                    "Find Black..?",
                    "Find Blue..?",
                    "Find Brown..?",
                    "Find Green..?",
                    "Find Orange..?",
                    "Find Pink..?",
                    "Find Purple..?",
                    "Find Red..?",
                    "Find White..?",
                    "Find Yellow..?"
                )
                answer = mutableListOf(
                    R.drawable.black,
                    R.drawable.blue,
                    R.drawable.brown,
                    R.drawable.green,
                    R.drawable.orange,
                    R.drawable.pink,
                    R.drawable.purple,
                    R.drawable.red,
                    R.drawable.white,
                    R.drawable.yellow,
                    R.drawable.black,
                    R.drawable.blue,
                    R.drawable.brown
                )
                option = mutableListOf("option0", "option1", "option2", "option3")
            }
            4 -> {
                question = mutableListOf(
                    "Find Crow..?",
                    "Find Dove..?",
                    "Find Duck..?",
                    "Find  Eagle..?",
                    "Find Hen..?",
                    "Find Ostrich..?",
                    "Find Owl..?",
                    "Find Parrot..?",
                    "Find Peacock..?",
                    "Find Sparrow..?",
                    "Find Swan..?",
                    "Find Turkey..?",
                    "Find Woodpecker..?"
                )
                answer = mutableListOf(
                    R.drawable.crow,
                    R.drawable.dove,
                    R.drawable.duck,
                    R.drawable.eagle,
                    R.drawable.hen,
                    R.drawable.ostrich,
                    R.drawable.owl,
                    R.drawable.parrot,
                    R.drawable.peacock,
                    R.drawable.sparrow,
                    R.drawable.swan,
                    R.drawable.turkey,
                    R.drawable.woodpecker,
                    R.drawable.crow,
                    R.drawable.dove,
                    R.drawable.duck
                )
                option = mutableListOf("option0", "option1", "option2", "option3")
            }
            5 -> {
                question = mutableListOf(
                    "Find Beet..?",
                    "Find Brinjal..?",
                    "Find Cabbage..?",
                    "Find Carrot..?",
                    "Find Cauliflower..?",
                    "Find Corn..?",
                    "Find Cucumber..?",
                    "Find Garlic..?",
                    "Find Mushroom..?",
                    "Find Onion..?",
                    "Find Potato..?",
                    "Find Pumpkin..?",
                    "Find Red-chili..?",
                    "Find Tomato..?"
                )
                answer = mutableListOf(
                    R.drawable.beet,
                    R.drawable.brinjal,
                    R.drawable.cabbage,
                    R.drawable.carrot,
                    R.drawable.cauliflower,
                    R.drawable.corn,
                    R.drawable.cucumber,
                    R.drawable.garlic,
                    R.drawable.mushroom,
                    R.drawable.onion,
                    R.drawable.potato,
                    R.drawable.pumpkin,
                    R.drawable.redchili,
                    R.drawable.tomato,
                    R.drawable.beet,
                    R.drawable.brinjal,
                    R.drawable.cabbage
                )
                option = mutableListOf("option0", "option1", "option2", "option3")
            }
            6 -> {
                question = mutableListOf(
                    "Find Sunflower..?",
                    "Find Tulip..?",
                    "Find Lily..?",
                    "Find Lotus..?",
                    "Find Rose..?",
                    "Find Marigold..?",
                    "Find Jasmin..?",
                    "Find Daisy..?",
                    "Find Orchids..?"
                )
                answer = mutableListOf(
                    R.drawable.sunflower,
                    R.drawable.tulip,
                    R.drawable.lily,
                    R.drawable.lotus,
                    R.drawable.rose,
                    R.drawable.marigold,
                    R.drawable.jasmine,
                    R.drawable.daisy,
                    R.drawable.orchids,
                    R.drawable.sunflower,
                    R.drawable.tulip,
                    R.drawable.lily
                )
                option = mutableListOf("option0", "option1", "option2", "option3")
            }
            7 -> {
                question = mutableListOf(
                    "Find Airplane?",
                    "Find Bicycle?",
                    "Find Boat?",
                    "Find Bus?",
                    "Find Car?",
                    "Find Motorcycle?",
                    "Find School-bus?",
                    "Find Ship?",
                    "Find Truck?"
                )
                answer = mutableListOf(
                    R.drawable.airplane,
                    R.drawable.bicycle,
                    R.drawable.boat,
                    R.drawable.bus,
                    R.drawable.car,
                    R.drawable.motorcycle,
                    R.drawable.schoolbus,
                    R.drawable.ship,
                    R.drawable.truck,
                    R.drawable.airplane,
                    R.drawable.bicycle,
                    R.drawable.boat
                )
                option = mutableListOf("option0", "option1", "option2", "option3")

            }
            8 -> {
                question = mutableListOf(
                    "Find Hand..?",
                    "Find Lips..?",
                    "Find Leg..?",
                    "Find Ear..?",
                    "Find Eye..?",
                    "Find Eyebrow..?",
                    "Find Foot..?",
                    "find Nose..?",
                    "find Teeth..?",
                    "find Tongue..?"
                )
                answer = mutableListOf(
                    R.drawable.hand,
                    R.drawable.lips,
                    R.drawable.leg,
                    R.drawable.ear,
                    R.drawable.eye,
                    R.drawable.eyebrow,
                    R.drawable.foot,
                    R.drawable.nose,
                    R.drawable.teeth,
                    R.drawable.tongue,
                    R.drawable.hand,
                    R.drawable.lips,
                    R.drawable.leg
                )
                option = mutableListOf("option0", "option1", "option2", "option3")
            }
        }
        textView.text = "Press Start Button"
        finish_view.setImageResource(R.drawable.start2)
        Log.d("CHECKER", "CHeck")

        finish_view.setOnClickListener {
            if (check == 0) {
                lay1.visibility = VISIBLE
                finish_view.visibility = View.INVISIBLE
                check = 1
                clickcheck = true
                reply()

            } else {
                this.onBackPressed()
            }
        }
        textView.setOnClickListener{
            t1?.speak(textView.text, QUEUE_ADD, null, null)
        }

        hint_btn.setOnClickListener {
            if (clickcheck) {
                Log.d("AD", "CLICKED")
                if (hint > 0) {
                    val view1: View = lay1
                    val animation: ObjectAnimator =
                        ObjectAnimator.ofFloat(view1, "rotationY", 0.0f, 360f)

                    animation.duration = 1000

                    animation.repeatCount = 0
                    animation.interpolator = AccelerateDecelerateInterpolator()
                    animation.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                        }
                    })
                    animation.start()
                    reply()
                    hint -= 1
                    hint_text.text = hint.toString()
                    settings.edit().putInt("hint", hint).apply()
                } else {
                    val popupView = LayoutInflater.from(this).inflate(R.layout.skip_popup, null)
                    popupView.animation = AnimationUtils.loadAnimation(this, R.anim.myanim)
                    val popupWindow = PopupWindow(
                        popupView,
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT
                    )
                    popupWindow.isFocusable = true
                    popupWindow.animationStyle = R.anim.myanim
                    var watch: Button = popupView.findViewById(R.id.watch_ad_btn)
                    watch.setOnClickListener {
                        //                      if (rewardedAd.isLoaded) {
                        val activityContext: Activity = this@QuizActivity
//                            val adCallback = object : RewardedAdCallback() {
//                                override fun onRewardedAdOpened() {
//                                    Log.d("EARNED", "CLOSED1")
//
//                                    // Ad opened.
//                                }
//
//                                override fun onRewardedAdClosed() {
//                                    // Ad closed.
//                                    rewardedAd = createAndLoadRewardedAd()
//                                    Log.d("EARNED", "CLOSED")
//                                    if (silent) {
//                                        startService(intent1)
//                                    }
//                                    popupWindow.dismiss()
//                                }
//
//                                override fun onUserEarnedReward(@NonNull reward: RewardItem) {
//                                    // User earned reward.
//                                    Toast.makeText(
//                                        activityContext,
//                                        "You Earn 2 free coins...",
//                                        Toast.LENGTH_LONG
//                                    ).show()
//                                    hint += 2
//                                    hint_text.text = hint.toString()
//                                    settings.edit().putInt("hint", hint).apply()
//                                    popupWindow.dismiss()
//
//                                }
//
//                                override fun onRewardedAdFailedToShow(adError: AdError) {
//                                    // Ad failed to display.
//                                    Snackbar.make(it, "Something went wrong", Snackbar.LENGTH_LONG)
//                                        .show()
//                                }
//                            }
//                            rewardedAd.show(activityContext, adCallback)
//                        } else {
//                            Toast.makeText(this, "wait a moment and Click Again", Toast.LENGTH_LONG)
//                                .show()
                        if (mRewardedAd != null) {
                            mRewardedAd?.show(this) {
                                fun onUserEarnedReward(rewardItem: RewardItem) {
                                    rewardItem.amount
                                    rewardItem.type
                                    Log.d(TAG, "User earned the reward.")
                                }
                            }
                        } else {
                            Log.d(TAG, "The rewarded ad wasn't ready yet.")
                        }
                    }

                    var no: Button = popupView.findViewById(R.id.no_thanks_btn)
                    no.setOnClickListener {
                        popupWindow.dismiss()
                    }
                    popupWindow.showAsDropDown(popupView, 0, 0)
                }
            }
        }

    }


    fun createAndLoadRewardedAd() {

        var adRequest = AdRequest.Builder().build()

        val reward = RewardedAd.load(
            this,
            "ca-app-pub-3940256099942544/5224354917",
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError?.message)
                    mRewardedAd = null
                    Toast.makeText(
                        this@QuizActivity,
                        "Check Network Connection And try Again",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mRewardedAd = rewardedAd
                }
            })
    }


    fun reply() {
        CoroutineScope(Dispatchers.IO).launch {
            random1 = Random.nextInt(4)
            random = Random.nextInt(question.size)
            if (currentScore > highScore) {
                settings.edit().putInt(position.toString(), currentScore).apply()
            }

            while (list.contains(random)) {
                if (list.size != question.size) {
                    random = Random.nextInt(question.size)
                } else {
                    break
                }
            }
            withContext(Dispatchers.Main) {
                current_score.text = currentScore.toString()
                if (list.size == question.size - 1) {
                    textView.text = "Go For Next Quiz"
                    finish_view.setImageResource(R.drawable.finish)
                    lay1.visibility = INVISIBLE
                    finish_view.visibility = View.VISIBLE

                } else {
                    finish_view.setOnClickListener(View.OnClickListener {
                        finish()
                    })
                    textView.text = question.get(random)
                    if (!list.contains(random)) {
                        list.add(random)
                    }
                    kotlin.run {
                        t1?.speak(textView.text, QUEUE_FLUSH, null, null)
                    }

                    when (option[random1]) {
                        option0.contentDescription -> {
                            option0.setBackgroundResource(answer.get(random))
                            option1.setBackgroundResource(answer.get(random + 3))
                            option2.setBackgroundResource(answer.get(random + 1))
                            option3.setBackgroundResource(answer.get(random + 2))

                        }
                        option1.contentDescription -> {
                            option0.setBackgroundResource(answer.get(random + 3))
                            option1.setBackgroundResource(answer.get(random))
                            option2.setBackgroundResource(answer.get(random + 1))
                            option3.setBackgroundResource(answer.get(random + 2))

                        }

                        option2.contentDescription -> {
                            option0.setBackgroundResource(answer.get(random + 3))
                            option1.setBackgroundResource(answer.get(random + 1))
                            option2.setBackgroundResource(answer.get(random))
                            option3.setBackgroundResource(answer.get(random + 2))

                        }

                        option3.contentDescription -> {
                            option0.setBackgroundResource(answer.get(random + 3))
                            option1.setBackgroundResource(answer.get(random + 1))
                            option2.setBackgroundResource(answer.get(random + 2))
                            option3.setBackgroundResource(answer.get(random))

                        }
                    }
                }
            }
        }
    }


    fun onClick(view: View) {
        if (clicker) {
            Log.d("OWN", "OWN-Out")
            if (view.contentDescription == option.get(random1)) {
                clicker = false
                mediaPlayer = MediaPlayer.create(this, R.raw.correct)
                if (silent2) {
                    mediaPlayer.start()
                }
                if (checkScore) {
                    currentScore += 1
                }
                when (view.id) {
                    R.id.option0 -> {
                        option0.setImageResource(R.drawable.answer_true)
                    }
                    R.id.option1 -> {
                        option1.setImageResource(R.drawable.answer_true)
                    }
                    R.id.option2 -> {
                        option2.setImageResource(R.drawable.answer_true)
                    }
                    R.id.option3 -> {
                        option3.setImageResource(R.drawable.answer_true)
                    }
                }
                object : CountDownTimer(3000, 1000) {
                    override fun onFinish() {
                        option0.setImageDrawable(null)
                        option1.setImageDrawable(null)
                        option2.setImageDrawable(null)
                        option3.setImageDrawable(null)
                        val view1: View = lay1
                        textView.startAnimation(
                            AnimationUtils.loadAnimation(
                                this@QuizActivity,
                                R.anim.swipe
                            )
                        )
                        val animation: ObjectAnimator =
                            ObjectAnimator.ofFloat(view1, "rotationY", 0.0f, 360f)

                        animation.duration = 1000

                        animation.repeatCount = 0
                        animation.interpolator = AccelerateDecelerateInterpolator()
                        animation.addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                            }
                        })
                        animation.start()
                        reply()
                        clicker = true
                        checkScore = true
                    }

                    override fun onTick(millisUntilFinished: Long) {
                    }
                }.start()

            } else {
                if (silent2) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.wrong)
                    mediaPlayer.start()
                }
                checkScore = false
                when (view.id) {
                    R.id.option0 -> {
                        option0.setImageResource(R.drawable.answer_false)
                    }
                    R.id.option1 -> {
                        option1.setImageResource(R.drawable.answer_false)
                    }
                    R.id.option2 -> {
                        option2.setImageResource(R.drawable.answer_false)
                    }
                    R.id.option3 -> {
                        option3.setImageResource(R.drawable.answer_false)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        settings = getSharedPreferences("switch", 0)
        silent = settings.getBoolean("music", true)
        silent2 = settings.getBoolean("sound", true)
        hint = settings.getInt("hint", 0)
        highScore = settings.getInt(position.toString(), 0)
        hint_text.text = hint.toString()
        if (silent) {
            startService(intent1)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (currentScore > highScore) {
            settings.edit().putInt(position.toString(), currentScore).apply()
        }
    }

    override fun onPause() {
        super.onPause()
        stopService(intent1)
        t1?.stop()
    }

}