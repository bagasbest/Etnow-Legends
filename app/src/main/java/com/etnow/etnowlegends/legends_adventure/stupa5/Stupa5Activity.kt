package com.etnow.etnowlegends.legends_adventure.stupa5

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
import com.etnow.etnowlegends.databinding.ActivityStupa5Binding
import com.etnow.etnowlegends.databinding.PopupQuizResultBinding
import com.etnow.etnowlegends.legends_adventure.StupaActivity
import com.etnow.etnowlegends.materi.persegi.PersegiActivity
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

class Stupa5Activity : AppCompatActivity() {

    private var binding: ActivityStupa5Binding? = null
    private var page: Int = 1
    private var result: Int? = 0
    private var validator: String? = null
    private var isPicked: Boolean? = false
    private var time: Long? = 0L
    private var option: String? = null
    private var answer: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStupa5Binding.inflate(layoutInflater)
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

            if (page == 1 && validator == "a") {
                result = result?.plus(1)
            } else if (page == 2 && validator == "b") {
                result = result?.plus(1)
            } else if (page == 3 && validator == "c") {
                result = result?.plus(1)
            } else if (page == 4 && validator == "c") {
                result = result?.plus(1)
            } else if (page == 5 && validator == "a") {
                result = result?.plus(1)
            } else if (page == 6 && answer == "12") {
                result = result?.plus(1)
            } else if (page == 7 && answer == "56") {
                result = result?.plus(1)
            } else if (page == 8 && answer == "192") {
                result = result?.plus(1)
            } else if (page == 9 && answer== "140") {
                result = result?.plus(1)
            } else if (page == 10 && answer == "3") {
                result = result?.plus(1)
                showPopupFinishQuiz()
            }
            else if(page == 10 && answer != "3"){
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
            "Diketahui segitiga ABC siku-siku di B. Jika luas segitiga ABC 105 cm2 dan panjang AB = 14 cm,  maka panjang BC adalah .... cm.\n"

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

        binding?.a?.text = "a. 15"
        binding?.b?.text = "b. 16"
        binding?.c?.text = "c. 18"
        binding?.d?.text = "d. 20"

        binding?.view17?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))


        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: A. 15"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui luas = 105 cm2 , AB = 14 cm adalah tinggi segitiga\n" +
                    "Ditanyakan panjang BC (alas) ?\n" +
                    "L = ½ x a x t\n" +
                    "105 = ½ x a x 14\n" +
                    "a = 105 x 2 : 14\n" +
                    "a = 15 cm\n"
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
        binding?.textView39?.text = "Keliling suatu bangun persegi 60 cm. Luas bangun tersebut adalah .... cm2\n"

        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let {
            Glide
                .with(this)
                .load(R.drawable.stupa5_2)
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
        binding?.textView39?.text = "Selembar kertas berbentuk segitiga sama sisi memiliki keliling yang panjangnya 114 cm, maka panjang sisi-sisinya adalah .... cm\n"
        binding?.textView38?.visibility = View.GONE


        binding?.view15?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view16?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.a?.text = "a. 34"
        binding?.b?.text = "b. 36"
        binding?.c?.text = "c. 38"
        binding?.d?.text = "d. 40"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: C. 38"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui keliling segitiga sama sisi = 114 cm\n" +
                    "Ditanyakan panjang sisi?\n" +
                    "K = 3 x panjang sisi\n" +
                    "114 = 3 x panjang sisi\n" +
                    "Panjang sisi = 114 : 3\n" +
                    "Panjang sisi = 38 cm\n"
        }

    }

    private fun loadProperty4() {
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
            "Teras rumah Pak Rudi luasnya 18 m2 akan dipasang ubin berukuran 25 cm x 20 cm. Banyaknya ubin yang dibutuhkan adalah .... ubin.\n"
        binding?.textView38?.visibility = View.GONE

        binding?.view16?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view21?.background?.setTint(resources.getColor(R.color.green))

        binding?.a?.text = "a. 300"
        binding?.b?.text = "b. 350"
        binding?.c?.text = "c. 360"
        binding?.d?.text = "d. 450"
        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: C. 360 ubin"
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui luas = 18 m2 , ukuran ubin = 25 cm x 20 cm\n" +
                    "Ditanyakan banyaknya ubin yang dibutuhkan?\n" +
                    "Luas ubin = 25 x 20 = 500 cm2\n" +
                    "Luas teras = 18 m2 = 180.000 cm2\n" +
                    "Ubin yang dibutuhkan = Luas teras : luas ubin\n" +
                    "Ubin yang dibutuhkan = 180.000 cm2 : 500 cm2/ubin\n" +
                    "Ubin yang dibutuhkan = 360 ubin\n"
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
            "Sebuah papan reklame berbentuk segitiga, memiliki panjang alas 65 cm dan luasnya 1.625 cm2, maka tingginya adalah .... cm\n"

        binding?.textView38?.visibility = View.GONE

        binding?.view21?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view22?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.a?.text = "a. 50"
        binding?.b?.text = "b. 52"
        binding?.c?.text = "c. 54"
        binding?.d?.text = "d. 65"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: A. 50"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui alas = 65 cm, luas = 1.625 cm2\n" +
                        "Ditanyakan tingginya?\n" +
                        "L = 1/2 x a x t\n" +
                        "1.625 = 1/2 x 65 x t\n" +
                        "t = 1.625 x 2 : 65\n" +
                        "t = 50 cm\n"
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

            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.etAnswer?.visibility = View.VISIBLE
        }

        binding?.textView38?.visibility = View.GONE
        binding?.textView39?.text =
            "Tinggi sebuah segitiga 11 cm. Jika luasnya 66 cm2, maka panjang alasnya adalah .... cm\n"


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
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView35?.text = "Jawaban: 12 cm"
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui tinggi = 31 cm. Luas = 66 cm2\n" +
                    "Ditanyakan alas?\n" +
                    "L = 1/2 x a x t\n" +
                    "66 = 1/2 x a x 11\n" +
                    "t = 66 x 2 : 11\n" +
                    "t = 12 cm\n"
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

        binding?.textView39?.text = "Keliling suatu persegi panjang adalah 236 cm. Jika panjangnya 62 cm, berapakah lebar persegi panjang tersebut?"

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
            binding?.textView35?.text = "Jawaban: 56 cm"
            binding?.textView40?.text = "Pembahasan\n" +
                    "Diketahui keliling 236, panjang = 62 cm\n" +
                    "\n" +
                    "Ditanyakan lebar?\n" +
                    "K = 2 x (p + l)\n" +
                    "236 = 2 x (62 + l)\n" +
                    "l = 236 : 2 – 62\n" +
                    "l = 56 cm\n" +
                    "Jadi, lebar persegi panjang tersebut adalah 56 cm \n"
        }
    }

    private fun loadProperty8() {
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

        binding?.textView39?.text = " Perkarangan candi berbentuk persegi dengan panjang sisinya 16 meter. Perkarangan candi tersebut akan dikelilingi pagar kawat 3 tingkat. Berapa meter kawat yang perlukan?\n"
        binding?.textView38?.visibility = View.GONE

        binding?.view24?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view25?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: 192 Meter"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui panjang sisi = 16 m, pagar kawat = 3 tingkat\n" +
                        "Ditanyakan kawat yang diperlukan?\n" +
                        "Untuk mengetahui panjang kawat yang diperlukan, kita harus menghitung keliling perkarangan candi\n" +
                        "K = 4 x s\n" +
                        "K = 4 x 16\n" +
                        "K = 64 meter\n" +
                        "Kawat yang diperlukan = keliling tambak x 3\n" +
                        "Kawat yang diperlukan = 64 m x 3\n" +
                        "Kawat yang diperlukan = 192 m\n" +
                        "Jadi, kawat yang diperlukan untuk pagar kawat sepanjang 192 meter \n"
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

        binding?.textView39?.text = "Kebun Pak Danu berbentuk persegi panjang dengan panjang 42 m dan lebar 28 m. Hitunglah keliling dan luas kebun Pak Danu !\n"


        binding?.textView38?.visibility = View.GONE

        binding?.view25?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view26?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: 140 m"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui panjang = 42 m, lebar = 28 m\n" +
                        "Ditanyakan keliling?\n" +
                        "K = 2 x (p + l)\n" +
                        "K = 2 x (42 + 28)\n" +
                        "K = 140 m\n" +
                        "Jadi, keliling kebun Pak Danu adalah 140 m \n" +
                        "Jawaban : 140 m\n"
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

        binding?.textView39?.text = "Sebuah lapangan berbentuk persegi dengan panjang sisi 250 m. Andi berlari mengelilingi lapangan tersebut 3 kali. Berapa km jarak yang ditempuh Andi ?"

        binding?.view26?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view27?.background?.setTint(resources.getColor(R.color.green))

        binding?.textView38?.visibility = View.GONE

        binding?.button6?.text = "K.D. 4.2"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.gray_et))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: 3 km"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui panjang sisi = 250 m, banyaknya putaran = 3 kali\n" +
                        "Ditanyakan jarak tempuh ?\n" +
                        "Untuk mengetahui jarak tempuh, kita harus menghitung keliling lapangan\n" +
                        "K = 4 x s\n" +
                        "K = 4 x 250 m = 1.000 m\n" +
                        "Jarak tempuh = keliling lapangan x banyaknya putaran\n" +
                        "Jarak tempuh = 1.000 m x 3 = 3.000 m = 3 km\n" +
                        "Jadi, jarak yang ditempuh Andi adalah 3 km\n" +
                        "Jawaban : 3 km\n"
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