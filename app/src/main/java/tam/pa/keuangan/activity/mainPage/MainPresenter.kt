package tam.pa.keuangan.activity.mainPage

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import tam.pa.keuangan.R
import tam.pa.keuangan.model.KeuanganItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainPresenter(val iView: IViewMain) {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference()
    val user = FirebaseAuth.getInstance().currentUser
    val dbReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    var list: MutableList<KeuanganItem> = mutableListOf()
    var listNominal: MutableList<KeuanganItem> = mutableListOf()
    var pemasukan = 0
    var pengeluaran = 0
    fun onReadDB(){
        list.clear()
        listNominal.clear()
        dbReference.child("dataCatatan").child(user?.uid.toString())
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                        for (it in snapshot.children){
                            var data = it.getValue(KeuanganItem::class.java)!!
                            list.add(data)
                            listNominal.add(data)
                        }
                        iView.onReadDB(list)
                }
                override fun onCancelled(error: DatabaseError) {
                    iView.onFail(error.message.toString())
                }
            })
    }
    fun onGetSaldo():Int{
        listNominal.forEach {
            if (it.type.toString().toLowerCase().equals("pengeluaran")){
                pengeluaran += it.nominal!!
            }else{
                pemasukan += it.nominal!!
            }
        }
        return pemasukan-pengeluaran
    }
    fun onGetPengeluaran(): Int{
        return pengeluaran
    }
    fun onGetPemasukan(): Int{
        return pemasukan
    }
}

