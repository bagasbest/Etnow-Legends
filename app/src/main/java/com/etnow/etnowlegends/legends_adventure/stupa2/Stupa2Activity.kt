package com.etnow.etnowlegends.legends_adventure.stupa2

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Environment
import android.os.StrictMode
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.HomeActivity
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivityStupa2Binding
import com.etnow.etnowlegends.databinding.PopupQuizResultBinding
import com.etnow.etnowlegends.legends_adventure.StupaActivity
import com.etnow.etnowlegends.materi.persegi.PersegiActivity
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

class Stupa2Activity : AppCompatActivity() {

    private var binding: ActivityStupa2Binding? = null
    private var page: Int = 1
    private var result: Int? = 0
    private var isPicked: Boolean? = false
    private var time: Long? = 0L
    private var option: String? = null
    private var answer: String? = null
    private lateinit var prefs: SharedPreferences
    private var mpSfx: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStupa2Binding.inflate(layoutInflater)
        setContentView(binding?.root)

        option = intent.getStringExtra(PersegiActivity.EXTRA_OPT)

        selectedOption()

        selectedPage()

        prefs = getSharedPreferences(
            "com.etnow.etnowlegends", Context.MODE_PRIVATE
        )


        binding?.back?.setOnClickListener {
            deleteAllPageChoice()
            onBackPressed()
        }

