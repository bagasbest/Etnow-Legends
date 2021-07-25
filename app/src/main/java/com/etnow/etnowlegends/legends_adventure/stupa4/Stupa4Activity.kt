package com.etnow.etnowlegends.legends_adventure.stupa4

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import com.etnow.etnowlegends.databinding.ActivityStupa4Binding
import com.etnow.etnowlegends.databinding.PopupQuizResultBinding
import com.etnow.etnowlegends.legends_adventure.StupaActivity
import com.etnow.etnowlegends.materi.persegi.PersegiActivity
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

class Stupa4Activity : AppCompatActivity() {

    private var binding: ActivityStupa4Binding? = null
    private var page: Int = 1
    private var result: Int? = 0
    private var validator: String? = null
    private var isPicked: Boolean? = false
    private var time: Long? = 0L
    private var option: String? = null
    private var answer: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStupa4Binding.inflate(layoutInflater)
        setContentView(binding?.root)
        option = intent.getStringExtra(PersegiActivity.EXTRA_OPT)

        selectedOption()

        selectedPage()




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

            if (page == 1 && validator == "b") {
                result = result?.plus(1)
            } else if (page == 2 && validator == "b") {
                result = result?.plus(1)
            } else if (page == 3 && validator == "a") {
                result = result?.plus(1)
            } else if (page == 4 && answer == "12") {
                result = result?.plus(1)
            } else if (page == 5 && validator == "a") {
                result = result?.plus(1)
            } else if (page == 6 && validator == "d") {
                result = result?.plus(1)
            } else if (page == 7 && answer == "60") {
                result = result?.plus(1)
            } else if (page == 8 && validator == "c") {
                result = result?.plus(1)
            } else if (page == 9 && validator== "b") {
                result = result?.plus(1)
            } else if (page == 10 && answer == "5") {
                result = result?.plus(1)
                showPopupFinishQuiz()
            }
            else if(page == 10 && answer != "5"){
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
            binding?.pilgan?.visibility = View.VISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        binding?.etAnswer?.visibility = View.INVISIBLE
        binding?.textView39?.text =
            "Selembar kertas karton berbentuk persegi dengan panjang 56 cm. Luas dan keliling kertas karton tersebut adalah ....\n"

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

        binding?.a?.text = "a. 3.126 cm2 dan 214 cm"
        binding?.b?.text = "b. 3.136 cm2 dan 224 cm"
        binding?.c?.text = "c. 3.146 cm2 dan 234 cm"
        binding?.d?.text = "d. 3.156 cm2 dan 244 cm"

        binding?.view17?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))


        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: B. 3.136 cm2 dan 224 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui panjang sisi = 56 cm\n" +
                    "Ditanyakan luas dan keliling?\n" +
                    "L = s x s\n" +
                    "L = 56 cm x 56 cm = 3.136 cm2\n" +
                    "K = 4 x s\n" +
                    "K = 4 x 56 cm = 224 cm\n"
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
        binding?.textView39?.text = "Sepetak sawah berbentuk persegi dengan panjang sisi 50 m. Luas sawah tersebut adalah .... cm2"

        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let {
            Glide
                .with(this)
                .load(R.drawable.stupa4_2)
                .into(it)
        }

        binding?.view14?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view15?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.a?.text = "a. 220"
        binding?.b?.text = "b. 225"
        binding?.c?.text = "c. 230"
        binding?.d?.text = "d. 235"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: B. 225"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui keliling = 60 cm\n" +
                    "Ditanyakan luas?\n" +
                    "\n" +
                    "Untuk menghitung luas, kita harus mengetahui panjang sisi.\n" +
                    "K = 4 x s\n" +
                    "s = K : 4\n" +
                    "s = 60 : 4 = 15 cm\n" +
                    "L = s x s\n" +
                    "L = 15 x 15\n" +
                    "L = 225 cm2\n"
        }
    }

    private fun loadProperty3() {
        if(option == "pembahasan") {
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        else {
            isPicked = true
            binding?.pilgan?.visibility = View.VISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }
        binding?.textView39?.text = "Keliling sebuah kebun 160 m. Jika panjang kebun 50 m, maka lebar kebun tersebut adalah ....\n"
        binding?.textView38?.visibility = View.GONE


        binding?.view15?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view16?.background?.setTint(resources.getColor(R.color.green))

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
            binding?.textView35?.text = "Jawaban: A. 30"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui keliling = 160 m, panjang = 50 m\n" +
                    "Ditanyakan lebar?\n" +
                    "K = 2 x (p + l)\n" +
                    "160 = 2 x (50 + l)\n" +
                    "l = 160 : 2 - 50\n" +
                    "l = 30 m\n"
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
            "Udin mempunyai kebun di belakang rumahnya yang berbentuk persegi panjang. Orangtua Udin akan membuat pagar di sekeliling kebun tersebut. Kebun milik Udin berukuran panjang 4 meter dan lebar 2 meter. Berapakah panjang pagar yang dibutuhkan?\n"
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
            binding?.textView35?.text = "Jawaban: 12 meter"
            binding?.textView40?.text = "Penyelesaian :\n" +
                    "Panjang pagar yang dibutuhkan = 2 x ( p + l)\n" +
                    "= 2 x (4 + 2 )\n" +
                    "= 2 x 6\n" +
                    "= 12 meter\n"
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

            binding?.pilgan?.visibility = View.VISIBLE
            binding?.etAnswer?.visibility = View.INVISIBLE
        }

        binding?.textView39?.text =
            "Sebuah kolam ikan berbentuk segitiga sama sisi dengan panjang sisinya 6 m. Jika sekeliling kolam dipagari kawat 3 tingkat, maka panjang kawat yang diperlukan adalah .... meter\n"

        binding?.textView38?.visibility = View.GONE

        binding?.view21?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view22?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.a?.text = "a. 54"
        binding?.b?.text = "b. 55"
        binding?.c?.text = "c. 56"
        binding?.d?.text = "d. 60"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: A. 54"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui panjang sisi segitiga = 6 m\n" +
                        "Pagar kawat = 3 tingkat\n" +
                        "Ditanyakan panjang kawat yang diperlukan?\n" +
                        "Untuk mengetahui panjang kawat yang diperlukan, kita harus menghitung keliling segitiga\n" +
                        "K = 3 x sisi\n" +
                        "K = 3 x 6 m\n" +
                        "K = 18 meter\n" +
                        "Kawat yang diperlukan = K x 3\n" +
                        "Kawat yang diperlukan = 18 m x 3 = 54 meter\n"
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

        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let { Glide.with(this).load(R.drawable.stupa4_6).into(it) }

        binding?.textView39?.text =
            "Luas gabungan tembok candi  pada gambar di atas adalah .... cm2\n"


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

        binding?.a?.text = "a. 100"
        binding?.b?.text = "b. 110"
        binding?.c?.text = "c. 115"
        binding?.d?.text = "d. 120"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: D. 120 cm2"
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui bangun I, panjang = 14 cm , lebar = 6 cm\n" +
                    "L = p x l\n" +
                    "L = 14 x 6\n" +
                    "L = 84 cm2\n" +
                    "Diketahui bangun II, panjang = 9 cm, lebar = 10 cm – 6 cm\n" +
                    "L = p x l\n" +
                    "L = 9 x 4\n" +
                    "L = 36 cm2\n" +
                    "Luas bangun = L. bangun I + L.bangun II\n" +
                    "Luas bangun = 84 cm2 + 36 cm2\n" +
                    "Luas bangun = 120 cm2\n"
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

        binding?.textView39?.text = "Di sekeliling taman berbentuk persegi akan ditanami pohon pinus dengan jarak antar pohon 4 m. Jika panjang sisi taman adalah 60 m, berapakah pohon pinus yang dibutuhkan? \n"

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
            binding?.textView35?.text = "Jawaban: 60 Pohon"
            binding?.textView40?.text = "Penyelesaian:\n" +
                    "Diketahui :\n" +
                    "Panjang sisi taman = 60 m\n" +
                    "Jarak antar tanaman = 4 m\n" +
                    "Penyelesaiannya : \n" +
                    "Keliling persegi \t= 4 x sisi\n" +
                    "= 4 x 60 \n" +
                    "= 240 m\n" +
                    "Untuk menanam pohon pinus diberi jarak 4 meter, maka \n" +
                    "Keliling kebun : jarak tanam = 240 : 4 \n" +
                    "= 60 Jadi pohon pinus yang dibutuhkan untuk ditanami sebanyak 60 pohon.\n"
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

        binding?.textView39?.text = "Luas suatu persegi panjang adalah 128 cm2 . Jika panjangnya 16 cm, maka lebarnya adalah .... cm\n"
        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let { Glide.with(this).load(R.drawable.stupa4_8).into(it) }

        binding?.view24?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view25?.background?.setTint(resources.getColor(R.color.green))

        binding?.a?.text = "a. 6"
        binding?.b?.text = "b. 7"
        binding?.c?.text = "c. 8"
        binding?.d?.text = "d. 9"
        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: C. 8"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui luas = 120 cm2 , panjang = 16 cm\n" +
                        "Diatanyakan lebar?\n" +
                        "L = p x l\n" +
                        "128 = 16 x l\n" +
                        "l = 128 : 16\n" +
                        "l = 8\n"
        }
    }

    private fun loadProperty9() {
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

        binding?.textView39?.text = "Diketahui 1/2 x p x l = 120 cm2. Jika l = 20 cm, maka nilai p adalah .... cm\n"


        binding?.textView38?.visibility = View.GONE

        binding?.view25?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view26?.background?.setTint(resources.getColor(R.color.green))


        binding?.a?.text = "a. 10"
        binding?.b?.text = "b. 12"
        binding?.c?.text = "c. 14"
        binding?.d?.text = "d. 16"
        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: B. 12"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui 1/2 x p x l = 120 cm2, l = 20 cm\n" +
                        "Ditanyakan p?\n" +
                        "1/2 x p x l = 120\n" +
                        "1/2 x p x 20 = 120\n" +
                        "1/2p x 20 = 120\n" +
                        "10p = 120\n" +
                        "p = 120 : 10\n" +
                        "p = 12 cm\n"
        }
    }

    private fun loadProperty10() {
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

        binding?.finish?.visibility = View.VISIBLE

        binding?.textView39?.text = "Diketahui keliling persegi panjang  yang dibentuk oleh 2  batang korek api adalah 20 cm. Tentukan panjang batang korek api!\n"

        binding?.view26?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view27?.background?.setTint(resources.getColor(R.color.green))

        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let {
            Glide
                .with(this)
                .load(R.drawable.stupa4_10)
                .into(it)
        }

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.gray_et))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: 5 cm"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan : \n" +
                        "panjang korek api = sisi persegi\n" +
                        "keliling persegi = 4 x sisi\n" +
                        "20 cm = 4 x sisi\n" +
                        "Sisi = 20 : 4 \n" +
                        "= 5 cm \n" +
                        "Jadi panjang batang korek api yaitu 5 cm\n"
        }
    }


    private fun pickedChoice() {
        binding?.a?.setOnClickListener {
            validator = "a"
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.yellow))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.b?.setOnClickListener {
            validator = "b"
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.yellow))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.c?.setOnClickListener {
            validator = "c"
            isPicked = true
            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.yellow))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
        }

        binding?.d?.setOnClickListener {
            validator = "d"
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

        if (result!! > 5) {
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
        binding.wrong.text = "Jawaban salah: ${10 - result!!}"

        binding.view19.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        binding.view21.setOnClickListener {
            val intent = Intent(this, StupaActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        countDownTimer.cancel()
    }

    companion object {
        const val EXTRA_OPT = "opt"
    }
}