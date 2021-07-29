package com.etnow.etnowlegends.legends_adventure.stupa3

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
import com.etnow.etnowlegends.databinding.ActivityStupa3Binding
import com.etnow.etnowlegends.databinding.PopupQuizResultBinding
import com.etnow.etnowlegends.legends_adventure.StupaActivity
import com.etnow.etnowlegends.materi.persegi.PersegiActivity
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

class Stupa3Activity : AppCompatActivity() {

    private var binding: ActivityStupa3Binding? = null
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
        binding = ActivityStupa3Binding.inflate(layoutInflater)
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

    private fun deleteAllPageChoice() {
        for (i in 1..10) {
            prefs.edit().remove("page$i").apply()
        }
    }

    private fun validateAns() {
        if (page <= 10) {

            answer = binding?.etAnswer?.text.toString().trim()

            when (page) {
                1 -> {
                    prefs.edit().putString("page$page", answer).apply()
                }
                2 -> {
                    prefs.edit().putString("page$page", answer).apply()
                }
                3 -> {
                    prefs.edit().putString("page$page", answer).apply()
                }
                4 -> {
                    prefs.edit().putString("page$page", answer).apply()
                }
                5 -> {
                    prefs.edit().putString("page$page", answer).apply()
                }
                6 -> {
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

            page += 1
            answer = ""
            binding?.etAnswer?.text?.clear()
            selectedPage()
        }
    }

    private fun selectedOption() {
        if (option == "kerjakan") {
            countdownTimer()
        } else {
            isPicked = true
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

        if(option == "pembahasan") {
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
            binding?.etAnswer?.visibility = View.VISIBLE
        }

        binding?.textView39?.text =
            "Keliling suatu persegi panjang adalah 236 cm. Jika panjangnya 62 cm, berapakah lebar persegi panjang tersebut?\n"

        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let {
            Glide
                .with(this)
                .load(R.drawable.stupa3_1)
                .into(it)
        }


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


        binding?.view17?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))


        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 56 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui keliling 236, panjang = 62 cm\n" +
                    "Ditanyakan lebar?\n" +
                    "K = 2 x (p + l)\n" +
                    "236 = 2 x (62 + l)\n" +
                    "l = 236 : 2 – 62\n" +
                    "l = 56 cm\n" +
                    "Jadi, lebar persegi panjang tersebut adalah 56 cm\n"
        }

    }

