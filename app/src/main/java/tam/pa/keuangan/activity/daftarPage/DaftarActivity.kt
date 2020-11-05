package tam.pa.keuangan.activity.daftarPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import tam.pa.keuangan.R
import tam.pa.keuangan.activity.mainPage.MainActivity

class DaftarActivity : AppCompatActivity(), IViewDaftar, View.OnClickListener {
    lateinit var etEmail: EditText
    lateinit var etPass: EditText
    lateinit var btnDaftar: Button
    lateinit var iViewDaftar: IViewDaftar
    lateinit var presenter: DaftarPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)
        init()
        iViewDaftar = this
        presenter = DaftarPresenter(iViewDaftar)
        btnDaftar.setOnClickListener(this)
    }

    override fun onGetSignUp(msg: String) {
        Toast.makeText(this, "Akun berhasil didaftarkan!", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onFailur(msg: String) {
        Toast.makeText(this, "Registrasi tidak berhasil!", Toast.LENGTH_LONG).show()
    }

    override fun init() {
        etEmail = findViewById(R.id.etEmail)
        etPass = findViewById(R.id.etPass)
        btnDaftar = findViewById(R.id.btnDaftar)
    }

    override fun onClick(view: View?) {
        if (view == btnDaftar){
            if (etEmail.text.toString().isNullOrEmpty()){
                etEmail.error = "form tidak boleh kosong!"
                etEmail.requestFocus()
            }else if (etPass.text.toString().isNullOrEmpty()){
                etPass.error = "form tidak boleh kosong!"
                etPass.requestFocus()
            }else{
                presenter.onGetSignUp(etEmail.text.toString(), etPass.text.toString())
            }
        }
    }
}