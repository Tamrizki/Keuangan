package tam.pa.keuangan.dialog.add

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import tam.pa.keuangan.model.KeuanganItem

class DialogPresenter(val iView: IViewDialog) {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference()
    val user = FirebaseAuth.getInstance().currentUser
    val dbReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    fun onInputNote(data: KeuanganItem, index: String){
        myRef.child("dataCatatan").child(user?.uid.toString()).child(index)
                .setValue(data)
                .addOnSuccessListener {
                    iView.onSuccesCreateData("Catatan berhasil ditambahkan!")
                }.addOnCanceledListener {
                    iView.onFailur("Catatan tidak berhasil ditambahkan!")
                }
    }
}