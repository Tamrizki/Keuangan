package tam.pa.keuangan.activity.mainPage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tam.pa.keuangan.R
import tam.pa.keuangan.activity.mainPage.adapter.ListAdapter
import tam.pa.keuangan.dialog.add.DialogAddNote
import tam.pa.keuangan.model.KeuanganItem


class MainActivity : AppCompatActivity(), IViewMain, View.OnClickListener {
    lateinit var iViewMain: IViewMain
    lateinit var presenter: MainPresenter

    lateinit var llExpand: LinearLayout
    lateinit var tvHello: TextView
    lateinit var tvSaldo: TextView
    lateinit var tvPemasukan: TextView
    lateinit var tvPengeluaran: TextView
    lateinit var llBottom: LinearLayout
    lateinit var cvTambahCatatn: CardView
    lateinit var rvList: RecyclerView
    lateinit var listData: MutableList<KeuanganItem>
    var index: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        iViewMain = this
        presenter = MainPresenter(iViewMain)
        presenter.onReadDB()
        llBottom.setOnClickListener(this)
        cvTambahCatatn.setOnClickListener(this)
    }

    override fun onSuccessInput(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        Log.d("ResultDB", msg)
    }

    override fun onFail(msg: String) {
        Log.d("ResultDB", msg)
    }

    override fun onReadDB(list: MutableList<KeuanganItem>) {
        this.index = list.size
        listData = list
        val adapter = ListAdapter(this, list, presenter)
        rvList.setHasFixedSize(true)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = adapter

        tvSaldo.text = presenter.onGetSaldo().toString()
        tvPemasukan.text = presenter.onGetPemasukan().toString()
        tvPengeluaran.text = presenter.onGetPengeluaran().toString()
    }

    override fun init() {
        tvHello = findViewById(R.id.tvHello)
        tvSaldo = findViewById(R.id.tvSaldo)
        tvPemasukan = findViewById(R.id.tvPemasukan)
        tvPengeluaran = findViewById(R.id.tvPengeluaran)
        llBottom = findViewById(R.id.llBottom)
        llExpand = findViewById(R.id.llExpand)
        cvTambahCatatn = findViewById(R.id.cvTambahCatatn)
        rvList = findViewById(R.id.rvList)
    }

    override fun onClick(view: View?) {
        if (view == llBottom){
            when{
                llExpand.visibility == View.GONE -> {llExpand.visibility = View.VISIBLE}
                llExpand.visibility == View.VISIBLE -> {llExpand.visibility = View.GONE}
            }
        }
        else if (view == cvTambahCatatn){
            index++
            val dialog = DialogAddNote(this, index)
            dialog.show()
            dialog.setCanceledOnTouchOutside(false)

            dialog.setOnDismissListener {
                presenter.onReadDB()
            }
        }
    }
}
