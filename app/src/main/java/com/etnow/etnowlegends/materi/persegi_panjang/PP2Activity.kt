package com.etnow.etnowlegends.materi.persegi_panjang

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.HomeActivity
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivityPp2Binding
import com.etnow.etnowlegends.databinding.PopupQuizResultBinding
import com.etnow.etnowlegends.materi.MateriPersegiPanjangActivity
import com.etnow.etnowlegends.materi.persegi.Persegi2Activity
import com.etnow.etnowlegends.materi.persegi.Persegi3Activity
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi
import java.util.concurrent.TimeUnit

class PP2Activity : AppCompatActivity() {
    
    private var binding: ActivityPp2Binding? = null
    private var result: Int? = 0
    private var isPicked: Boolean? = false
    private var time: Long? = 0L
    private var getTime: Long? = 0L
    private lateinit var prefs: SharedPreferences
    private var mpSfx: MediaPlayer? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPp2Binding.inflate(layoutInflater)
        setContentView(binding?.root)

        if(intent.getStringExtra(EXTRA_OPT) == "kerjakan") {
            result = intent.getIntExtra(RESULT, 0)
            getTime = intent.getLongExtra(TIME, 0L)
            countdownTimer(getTime!!)
            pickedChoice()
        } else {
            isPicked = true
            binding?.pilgan?.visibility = View.GONE
            binding?.textView35?.visibility = View.VISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView36?.visibility = View.INVISIBLE
            binding?.textView37?.visibility = View.INVISIBLE
        }

        prefs = getSharedPreferences(
            "com.etnow.etnowlegends", Context.MODE_PRIVATE
        )
        binding?.back?.setOnClickListener {
            onBackPressed()
        }

        binding?.help?.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragmentPersegi()
            binding?.help?.setOnClickListener {
                bottomSheetFragment.show(supportFragmentManager, "ButtonSheetDialog")
            }
        }

        binding?.view18?.setOnClickListener {
            if(isPicked == true) {

                if(intent.getStringExtra(EXTRA_OPT) == "kerjakan") {
                    val intent = Intent(this, PP3Activity::class.java)
                    intent.putExtra(PP3Activity.RESULT, result)
                    intent.putExtra(PP3Activity.TIME, time)
                    intent.putExtra(PP3Activity.EXTRA_OPT, "kerjakan")
                    startActivity(intent)
                }
                else {
                    val intent = Intent(this, PP3Activity::class.java)
                    intent.putExtra(PP3Activity.EXTRA_OPT, "pembahasan")
                    startActivity(intent)
                }
            }
            else {
                Toast.makeText(this, "Silahkan pilih jawaban kamu terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }

        binding?.view17?.setOnClickListener {
            onBackPressed()
        }

    }

    private fun pickedChoice() {
        binding?.a?.setOnClickListener {
            result = result?.plus(0)
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.darker_green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.b?.setOnClickListener {
            result = result?.plus(1)
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.darker_green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.c?.setOnClickListener {
            result = result?.plus(0)
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.darker_green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.d?.setOnClickListener {
            result = result?.plus(0)
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.darker_green))
        }
    }

    private fun countdownTimer(getTime: Long) {
        object : CountDownTimer(getTime, 1000) {
            override fun onTick(p0: Long) {
                time = p0
                binding?.textView37?.text = getString(
                    R.string.formatted_time,
                    TimeUnit.MILLISECONDS.toMinutes(p0) % 60,
                    TimeUnit.MILLISECONDS.toSeconds(p0) % 60
                )
            }

            override fun onFinish() {
                showPopupFinishQuiz()
            }
        }.start()
    }

    @SuppressLint("SetTextI18n")
    private fun showPopupFinishQuiz() {
        val binding: PopupQuizResultBinding = PopupQuizResultBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.setContentView(binding.root)

        val sfx = prefs.getBoolean("sfx", false)
        val name = prefs.getString("key", "")
        val school = prefs.getString("school", "")

        binding.name.text = "Nama: $name"
        binding.school.text = "Sekolah: $school"

        if (result!! >= 2) {
            Glide
                .with(this)
                .load(R.drawable.more_five)
                .into(binding.imageView10)
            checkSfx(sfx, "win")
        } else {
            Glide
                .with(this)
                .load(R.drawable.less_five)
                .into(binding.imageView10)
            checkSfx(sfx, "lose")
        }

        binding.correct.text = "Jawaban benar: $result"
        binding.wrong.text = "Jawaban salah: ${3 - result!!}"

        binding.view19.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        binding.view21.setOnClickListener {
            val intent = Intent(this, MateriPersegiPanjangActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.pembahasan.setOnClickListener {
            val intent = Intent(this, PPActivity::class.java)
            intent.putExtra(PPActivity.EXTRA_OPT, "pembahasan")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }
    }

    private fun checkSfx(sfx: Boolean, hasil: String) {
        if (sfx) {
            mpSfx = if(hasil == "win") {
                MediaPlayer.create(this, R.raw.win)
            } else {
                MediaPlayer.create(this, R.raw.lose)
            }
            mpSfx?.start()
            mpSfx?.setOnCompletionListener {
                onSongComplete()
            }

        }
    }

    private fun onSongComplete() {
        mpSfx?.release()
        mpSfx = null
    }

    override fun onStop() {
        super.onStop()
        onSongComplete()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val RESULT = "result"
        const val TIME = "time"
        const val EXTRA_OPT = "opt"
    }
    
}