        binding?.help?.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragmentPersegi()
            binding?.help?.setOnClickListener {
                bottomSheetFragment.show(supportFragmentManager, "ButtonSheetDialog")
            }
        }

        binding?.view18?.setOnClickListener {
            if (isPicked == true) {
                validateAns()
            } else {
                Toast.makeText(
                    this,
                    "Silahkan pilih jawaban kamu terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding?.view17?.setOnClickListener {
            if (page > 1) {
                page -= 1
                selectedPage()
            }
        }

        binding?.finish?.setOnClickListener {
            if (isPicked == true) {
                validateAns()
            } else {
                Toast.makeText(
                    this,
                    "Silahkan pilih jawaban kamu terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun validateAns() {
        if (page <= 10) {

            answer = binding?.etAnswer?.text.toString().trim()

            when (page) {
                4 -> {
                    prefs.edit().putString("page$page", answer).apply()
                }
                7 -> {
                    prefs.edit().putString("page$page", answer).apply()
                }
                8 -> {
                    prefs.edit().putString("page$page", answer).apply()
                }
                9 -> {
                    prefs.edit().putString("page$page", answer).apply()
                }
                10 -> {
                    prefs.edit().putString("page$page", answer).apply()
                    showPopupFinishQuiz()
                }
            }

            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
            page += 1
            answer = ""
            binding?.etAnswer?.text?.clear()
            selectedPage()
        }
    }

    private fun selectedOption() {
        if (option == "kerjakan") {
            countdownTimer()
            pickedChoice()
        } else {
            isPicked = true
            binding?.pilgan?.visibility = View.GONE
            binding?.textView35?.visibility = View.VISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView36?.visibility = View.INVISIBLE
            binding?.textView37?.visibility = View.INVISIBLE
        }
    }

    private fun selectedPage() {
        when (page) {
            1 -> {
                loadProperty1()
            }
            2 -> {
                loadProperty2()
            }
            3 -> {
                loadProperty3()
            }
            4 -> {
                loadProperty4()
            }
            5 -> {
                loadProperty5()
            }
            6 -> {
                loadProperty6()
            }
            7 -> {
                loadProperty7()
            }
            8 -> {
                loadProperty8()
            }
            9 -> {
                loadProperty9()
            }
            10 -> {
                loadProperty10()
            }
        }
    }

    private fun loadProperty1() {
        binding?.etAnswer?.visibility = View.INVISIBLE
        binding?.textView39?.text =
            "Suatu persegi panjang memiliki panjang 28 cm dan lebar 9 cm, maka luasnya adalah .... cm2"

        binding?.textView38?.visibility = View.GONE


        binding?.view14?.visibility = View.VISIBLE
        binding?.view15?.visibility = View.VISIBLE
        binding?.view16?.visibility = View.VISIBLE
        binding?.view21?.visibility = View.VISIBLE
        binding?.view22?.visibility = View.VISIBLE
        binding?.view23?.visibility = View.VISIBLE

        binding?.view24?.visibility = View.GONE
        binding?.view25?.visibility = View.GONE
        binding?.view26?.visibility = View.GONE
        binding?.view27?.visibility = View.GONE

        binding?.view14?.background?.setTint(resources.getColor(R.color.green))
        binding?.view15?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view16?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view21?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view22?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view23?.background?.setTint(resources.getColor(R.color.gray_et))


        binding?.finish?.visibility = View.GONE

        binding?.button6?.text = "K.D. 4.2"

        binding?.a?.text = "a. 242"
        binding?.b?.text = "b. 250"
        binding?.c?.text = "c. 252"
        binding?.d?.text = "d. 264"

        binding?.view17?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))


        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: C. 252"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui panjang = 28 cm, lebar = 9 cm\n" +
                    "Ditanyakan luas ?\n" +
                    "L = p x l\n" +
                    "L = 28 x 9\n" +
                    "L = 252 m2\n" +
                    "Jawaban : c. 252\n"
        }

    }

    private fun loadProperty2() {
        binding?.textView39?.text =
            "Sepetak sawah berbentuk persegi dengan panjang sisi 50 m. Luas sawah tersebut adalah .... cm2"

        binding?.textView38?.visibility = View.GONE

        binding?.view14?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view15?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.a?.text = "a. 2000"
        binding?.b?.text = "b. 2250"
        binding?.c?.text = "c. 2500"
        binding?.d?.text = "d. 3000"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: C. 2500"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui panjang sisi = 50 cm\n" +
                    "Ditanyakan luas?\n" +
                    "L = s x s\n" +
                    "L = 50 x 50\n" +
                    "L = 2.500 cm2\n"
        }
    }

    private fun loadProperty3() {
        binding?.textView39?.text = "Keliling bangun di samping adalah .... cm\n" +
                "Diketahui panjang sisi segitiga = 6 cm, 8 cm, dan 10 cm"
        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let {
            Glide
                .with(this)
                .load(R.drawable.stupa2_3)
                .into(it)
        }

        binding?.view15?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view16?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.a?.text = "a. 21"
        binding?.b?.text = "b. 22"
        binding?.c?.text = "c. 24"
        binding?.d?.text = "d. 25"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: D. 4"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui panjang sisi segitiga = 6 cm, 8 cm, dan 10 cm\n" +
                    "Ditanyakan keliling?\n" +
                    "K = sisi a + sisi b + sisi c\n" +
                    "K = 6 cm + 8 cm + 10 cm\n" +
                    "K = 24 cm"
        }

    }

    private fun loadProperty4() {
        if (option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        } else {
            isPicked = true
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.VISIBLE
        }

        binding?.textView39?.text =
            "Tentukan keliling relief candi yang berbentuk persegi panjang berikut!"
        binding?.textView38?.let {
            Glide
                .with(this)
                .load(R.drawable.stupa2_4)
                .into(it)
        }

        binding?.view16?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view21?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 44 cm"
            binding?.textView40?.text = "Penyelesaian :\n" +
                    "Keliling persegi panjang = 2 x (p + l)\n" +
                    "= 2 x ( 14 + 8 )\n" +
                    "= 2 x 22 \n" +
                    "= 44 cm  \n"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadProperty5() {
        if (option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        } else {
            isPicked = true

            binding?.pilgan?.visibility = View.VISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }

        binding?.textView39?.text =
            "Jika panjang sisi persegi pada tembok candi  48 cm, maka kelilingnya adalah .... cm\n"

        binding?.textView38?.let {
            Glide.with(this)
                .load(R.drawable.stupa2_5)
                .into(it)
        }

        binding?.view21?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view22?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.a?.text = "a. 30"
        binding?.b?.text = "b. 35"
        binding?.c?.text = "c. 40"
        binding?.d?.text = "d. 45"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: A. 192"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui panjang sisi = 48 cm \n" +
                        "Ditanyakan keliling?\n" +
                        "K = 4 x s\n" +
                        "K = 4 x 48\n" +
                        "K = 192 cm\n"
        }
    }

    private fun loadProperty6() {
        if (option == "pembahasan") {
            isPicked = true
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        } else {
            isPicked = true

            binding?.pilgan?.visibility = View.VISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }

        binding?.textView38?.visibility = View.GONE

        binding?.textView39?.text =
            "Keliling segitiga sama sisi dengan panjang sisi 26 cm adalah .... cm"


        binding?.view14?.visibility = View.VISIBLE
        binding?.view15?.visibility = View.VISIBLE
        binding?.view16?.visibility = View.VISIBLE
        binding?.view21?.visibility = View.VISIBLE
        binding?.view22?.visibility = View.VISIBLE
        binding?.view23?.visibility = View.VISIBLE

        binding?.view24?.visibility = View.GONE
        binding?.view25?.visibility = View.GONE
        binding?.view26?.visibility = View.GONE
        binding?.view27?.visibility = View.GONE

        binding?.view14?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view15?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view16?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view21?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view22?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view23?.background?.setTint(resources.getColor(R.color.green))


        binding?.button6?.text = "K.D. 4.2"

        binding?.a?.text = "a. Persegi"
        binding?.b?.text = "b. Segitiga"
        binding?.c?.text = "c. Trapesium"
        binding?.d?.text = "d. Jajar Genjang"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: B. 78"
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui panjang sisi = 26 cm\n" +
                    "Ditanyakan keliling segitiga sama sisi?\n" +
                    "K = 3 x panjang sisi\n" +
                    "K = 3 x 26 cm\n" +
                    "K = 78 cm\n"
        }
    }

    private fun loadProperty7() {
        if (option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        } else {
            isPicked = true

            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.VISIBLE
        }
        binding?.view14?.visibility = View.GONE
        binding?.view15?.visibility = View.GONE
        binding?.view16?.visibility = View.GONE
        binding?.view21?.visibility = View.GONE
        binding?.view22?.visibility = View.GONE
        binding?.view23?.visibility = View.GONE

        binding?.view24?.visibility = View.VISIBLE
        binding?.view25?.visibility = View.VISIBLE
        binding?.view26?.visibility = View.VISIBLE
        binding?.view27?.visibility = View.VISIBLE

        binding?.textView39?.text =
            "Diketahui panjang dan lebar sebuah tembok candi yang berbentuk persegi panjang berturut-turut 57 cm dan 43 cm. Tentukan keliling bangun tersebut!"

        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let {
            Glide.with(this)
                .load(R.drawable.stupa2_7)
                .into(it)
        }

        binding?.view24?.background?.setTint(resources.getColor(R.color.green))
        binding?.view25?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view26?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view27?.background?.setTint(resources.getColor(R.color.gray_et))

        binding?.button6?.text = "K.D. 4.2"

        binding?.a?.text = "a. Semua sisinya sama"
        binding?.b?.text = "b. Memiliki 3 sudut sama besar"
        binding?.c?.text = "c. Memiliki 3 sisi"
        binding?.d?.text = "d. Memiliki dua simetri lipat"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 200 cm"
            binding?.textView40?.text = "Penyelesaian :\n" +
                    "Keliling persegi panjang = 2 x (p + l)\n" +
                    "= 2 x (57 + 43)\n" +
                    "= 2 x 100 \n" +
                    "= 200 cm\n"
        }
    }

    private fun loadProperty8() {
        if (option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        } else {
            isPicked = true

            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.VISIBLE
        }
        binding?.view14?.visibility = View.GONE
        binding?.view15?.visibility = View.GONE
        binding?.view16?.visibility = View.GONE
        binding?.view21?.visibility = View.GONE
        binding?.view22?.visibility = View.GONE
        binding?.view23?.visibility = View.GONE

        binding?.view24?.visibility = View.VISIBLE
        binding?.view25?.visibility = View.VISIBLE
        binding?.view26?.visibility = View.VISIBLE
        binding?.view27?.visibility = View.VISIBLE

        binding?.textView39?.text = "Keliling bangun pada gambar di atas adalah .... cm"
        binding?.textView38?.let { Glide.with(this).load(R.drawable.stupa2_8).into(it) }

        binding?.view24?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view25?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: 128"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui panjang sisi bangun I = 24 cm , panjang sisi bangun II dan III = 8 cm\n" +
                        "Ditanyakan keliling ?\n" +
                        "K = sisi bangun I + sisi bangun II + sisi bangun III\n" +
                        "K = (3 x 24 + 8) + (3 x 8) + (3 x 8)\n" +
                        "K = 80 cm + 24 cm + 24 cm\n" +
                        "K = 128 cm\n"
        }
    }

    private fun loadProperty9() {
        if (option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        } else {
            isPicked = true

            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.VISIBLE
        }
        binding?.view14?.visibility = View.GONE
        binding?.view15?.visibility = View.GONE
        binding?.view16?.visibility = View.GONE
        binding?.view21?.visibility = View.GONE
        binding?.view22?.visibility = View.GONE
        binding?.view23?.visibility = View.GONE

        binding?.view24?.visibility = View.VISIBLE
        binding?.view25?.visibility = View.VISIBLE
        binding?.view26?.visibility = View.VISIBLE
        binding?.view27?.visibility = View.VISIBLE

        binding?.textView39?.text =
            "Selembar kain dengan ukuran panjang 150 cm dan lebarnya 75 cm. Keliling kain tersebut adalah .... cm."

        binding?.textView38?.visibility = View.GONE

        binding?.view25?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view26?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: 450 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui panjang = 150 cm, lebar 75 cm\n" +
                        "Ditanyakan keliling?\n" +
                        "K = 2 x (p + l)\n" +
                        "K = 2 x (150 + 75)\n" +
                        "K = 450 cm\n"
        }
    }

    private fun loadProperty10() {
        if (option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        } else {
            isPicked = true

            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.VISIBLE
        }
        binding?.view14?.visibility = View.GONE
        binding?.view15?.visibility = View.GONE
        binding?.view16?.visibility = View.GONE
        binding?.view21?.visibility = View.GONE
        binding?.view22?.visibility = View.GONE
        binding?.view23?.visibility = View.GONE

        binding?.view24?.visibility = View.VISIBLE
        binding?.view25?.visibility = View.VISIBLE
        binding?.view26?.visibility = View.VISIBLE
        binding?.view27?.visibility = View.VISIBLE

        binding?.finish?.visibility = View.VISIBLE

        binding?.textView39?.text =
            "Diketahui panjang dan lebar sebuah persegi panjang berturut-turut 57 cm dan 43 cm. Tentukan keliling persegi panjang!\n"

        binding?.view26?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view27?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.gray_et))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: 200 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Penyelesaian :\n" +
                        "Keliling persegi panjang = 2 x (p + l)\n" +
                        "= 2 x (57 + 43)\n" +
                        "= 2 x 100 \n" +
                        "= 200 cm\n"
        }
    }


    private fun pickedChoice() {
        binding?.a?.setOnClickListener {
            prefs.edit().putString("page$page", "a").apply()
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.darker_green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.b?.setOnClickListener {
            prefs.edit().putString("page$page", "b").apply()
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.darker_green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.c?.setOnClickListener {
            prefs.edit().putString("page$page", "c").apply()
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.darker_green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.d?.setOnClickListener {
            prefs.edit().putString("page$page", "d").apply()
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.darker_green))
        }
    }

    private fun countdownTimer() {
        countDownTimer.start()
    }

    private var countDownTimer = object : CountDownTimer(1000 * 600, 1000) {
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

    }

    @SuppressLint("SetTextI18n")
    private fun showPopupFinishQuiz() {
        val binding: PopupQuizResultBinding = PopupQuizResultBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.setContentView(binding.root)

        if (option == "kerjakan") {
            getPageChoice()
        }

        val sfx = prefs.getBoolean("sfx", false)
        val name = prefs.getString("key", "")
        val school = prefs.getString("school", "")

        binding.name.text = "Nama: $name"
        binding.school.text = "Sekolah: $school"

        if (result!! > 5) {
            Glide
                .with(this)
                .load(R.drawable.more_five)
                .into(binding.imageView10)
            prefs.edit().putBoolean("stupa3", true).apply()
            checkSfx(sfx, "win")

        } else {
            Glide
                .with(this)
                .load(R.drawable.less_five)
                .into(binding.imageView10)
            checkSfx(sfx, "lose")
        }

        binding.correct.text = "Jawaban benar: $result"
        binding.wrong.text = "Jawaban salah: ${10 - result!!}"

        binding.view19.setOnClickListener {
            deleteAllPageChoice()
            dialog.dismiss()
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        binding.view21.setOnClickListener {
            deleteAllPageChoice()
            dialog.dismiss()
            val intent = Intent(this, StupaActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.pembahasan.setOnClickListener {
            dialog.dismiss()
            page = 1
            option = "pembahasan"
            selectedOption()
            selectedPage()
        }

        binding.view20.setOnClickListener {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                PackageManager.PERMISSION_GRANTED
            )
            val builder = StrictMode.VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())

            val timeInMillis = System.currentTimeMillis().toString()

            try {
                val view1 = binding.root
                view1.isDrawingCacheEnabled = true
                val bitmap = Bitmap.createBitmap(view1.drawingCache)
                view1.isDrawingCacheEnabled = false

                val filePath = Environment.getExternalStorageDirectory()
                    .toString() + "/Download/" + timeInMillis + ".jpg"
                val fileScreenshot = File(filePath)
                var fileOutputStream: FileOutputStream? = null
                try {
                    fileOutputStream = FileOutputStream(fileScreenshot)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fileOutputStream)
                    fileOutputStream.flush()
                    fileOutputStream.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                val intent = Intent(Intent.ACTION_VIEW)
                val uri: Uri = Uri.fromFile(fileScreenshot)
                intent.setDataAndType(uri, "image/jpeg")
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                this.startActivity(intent)

                Toast.makeText(this, "Berhasil mendownload pencapaian", Toast.LENGTH_SHORT).show()

            } catch (e: Throwable) {
                e.printStackTrace()
            }

        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    private fun deleteAllPageChoice() {
        for (i in 1..10) {
            prefs.edit().remove("page$i").apply()
        }
    }

    private fun getPageChoice() {
        for (i in 1..10) {
            val validator = prefs.getString("page$i", "")

            if (i == 1 && validator == "c") {
                result = result?.plus(1)
            } else if (i == 2 && validator == "c") {
                result = result?.plus(1)
            } else if (i == 3 && validator == "c") {
                result = result?.plus(1)
            } else if (i == 4 && validator == "44") {
                result = result?.plus(1)
            } else if (i == 5 && validator == "d") {
                result = result?.plus(1)
            } else if (i == 6 && validator == "b") {
                result = result?.plus(1)
            } else if (i == 7 && validator == "200") {
                result = result?.plus(1)
            } else if (i == 8 && validator == "128") {
                result = result?.plus(1)
            } else if (i == 9 && validator == "450") {
                result = result?.plus(1)
            } else if (i == 10 && validator == "200") {
                result = result?.plus(1)
            }
        }
    }

    private fun checkSfx(sfx: Boolean, hasil: String) {
        if (sfx) {
            mpSfx = if (hasil == "win") {
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
        countDownTimer.cancel()
    }

    companion object {
        const val EXTRA_OPT = "opt"
    }
}