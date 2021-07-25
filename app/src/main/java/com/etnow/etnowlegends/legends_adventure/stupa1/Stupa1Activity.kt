package com.etnow.etnowlegends.legends_adventure.stupa1

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
import com.etnow.etnowlegends.databinding.ActivityStupa1Binding
import com.etnow.etnowlegends.databinding.PopupQuizResultBinding
import com.etnow.etnowlegends.legends_adventure.StupaActivity
import com.etnow.etnowlegends.materi.persegi.PersegiActivity
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

class Stupa1Activity : AppCompatActivity() {

    private var binding: ActivityStupa1Binding? = null
    private var page: Int = 1
    private var result: Int? = 0
    private var validator: String? = null
    private var isPicked: Boolean? = false
    private var time: Long? = 0L
    private var option: String? = null
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStupa1Binding.inflate(layoutInflater)
        setContentView(binding?.root)

        option = intent.getStringExtra(PersegiActivity.EXTRA_OPT)

        prefs = getSharedPreferences(
            "com.etnow.etnowlegends", Context.MODE_PRIVATE
        )

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

            if (page == 1 && validator == "c") {
                result = result?.plus(1)
            } else if (page == 2 && validator == "b") {
                result = result?.plus(1)
            } else if (page == 3 && validator == "d") {
                result = result?.plus(1)
            } else if (page == 4 && validator == "c") {
                result = result?.plus(1)
            } else if (page == 5 && validator == "a") {
                result = result?.plus(1)
            } else if (page == 6 && validator == "a") {
                result = result?.plus(1)
            } else if (page == 7 && validator == "d") {
                result = result?.plus(1)
            } else if (page == 8 && validator == "b") {
                result = result?.plus(1)
            } else if (page == 9 && validator == "b") {
                result = result?.plus(1)
            } else if (page == 10 && validator == "d") {
                result = result?.plus(1)
                showPopupFinishQuiz()
            }
            else if(page == 10 && validator != "d") {
                showPopupFinishQuiz()
            }