    private fun loadProperty2() {
        if(option == "pembahasan") {
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
            binding?.etAnswer?.visibility = View.VISIBLE
        }
        binding?.textView39?.text = "Sebidang tanah berbentuk persegi panjang memiliki luas 950 m². Jika panjangnya 38 m, berapakah lebar sebidang tanah tersebut?\n"

        binding?.textView38?.visibility = View.GONE

        binding?.view14?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view15?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 25 Meter"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui luas 950 m ², panjang = 38 m\n" +
                    "Ditanyakan lebar?\n" +
                    "L = p x l\n" +
                    "950 = 38 x l\n" +
                    "l = 950 : 38\n" +
                    "l = 25 m\n" +
                    "Jadi, lebar sebidang tanah tersebut adalah 25 meter\n"
        }
    }

    private fun loadProperty3() {
        if(option == "pembahasan") {
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
            binding?.etAnswer?.visibility = View.VISIBLE
        }
        binding?.textView39?.text = "Keliling persegi panjang 88 cm. Jika panjangnya 26 cm, maka lebarnya adalah .... cm"
        binding?.textView38?.visibility = View.GONE

        binding?.view15?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view16?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 18 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui keliling = 88 cm, panjang = 26 cm\n" +
                    "Ditanyakan lebar?\n" +
                    "K = 2 x (p + l)\n" +
                    "88 = 2 x (26 + l)\n" +
                    "l = 88 : 2 – 26\n" +
                    "l = 18 cm\n"
        }

    }

    private fun loadProperty4() {
        if(option == "pembahasan") {
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
            binding?.etAnswer?.visibility = View.VISIBLE
        }

        binding?.textView39?.text =
            "Panjang alas segitiga 24 cm dan tinggi 18 cm. Luas segitiga tersebut adalah .... cm2"
        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let {
            Glide
                .with(this)
                .load(R.drawable.stupa3_3)
                .into(it)
        }

        binding?.view16?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view21?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 216 cm2"
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui a = 24 cm, t = 18 cm\n" +
                    "Ditanyakan luas?\n" +
                    "L = ½ x a x t\n" +
                    "L = ½ x 24 x 18\n" +
                    "L = 216 cm2\n" +
                    "Jawaban : 216 cm2\n"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadProperty5() {
        if(option == "pembahasan") {
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
            binding?.etAnswer?.visibility = View.VISIBLE
        }

        binding?.textView39?.text =
            "Jika persegi A mempunyai panjang sisi 23 cm, sedangkan panjang sisi persegi B 7 cm lebih pendek dari persegi A. Berapakah selisih keliling persegi panjang A dan B?\n"

        binding?.textView38?.visibility = View.GONE

        binding?.view21?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view22?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: A. 18 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Diketahui : \n" +
                        "Sisi persegi A = 23 cm \n" +
                        "Sisi persegi B = 7 cm lebih pendek dari persegi A \n" +
                        "= 23 cm – 7 cm \n" +
                        "= 16 cm\n" +
                        "Penyelesaian :\n" +
                        "Keliling persegi A = 4 x s\n" +
                        "= 4 x 23 = 92 cm \n" +
                        "Keliling persegi B = 4 x s\n" +
                        "= 4 x 16 = 74 cm\n" +
                        "Selisih  keliling persegi A dan persegi B = 92 – 74 = 18 cm\n" +
                        "Jadi selisih keliling dari kedua persegi tersebut yaitu 18 cm\n" +
                        "Jawaban : 18 cm\n"
        }
    }

    private fun loadProperty6() {
        if(option == "pembahasan") {
            isPicked = true
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
            binding?.etAnswer?.visibility = View.VISIBLE
        }

        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let { Glide.with(this).load(R.drawable.stupa3_6).into(it) }

        binding?.textView39?.text =
            "Tentukan Luas  persegi panjang berikut!"


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

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 112 cm2"
            binding?.textView40?.text = "Pembahasan :\n" +
                    "Luas persegi panjang = p x l\n" +
                    "= 14 x 8 \n" +
                    "=  112 cm2\n"
        }
    }

    private fun loadProperty7() {
        if(option == "pembahasan") {
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true

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

        binding?.textView39?.text = "Sepetak sawah berbentuk persegi dengan panjang sisi 50 m. Luas sawah tersebut adalah .... cm2\n"

        binding?.textView38?.visibility = View.GONE


        binding?.view24?.background?.setTint(resources.getColor(R.color.green))
        binding?.view25?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view26?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view27?.background?.setTint(resources.getColor(R.color.gray_et))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 2500 cm2"
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui panjang sisi = 50 cm\n" +
                    "Ditanyakan luas?\n" +
                    "L = s x s\n" +
                    "L = 50 x 50\n" +
                    "L = 2.500 cm2\n"
        }
    }

    private fun loadProperty8() {
        if(option == "pembahasan") {
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
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

        binding?.textView39?.text = "Jika lebar persegi panjang 81 cm dan keliling persegi panjang tersebut 456 cm. Berapakah panjangnya?\n"
        binding?.textView38?.visibility = View.GONE

        binding?.view24?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view25?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 157 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "keliling = 2 x (p + l)\n" +
                        "456 cm = 2 x (p + 81)\n" +
                        "456 cm = 2p + 162 \n" +
                        "2p = 456 – 142 \n" +
                        "2p = 314\n" +
                        "p  = 314 : 2\n" +
                        "   = 157 cm\n" +
                        "Jadi panjang dari bangun tersebut yaitu 157 cm.\n"
        }
    }

    private fun loadProperty9() {
        if(option == "pembahasan") {
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
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

        binding?.textView39?.text = "Beni mempunyai dua buah persegi yang diberi nama persegi X dan persegi Y. Persegi X mempunyai panjang sisi 15 cm, sedangkan panjang sisi persegi Y 4 cm lebih panjang dari persegi X. Berapakah selisih keliling persegi Beni?\n"

        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let { Glide.with(this).load(R.drawable.stupa3_9).into(it) }

        binding?.view25?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view26?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 16 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Penyelesaian:\n" +
                        "Keliling persegi X = 4 x 15 = 60\n" +
                        "Panjang sisi persegi Y = 15 + 4 = 19\n" +
                        "Keliling persegi Y = 4 x 19 = 76\n" +
                        "Selisih keliling kedua persegi = 76 – 60 = 16\n" +
                        "Jadi, selisih keliling persegi Beni adalah 16 cm.\n"
        }
    }

    private fun loadProperty10() {
        if(option == "pembahasan") {
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
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

        binding?.textView39?.text = "Diketahui segitiga KLM merupakan segitiga sama kaki. Sisi KL dan sisi KM sama panjang yaitu 26 cm. Jika keliling segitiga KLM 83 cm, maka panjang sisi LM adalah .... cm\n"
        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let { Glide.with(this).load(R.drawable.stupa3_10).into(it) }

        binding?.view26?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view27?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.gray_et))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 31 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui sisi KL dan sisi KM = 26 cm, keliling = 83 cm\n" +
                        "Ditanyakan sisi LM?\n" +
                        "K = sisi KL + sisi KM + sisi LM\n" +
                        "Sisi LM = K - (sisi KL + sisi KM)\n" +
                        "Sisi LM = 83 - (26 + 26)\n" +
                        "Sisi LM = 31 cm\n" +
                        "Jawaban : 31 cn\n"
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
            prefs.edit().putBoolean("stupa4", true).apply()
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

    private fun getPageChoice() {
        for (i in 1..10) {
            val validator = prefs.getString("page$i", "")


            if (i == 1 && validator == "56") {
                result = result?.plus(1)
            } else if (i == 2 && validator == "25") {
                result = result?.plus(1)
            } else if (i == 3 && validator == "18") {
                result = result?.plus(1)
            } else if (i == 4 && validator == "216") {
                result = result?.plus(1)
            } else if (i == 5 && validator == "18") {
                result = result?.plus(1)
            } else if (i == 6 && validator == "112") {
                result = result?.plus(1)
            } else if (i == 7 && validator == "2500") {
                result = result?.plus(1)
            } else if (i == 8 && validator == "157") {
                result = result?.plus(1)
            } else if (i == 9 && validator == "16") {
                result = result?.plus(1)
            } else if (i == 10 && validator == "31") {
                result = result?.plus(1)
            }

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
        countDownTimer.cancel()
    }

    companion object {
        const val EXTRA_OPT = "opt"
    }
}