package tam.pa.keuangan.activity.loginPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import tam.pa.keuangan.R
import tam.pa.keuangan.activity.daftarPage.DaftarActivity
import tam.pa.keuangan.activity.mainPage.MainActivity


class LoginActivity : AppCompatActivity(), IViewLogin, View.OnClickListener {
    lateinit var etEmail: EditText
    lateinit var etPass: EditText
    lateinit var btnMasuk: Button
    lateinit var tvDaftar: TextView
    val presenter: LoginPresenter = LoginPresenter()

    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        if (auth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
        }
        btnMasuk.setOnClickListener(this)
        tvDaftar.setOnClickListener(this)
    }

    override fun init() {
        etEmail = findViewById(R.id.etEmail)
        etPass = findViewById(R.id.etPass)
        btnMasuk = findViewById(R.id.btnMasuk)
        tvDaftar = findViewById(R.id.tvDaftar)
    }

    override fun onGetLogin(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onGetFailur(msg: String) {
        Toast.makeText(this, "Akun yang anda masukan belum terdaftar.", Toast.LENGTH_LONG).show()
    }

    override fun onClick(view: View?) {
        if (view == btnMasuk){
            if (etEmail.text.toString().isNullOrEmpty()){
                etEmail.error = "form tidak boleh kosong!"
                etEmail.requestFocus()
            }else if (etPass.text.toString().isNullOrEmpty()){
                etPass.error = "form tidak boleh kosong!"
                etPass.requestFocus()
            }else{
                presenter.onGetLogin(etEmail.text.toString(), etPass.text.toString())
            }
        }else if (view == tvDaftar){
            startActivity(Intent(this, DaftarActivity::class.java))
        }
    }

}