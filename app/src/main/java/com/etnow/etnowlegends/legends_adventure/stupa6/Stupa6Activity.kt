package com.etnow.etnowlegends.legends_adventure.stupa6

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
import com.etnow.etnowlegends.databinding.ActivityStupa6Binding
import com.etnow.etnowlegends.databinding.PopupQuizResultBinding
import com.etnow.etnowlegends.legends_adventure.StupaActivity
import com.etnow.etnowlegends.materi.persegi.PersegiActivity
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

class Stupa6Activity : AppCompatActivity() {

    private var binding: ActivityStupa6Binding? = null
    private var page: Int = 1
    private var result: Int? = 0
    private var validator: String? = null
    private var isPicked: Boolean? = false
    private var time: Long? = 0L
    private var option: String? = null
    private var answer: String? = null
    private lateinit var prefs: SharedPreferences
    private var mpSfx: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStupa6Binding.inflate(layoutInflater)
        setContentView(binding?.root)
        option = intent.getStringExtra(PersegiActivity.EXTRA_OPT)

        selectedOption()

        selectedPage()

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
            if (page > 1 && option == "pembahasan") {
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

            if (page == 1 && answer == "89") {
                result = result?.plus(1)
            } else if (page == 2 && validator == "v") {
                result = result?.plus(1)
            } else if (page == 3 && answer == "80") {
                result = result?.plus(1)
            } else if (page == 4 && answer == "108") {
                result = result?.plus(1)
            } else if (page == 5 && answer == "2") {
                result = result?.plus(1)
            } else if (page == 6 && validator == "d") {
                result = result?.plus(1)
            } else if (page == 7 && answer == "4800") {
                result = result?.plus(1)
            } else if (page == 8 && validator == "b") {
                result = result?.plus(1)
            } else if (page == 9 && answer== "116") {
                result = result?.plus(1)
            } else if (page == 10 && validator == "c") {
                result = result?.plus(1)
                showPopupFinishQuiz()
            }
            else if(page == 10 && validator!= "c"){
                showPopupFinishQuiz()
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
        if(option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.VISIBLE
        }
        binding?.textView39?.text =
            "Berapakah panjang sisi persegi pada bagian candi jika dengan luas 7.921 cm² ?\n"

        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let { Glide.with(this).load(R.drawable.stupa6_1).into(it) }


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
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: 89 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui luas = 7.921 cm²\n" +
                    "Ditanyakan panjang sisi ?\n" +
                    "L = s x s\n" +
                    "s = √L\n" +
                    "s = √7.921\n" +
                    "s = 89 cm\n" +
                    "Jadi, panjang sisi persegi tersebut adalah 89 cm\n"
        }

    }

    private fun loadProperty2() {
        if(option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
            binding?.pilgan?.visibility = View.VISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        binding?.textView39?.text = "Tina sedang membuat prakarya dari kertas karton berbentuk persegi dengan panjang sisi 70 cm. Pada kertas karton tersebut akan ditempelkan potongan kertas origami berukuran 5 cm x 5 cm. Jumlah potongan kertas origami yang dibutuhkan Tina sebanyak .... lembar.\n"

        binding?.textView38?.visibility = View.GONE

        binding?.view14?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view15?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.a?.text = "a. 190"
        binding?.b?.text = "b. 195"
        binding?.c?.text = "c. 196"
        binding?.d?.text = "d. 198"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: C. 196"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui panjang sisi kertas karton = 70 cm, ukuran kertas origami 5 cm x 5 cm\n" +
                    "Ditanyakan jumlah kertas origami yang dibutuhkan ?\n" +
                    "Untuk mengetahui jumlah kertas origami yang dibutuhkan, kita harus menghitung luas kertas karton dan luas kertas origami.\n" +
                    "L = s x s\n" +
                    "L kertas karton = 70 cm x 70 cm = 4.900 cm2\n" +
                    "L kertas origami = 5 cm x 5 cm = 25 cm2\n" +
                    "Kertas origami yang dibutuhkan = L kertas karton : L kertas origami\n" +
                    "Kertas origami yang dibutuhkan = 4.900 cm2 : 25 cm2\n" +
                    "Kertas origami yang dibutuhkan = 196 lembar\n"
        }
    }

