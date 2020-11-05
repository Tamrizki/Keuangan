package tam.pa.keuangan.activity.daftarPage

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class DaftarPresenter(val iViewDaftar: IViewDaftar) {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    fun onGetSignUp(email: String, pass: String){
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(OnCompleteListener {
                if (it.isSuccessful) {
                    val user: FirebaseUser? = auth.getCurrentUser()
                    iViewDaftar.onGetSignUp("Success")
                }else{
                    iViewDaftar.onFailur(it.exception.toString())
                }
            })
    }
}