package tam.pa.keuangan.activity.loginPage

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser




class LoginPresenter() {
    val RC_SIGN_IN: Int = 1
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun onGetLogin(email: String, pass: String){
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(OnCompleteListener {
                if (it.isSuccessful) {
                    val user: FirebaseUser? = auth.getCurrentUser()
                    Log.d("LODINN", "createUserWithEmail:success"+user);
                }else{
                    Log.d("LODINN", "createUserWithEmail:Failure "+it.exception.toString());

                }
            })
    }
}