    private fun loadProperty3() {
        if(option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.VISIBLE
        }
        binding?.textView39?.text = "Berapakah keliling segitiga siku-siku dengan luas 240 cm² dan tinggi 16 cm?\n"
        binding?.textView38?.visibility = View.GONE


        binding?.view15?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view16?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: 80 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui luas segitiga = 240 cm² , tinggi 16 cm\n" +
                    "Ditanyakan keliling?\n" +
                    "Untuk menghitung keliling segitiga, kita harus mencari panjang alasnya dan sisi miring.\n" +
                    "L = 1/2 x a x t\n" +
                    "240 = 1/2 x a x 16\n" +
                    "a = 240 x 2 : 16\n" +
                    "a = 30 cm\n" +
                    "Panjang alas = 30 cm\n" +
                    "Sekarang kita harus mencari sisi miring dengan cara menggunakan Rumus Pythagoras\n" +
                    "c2 = a2 + b2\n" +
                    "c2 = 162 + 302\n" +
                    "c2 = 1.156\n" +
                    "c = √1.156\n" +
                    "c = 34\n" +
                    "Panjang sisi miring = 34 cm\n" +
                    "K segitiga siku-siku = tinggi + alas + sisi miring\n" +
                    "K segitiga siku-siku = 16 cm + 30 cm + 34 cm\n" +
                    "K segitiga siku-siku = 80 cm\n" +
                    "Jadi keliling segitiga siku-siku adalah 80 cm \n"
        }

    }

    private fun loadProperty4() {
        if(option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.VISIBLE
        }

        binding?.textView39?.text =
            "Sebuah tambak udang berbentuk segitiga sama sisi dengan panjang sisinya 12 meter. Tambak tersebut akan dikelilingi pagar kawat 3 tingkat. Berapa meter kawat yang dibutuhkan?\n"
        binding?.textView38?.visibility = View.GONE

        binding?.view16?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view21?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 108 meter"
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui panjang sisi = 12 m\n" +
                    "Pagar kawat = 3 tingkat\n" +
                    "Ditanyakan panjang kawat yang diperlukan?\n" +
                    "Untuk mengetahui panjang kawat yang diperlukan, kita harus menghitung keliling segitiga\n" +
                    "K = 3 x sisi\n" +
                    "K = 3 x 12 m\n" +
                    "K = 36 meter\n" +
                    "Kawat yang diperlukan = K x 3\n" +
                    "Kawat yang diperlukan = 36 m x 3 = 108 meter\n" +
                    "Jadi, kawat yang dibutuhkan adalah 108 meter\n"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadProperty5() {
        if(option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true

            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.VISIBLE
        }

        binding?.textView39?.text =
            "Sawah kakek berbentuk persegi panjang dengan panjang 200 m dan lebar 100 m. Berapa hektar luas sawah kakek?\n"

        binding?.textView38?.visibility = View.GONE

        binding?.view21?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view22?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"


        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: 2 Hektar"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan :\n" +
                        "Diketahui panjang 200 m, lebar 100 m\n" +
                        "Ditanyakan luas?\n" +
                        "L = p x l\n" +
                        "L = 200 x 100\n" +
                        "L = 20.000 m2\n" +
                        "1 hektar = 10.000 m2\n" +
                        "Luas sawah kakek = 20.000 m2 : 10.000 m2/hektar\n" +
                        "Luas sawah kakek = 2 hektar\n" +
                        "Jadi, luas sawah kakaek adalah 2 hektar \n"
        }
    }

    private fun loadProperty6() {
        if(option == "pembahasan") {
            isPicked = true
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true

            binding?.pilgan?.visibility = View.VISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }

        binding?.textView38?.visibility = View.GONE
        binding?.textView39?.text =
            "Taman bunga berbentuk segitiga dengan ukuran 135 cm, 75 cm, dan 90 cm. Jika taman tersebut dikelilingi pagar kawat 5 tingkat, maka kawat yang diperlukan adalah .... meter.\n"


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

        binding?.a?.text = "a. 9"
        binding?.b?.text = "b. 10"
        binding?.c?.text = "c. 12"
        binding?.d?.text = "d. 15"
        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: D. 15"
            binding?.textView40?.text = "Diketahui ukuran segitiga = 135 cm, 75 cm, dan 90 cm\n" +
                    "Pagar kawat = 5 tingkat\n" +
                    "Ditanyakan kawat yang diperlukan?\n" +
                    "Untuk mengetahui panjang kawat yang diperlukan, kita harus menghitung keliling segitiga\n" +
                    "K = sisi a + sisi b + sisi c\n" +
                    "K = 135 cm + 75 cm + 90 cm\n" +
                    "K = 300 cm = 3 meter\n" +
                    "Kawat yang diperlukan = K x 5\n" +
                    "Kawat yang diperlukan = 3 m x 5 = 15 m\n"
        }
    }

    private fun loadProperty7() {
        if(option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
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

        binding?.textView39?.text = "Sebuah tambak berbentuk persegi dengan panjang sisi 75 meter. Tambak tersebut akan dikelilingi batako. Tiap meter membutuhkan 16 batako. Berapa batako yang dibutuhkan untuk mengelilingi tambak tersebut ?\n"

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
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 4800"
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui panjang sisi lantai = 75 m, batako yang dibutuhkan per meter = 16\n" +
                    "Ditanyakan jumlah batako yang dibutuhkan ?\n" +
                    "Untuk mengetahui jumlah batako yang dibutuhkan, kita harus menghitung keliling tambak.\n" +
                    "K = 4 x s\n" +
                    "K = 4 x 75 m = 300 m\n" +
                    "Batako yang dibutuhkan = keliling tambak x jumlah batako/meter\n" +
                    "Batako yang dibutuhkan = 300 m x 16/m\n" +
                    "Batako yang dibutuhkan = 4.800\n" +
                    "Jadi, batako yang dibutuhkan untuk mengelilingi tambak sebanyak 4.800 \n"
        }
    }

    private fun loadProperty8() {
        if(option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true

            binding?.pilgan?.visibility = View.VISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
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
        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let {
            Glide
                .with(this)
                .load(R.drawable.stupa6_8)
                .into(it)
        }

        binding?.view24?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view25?.background?.setTint(resources.getColor(R.color.green))

        binding?.a?.text = "a. 62"
        binding?.b?.text = "b. 64"
        binding?.c?.text = "c. 72"
        binding?.d?.text = "d. 80"
        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: B. 64 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui panjang bangun I dan II = 24 cm\n" +
                        "Bangun I, panjang = 14 cm, lebar = 8 cm.\n" +
                        "Bangun II, panjang = 24 cm - 14 cm = 10 cm, lebar = 8 cm - 3 cm = 5 cm\n" +
                        "Ditanyakan keliling? K = jumlah semua sisi K = 14 cm + 10 cm + 5 cm + 10 cm + 3 cm + 14 cm + 8 cm\n" +
                        "K = 64 cm\n"
        }
    }

    private fun loadProperty9() {
        if(option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
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

        binding?.textView39?.text = "Berapakah keliling persegi dengan luas 841 cm2?\n"

        binding?.textView38?.visibility = View.GONE

        binding?.view25?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view26?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: 116 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Diketahui luas = 841 cm2\n" +
                        "Ditanyakan keliling?\n" +
                        "Untuk menghitung keliling, kita harus mengetahui panjang sisi.\n" +
                        "L = s x s\n" +
                        "841 = s x s\n" +
                        "s = √841\n" +
                        "s = 29 cm\n" +
                        "K = 4 x s\n" +
                        "K = 4 x 29 cm = 116 cm\n" +
                        "Jadi, keliling persegi tersebut adalah 116 cm \n"
        }
    }

    private fun loadProperty10() {
        if(option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true

            binding?.pilgan?.visibility = View.VISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
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

        binding?.textView39?.text = "Kebun paman berbentuk persegi dengan luas 3.025 m2. Keliling kebun paman adalah .... m\n"

        binding?.view26?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view27?.background?.setTint(resources.getColor(R.color.green))

        binding?.textView38?.visibility = View.GONE

        binding?.a?.text = "a. 200"
        binding?.b?.text = "b. 210"
        binding?.c?.text = "c. 220"
        binding?.d?.text = "d. 230"
        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.gray_et))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: C. 220"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui luas = 3.025 m2\n" +
                        "Ditanyakan keliling?\n" +
                        "Untuk menghitung keliling, kita harus mengetahui panjang sisi.\n" +
                        "L = s x s\n" +
                        "3.025 = s x s\n" +
                        "s = √3.025\n" +
                        "s = 55 m\n" +
                        "K = 4 x s\n" +
                        "K = 4 x 55 cm = 220 m\n"
        }
    }


    private fun pickedChoice() {
        binding?.a?.setOnClickListener {
            validator = "a"
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.darker_green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.b?.setOnClickListener {
            validator = "b"
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.darker_green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.c?.setOnClickListener {
            validator = "c"
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.darker_green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.d?.setOnClickListener {
            validator = "d"
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
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        binding.view21.setOnClickListener {
            val intent = Intent(this, StupaActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.pembahasan.setOnClickListener {
            dialog.dismiss()
            page = 1
            option = "pembahasan"
            validator = ""
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