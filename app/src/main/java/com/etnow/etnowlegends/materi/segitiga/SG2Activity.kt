package com.etnow.etnowlegends.materi.segitiga

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
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivitySg2Binding
import com.etnow.etnowlegends.databinding.PopupQuizResultBinding
import com.etnow.etnowlegends.materi.MateriSegitigaActivity
import com.etnow.etnowlegends.materi.persegi.Persegi2Activity
import com.etnow.etnowlegends.materi.persegi.Persegi3Activity
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi
import java.util.concurrent.TimeUnit

class SG2Activity : AppCompatActivity() {

    private var binding: ActivitySg2Binding? = null
    private var result: Int? = 0
    private var isPicked: Boolean? = false
    private var time: Long? = 0L
    private var getTime: Long? = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySg2Binding.inflate(layoutInflater)
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

        binding?.view18?.setOnClickListener {
            if(isPicked == true) {

                if(intent.getStringExtra(EXTRA_OPT) == "kerjakan") {
                    val intent = Intent(this, SG3Activity::class.java)
                    intent.putExtra(SG3Activity.RESULT, result)
                    intent.putExtra(SG3Activity.TIME, time)
                    intent.putExtra(SG3Activity.EXTRA_OPT, "kerjakan")
                    startActivity(intent)
                }
                else {
                    val intent = Intent(this, SG3Activity::class.java)
                    intent.putExtra(SG3Activity.EXTRA_OPT, "pembahasan")
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
            binding?.a?.setBackgroundColor(resources.getColor(R.color.yellow))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.b?.setOnClickListener {
            result = result?.plus(1)
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
                showPopupFinishQuiz()
            }
        }.start()
    }

    @SuppressLint("SetTextI18n")
    private fun showPopupFinishQuiz() {
        val binding: PopupQuizResultBinding = PopupQuizResultBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.setContentView(binding.root)

        binding.correct.text = "Jawaban benar: $result"
        binding.wrong.text = "Jawaban salah: ${3 - result!!}"

        binding.view19.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        binding.view21.setOnClickListener {
            val intent = Intent(this, MateriSegitigaActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        binding.pembahasan.setOnClickListener {
            val intent = Intent(this, SG1Activity::class.java)
            intent.putExtra(SG1Activity.EXTRA_OPT, "pembahasan")
            startActivity(intent)
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
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