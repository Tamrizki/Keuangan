package tam.pa.keuangan.dialog.add

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.*
import tam.pa.keuangan.R
import tam.pa.keuangan.model.KeuanganItem
import java.text.SimpleDateFormat
import java.util.*

class DialogAddNote(val ctx: Context, val index: Int): Dialog(ctx), IViewDialog, View.OnClickListener {
    lateinit var radioButton: RadioButton
    lateinit var etNote: EditText
    lateinit var etNominal: EditText
    lateinit var btnBatal: Button
    lateinit var btnSimpan: Button
    lateinit var rGrup: RadioGroup
    lateinit var iview: IViewDialog
    lateinit var presenter: DialogPresenter

    val sdf = SimpleDateFormat("ddMyymmss")
    val idNote = sdf.format(Date())
    val today = SimpleDateFormat("dd/M/yyyy")
    val dateToday = today.format(Date())

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.custom_dialog_add_note)
        init()
        iview = this
        presenter = DialogPresenter(iview)
        btnBatal.setOnClickListener(this)
        btnSimpan.setOnClickListener(this)
    }

    fun onGetType(): String{
        val selection: Int = rGrup.checkedRadioButtonId
        radioButton = findViewById(selection)
        return radioButton.text.toString()
    }

    override fun onSuccesCreateData(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        dismiss()
    }

    override fun onFailur(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun init() {
        etNote = findViewById(R.id.etNote)
        etNominal = findViewById(R.id.etNominal)
        btnBatal = findViewById(R.id.btnBatal)
        btnSimpan = findViewById(R.id.btnSimpan)
        rGrup = findViewById(R.id.rGrup)
    }

    override fun onClick(view: View?) {
        if (view == btnBatal){
            cancel()
        }
        else if (view == btnSimpan){
            if (etNote.text.toString().isNullOrEmpty()){
                etNote.error = "Form must be field!"
                etNote.requestFocus()
            }
            else if (etNominal.text.toString().isNullOrEmpty()){
                etNominal.error = "Form must be field!"
                etNominal.requestFocus()
            }
            else{
                val data = KeuanganItem(idNote.toInt(), onGetType(), etNote.text.toString(), etNominal.text.toString().toInt(), dateToday.toString())
                presenter.onInputNote(data, this.index.toString())
            }
        }
    }
}