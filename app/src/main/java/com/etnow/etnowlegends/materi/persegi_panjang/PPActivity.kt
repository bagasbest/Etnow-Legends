package com.etnow.etnowlegends.materi.persegi_panjang

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.HomeActivity
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivityPpactivityBinding
import com.etnow.etnowlegends.databinding.PopupQuizResultBinding
import com.etnow.etnowlegends.materi.MateriPersegiPanjangActivity
import com.etnow.etnowlegends.materi.persegi.Persegi2Activity
import com.etnow.etnowlegends.materi.persegi.Persegi3Activity
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi
import java.util.concurrent.TimeUnit

class PPActivity : AppCompatActivity() {


    private var binding : ActivityPpactivityBinding? = null
    private var result: Int? = 0
    private var isPicked: Boolean? = false
    private var time: Long? = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPpactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if(intent.getStringExtra(EXTRA_OPT) == "kerjakan") {
            countdownTimer()
            pickedChoice()
        } else {
            isPicked = true
            binding?.pilgan?.visibility = View.GONE
            binding?.textView35?.visibility = View.VISIBLE
            binding?.textView40?.visibility = View.VISIBLE
        }

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
                    val intent = Intent(this, PP2Activity::class.java)
                    intent.putExtra(PP2Activity.RESULT, result)
                    intent.putExtra(PP2Activity.TIME, time)
                    intent.putExtra(PP2Activity.EXTRA_OPT, "kerjakan")
                    startActivity(intent)
                }
                else {
                    val intent = Intent(this, PP2Activity::class.java)
                    intent.putExtra(PP2Activity.EXTRA_OPT, "pembahasan")
                    startActivity(intent)
                }
            }
            else {
                Toast.makeText(this, "Silahkan pilih jawaban kamu terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun pickedChoice() {
        binding?.a?.setOnClickListener {
            result = 0
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.yellow))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.b?.setOnClickListener {
            result = 0
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.yellow))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.c?.setOnClickListener {
            result = 0
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.yellow))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.d?.setOnClickListener {
            result = 1
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.yellow))
        }
    }

    private fun countdownTimer() {
        countDownTimer.start()
    }

    private var countDownTimer = object : CountDownTimer(1000 * 300, 1000) {
        override fun onTick(p0: Long) {
            time = p0
            binding?.textView37?.text = getString(
                R.string.formatted_time,
                TimeUnit.MILLISECONDS.toMinutes(p0) % 60,
                TimeUnit.MILLISECONDS.toSeconds(p0) % 60)
        }

        override fun onFinish() {
            showPopupFinishQuiz()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun showPopupFinishQuiz() {
        val binding: PopupQuizResultBinding = PopupQuizResultBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.setContentView(binding.root)

        if(result!! > 2) {
            Glide
                .with(this)
                .load(R.drawable.more_five)
                .into(binding.imageView10)
        } else {
            Glide
                .with(this)
                .load(R.drawable.less_five)
                .into(binding.imageView10)
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
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        binding.pembahasan.setOnClickListener {
            val intent = Intent(this, PPActivity::class.java)
            intent.putExtra(EXTRA_OPT, "pembahasan")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        countDownTimer.cancel()
    }

    companion object {
        const val EXTRA_OPT = "option"
    }
}