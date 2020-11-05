package tam.pa.keuangan.dialog.update

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import tam.pa.keuangan.model.KeuanganItem

class UpdatePresenter(val iView: IViewUpdate){
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference()
    val user = FirebaseAuth.getInstance().currentUser
    val dbReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun onDeleteList(index: String){
        dbReference.child("dataCatatan").child(user!!.uid).child(index)
                .removeValue()
                .addOnSuccessListener {
                    iView.onSuccessDelete("Data berhasil dihapus!")
                }.addOnFailureListener {
                    iView.onFailur("Data tidak berhasil dihapus!")
                }
    }
    fun onUpdateList(data: KeuanganItem, index: String){
        myRef.child("dataCatatan").child(user?.uid.toString()).child(index)
                .setValue(data)
                .addOnSuccessListener {
                    iView.onSuccessUpdate("Catatan berhasil diperbarui!")
                }.addOnCanceledListener {
                    iView.onFailur("Catatan tidak berhasil diperbarui!")
                }
    }
}