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
import com.etnow.etnowlegends.HomeActivity
import com.etnow.etnowlegends.OnboardingActivity
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivityPp3Binding
import com.etnow.etnowlegends.databinding.PopupQuizResultBinding
import com.etnow.etnowlegends.materi.MateriBangunDatarActivity
import com.etnow.etnowlegends.materi.MateriPersegiPanjangActivity
import com.etnow.etnowlegends.materi.persegi.Persegi2Activity
import com.etnow.etnowlegends.materi.persegi.Persegi3Activity
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi
import java.util.concurrent.TimeUnit

class PP3Activity : AppCompatActivity() {

    private var binding: ActivityPp3Binding? = null
    private var result: Int? = 0
    private var isPicked: Boolean? = false
    private var time: Long? = 0L
    private var getTime: Long? = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPp3Binding.inflate(layoutInflater)
        setContentView(binding?.root)

        if(intent.getStringExtra(EXTRA_OPT) == "kerjakan") {
            result = intent.getIntExtra(RESULT, 0)
            getTime = intent.getLongExtra(TIME, 0L)
            countdownTimer(getTime!!)
            pickedChoice()
        }
        else {
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

        binding?.finish?.setOnClickListener {
            if(isPicked == true) {
                showQuizResult()
            }
            else {
                Toast.makeText(this, "Silahkan pilih jawaban kamu terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showQuizResult() {
        val binding: PopupQuizResultBinding = PopupQuizResultBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.setContentView(binding.root)

        binding.correct.text = "Jawaban benar: " + result.toString()
        binding.wrong.text = "Jawaban salah: " + (3 - result!!).toString()

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
            intent.putExtra(PPActivity.EXTRA_OPT, "pembahasan")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    private fun pickedChoice() {
        binding?.a?.setOnClickListener {
            result = result?.plus(1)
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.yellow))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.b?.setOnClickListener {
            result = result?.plus(0)
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.yellow))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.c?.setOnClickListener {
            result = result?.plus(0)
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.yellow))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.d?.setOnClickListener {
            result = result?.plus(0)
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.yellow))
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
                showQuizResult()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val TIME = "time"
        const val RESULT = "result"
        const val EXTRA_OPT = "opt"
    }
}