            binding?.a?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.b?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.c?.setBackgroundColor(resources.getColor(R.color.green))
            binding?.d?.setBackgroundColor(resources.getColor(R.color.green))
            page += 1
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
        binding?.textView39?.text =
            "Dinding candi diatas yang terdapat garis merah berbentuk bangun ..."
        binding?.textView38?.let {
            Glide
                .with(this)
                .load(R.drawable.stupa1_1)
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

        binding?.button6?.text = "K.D. 3.2"

        binding?.a?.text = "a. Segitiga"
        binding?.b?.text = "b. Trapesium"
        binding?.c?.text = "c. Persegi"
        binding?.d?.text = "d. Belah Ketupat"

        binding?.view17?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))


        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: C. Persegi"
        }

    }

    private fun loadProperty2() {
        binding?.textView39?.text = "Banyaknya titik sudut pada bangun trapesium adalah ..."

        binding?.textView38?.visibility = View.GONE

        binding?.view14?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view15?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 3.2"

        binding?.a?.text = "a. 2"
        binding?.b?.text = "b. 4"
        binding?.c?.text = "c. 6"
        binding?.d?.text = "d. 3"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: B. 4"
        }
    }

    private fun loadProperty3() {
        binding?.textView39?.text = "Bangun di atas memiliki sisi sebanyak ..."
        binding?.textView38?.visibility = View.VISIBLE
        binding?.textView38?.let {
            Glide
                .with(this)
                .load(R.drawable.stupa1_3)
                .into(it)
        }

        binding?.view15?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view16?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 3.2"

        binding?.a?.text = "a. 3"
        binding?.b?.text = "b. 5"
        binding?.c?.text = "c. 2"
        binding?.d?.text = "d. 4"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: D. 4"
        }

    }

    private fun loadProperty4() {
        binding?.textView39?.text =
            "Pada potongan  gambar candi di atas terdapat bangun datar berupa ..."
        binding?.textView38?.let {
            Glide
                .with(this)
                .load(R.drawable.stupa1_4)
                .into(it)
        }

        binding?.view16?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view21?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 3.2"

        binding?.a?.text = "a. Trapesium"
        binding?.b?.text = "b. Jajar Genjang"
        binding?.c?.text = "c. Persegi Panjang"
        binding?.d?.text = "d. Persegi"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView40?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: C. Persegi panjang"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadProperty5() {
        binding?.textView39?.text =
            "Keliling sebuah kebun 160 m. Jika panjang kebun 50 m maka lebar kebun tersebut adalah ..."

        binding?.textView38?.visibility = View.GONE

        binding?.view21?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view22?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 3.2"

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
            binding?.textView40?.text =
                "Pembahasan\n" +
                        "Diketahui keliling = 160 m, panjang = 50 m\n" +
                        "Ditanyakan lebar?\n" +
                        "K = 2 x (p + l)\n" +
                        "160 = 2 x (50 + l)\n" +
                        "l = 160 : 2 - 50\n" +
                        "l = 30 m\n"
        }
    }

    private fun loadProperty6() {
        binding?.textView39?.text =
            "Pada potongan gambar candi di samping adalah bangun segi banyak ..."
        binding?.textView38?.let {
            Glide
                .with(this)
                .load(R.drawable.stupa1_4)
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

        binding?.view14?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view15?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view16?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view21?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view22?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view23?.background?.setTint(resources.getColor(R.color.green))


        binding?.button6?.text = "K.D. 3.2"

        binding?.a?.text = "a. Persegi"
        binding?.b?.text = "b. Segitiga"
        binding?.c?.text = "c. Trapesium"
        binding?.d?.text = "d. Jajar Genjang"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView40?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: A. Persegi"
        }
    }

    private fun loadProperty7() {
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

        binding?.textView39?.text = "Bagaimana sifat dari persegi panjang?"

        binding?.textView38?.visibility = View.GONE

        binding?.view24?.background?.setTint(resources.getColor(R.color.green))
        binding?.view25?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view26?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view27?.background?.setTint(resources.getColor(R.color.gray_et))

        binding?.button6?.text = "K.D. 3.2"

        binding?.a?.text = "a. Semua sisinya sama"
        binding?.b?.text = "b. Memiliki 3 sudut sama besar"
        binding?.c?.text = "c. Memiliki 3 sisi"
        binding?.d?.text = "d. Memiliki dua simetri lipat"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView40?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: D. Memilili 2 simetri lipat"
        }
    }

    private fun loadProperty8() {
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

        binding?.textView39?.text = "Segitiga yang ketiga sisinya sama panjang disebut segitiga ..."

        binding?.textView38?.visibility = View.GONE

        binding?.view24?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view25?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 3.2"

        binding?.a?.text = "a. Siku - siku"
        binding?.b?.text = "b. Sama Sissi"
        binding?.c?.text = "c. Sama Kaki"
        binding?.d?.text = "d. Sembarang"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: B. Sama sisi"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Pembahasan :Segitiga adalah bangun yang memiliki 3 sisi dan 3 sudut. Sedangkan segitiga yang ketiga sisinya sama panjang disebut segitiga sama sisi."
        }
    }

    private fun loadProperty9() {
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

        binding?.textView39?.text = "Pada segitiga sama sisi, besar setiap sudutnya adalah .... cm"

        binding?.textView38?.visibility = View.GONE

        binding?.view25?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view26?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 3.2"

        binding?.a?.text = "a. 50 derajat"
        binding?.b?.text = "b. 60 derajat"
        binding?.c?.text = "c. 70 derajat"
        binding?.d?.text = "d. 80 derajat"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.green))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: B. 60 derajat"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Bangun segitiga memiliki jumlah sudut yang besarnya 180°. Sedangkan segitiga sama sisi, ketiga sudutnya sama besar yang masing-masing besarnya 60°."
        }
    }

    private fun loadProperty10() {
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

        binding?.textView39?.text = "Rumus luas dan keliling persegi panjang adalah ..."

        binding?.textView38?.visibility = View.GONE

        binding?.view26?.background?.setTint(resources.getColor(R.color.gray_et))
        binding?.view27?.background?.setTint(resources.getColor(R.color.green))

        binding?.button6?.text = "K.D. 3.2"

        binding?.a?.text = "a. L = s x s dan K = 4 x s"
        binding?.b?.text = "b. L = p x l dan K = 2 x p x l"
        binding?.c?.text = "c. L = p + l dan K = 2 x (p + l)"
        binding?.d?.text = "d. L = p x l dan K = 2 x (p + l)"

        binding?.view17?.background?.setTint(resources.getColor(R.color.green))
        binding?.view18?.background?.setTint(resources.getColor(R.color.gray_et))

        if (option == "pembahasan") {
            binding?.textView35?.visibility = View.VISIBLE
            binding?.pilgan?.visibility = View.INVISIBLE
            binding?.textView35?.text = "Jawaban: D. L = p x l dan K = 2 x (p + l)"
            binding?.textView40?.visibility = View.VISIBLE
            binding?.textView40?.text =
                "Rumus luas persegi panjang adalah panjang x lebar, dan rumus keliling persegi panjang adalah 2 x (panjang + lebar)."
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
            prefs.edit().putBoolean("stupa2", true).apply()

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