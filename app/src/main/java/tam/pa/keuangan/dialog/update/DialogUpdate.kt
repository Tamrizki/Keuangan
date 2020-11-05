package tam.pa.keuangan.dialog.update

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.view.View
import android.view.Window
import android.widget.*
import tam.pa.keuangan.R
import tam.pa.keuangan.model.KeuanganItem

class DialogUpdate (val ctx: Context, val data: KeuanganItem, var index: Int):Dialog(ctx), IViewUpdate, View.OnClickListener {
    lateinit var radioButton: RadioButton
    lateinit var etNote: EditText
    lateinit var etNominal: EditText
    lateinit var btnBatal: Button
    lateinit var btnHapus: Button
    lateinit var btnUpdate: Button
    lateinit var rGrup: RadioGroup
    lateinit var iView: IViewUpdate
    lateinit var presenter: UpdatePresenter

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.custom_update_dialog)
        init()
        index++

        etNote.text = data.title.toString().toEditable()
        etNominal.text = data.nominal.toString().toEditable()
        iView = this
        presenter = UpdatePresenter(iView)
        btnBatal.setOnClickListener(this)
        btnHapus.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
    }
    override fun onSuccessUpdate(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        dismiss()
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    override fun onSuccessDelete(msg: String) {
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
        btnHapus = findViewById(R.id.btnHapus)
        btnUpdate = findViewById(R.id.btnUpdate)
        rGrup = findViewById(R.id.rGrup)
    }

    fun onGetType(): String{
        val selection: Int = rGrup.checkedRadioButtonId
        radioButton = findViewById(selection)
        return radioButton.text.toString()
    }

    override fun onClick(view: View?) {
        if (view == btnBatal){
            cancel()
        }else if (view == btnHapus){
            presenter.onDeleteList(this.index.toString())
        }else if (view == btnUpdate){
            if (etNote.text.toString().isNullOrEmpty()){
                etNote.error = "Form must be field!"
                etNote.requestFocus()
            }
            else if (etNominal.text.toString().isNullOrEmpty()){
                etNominal.error = "Form must be field!"
                etNominal.requestFocus()
            }
            else{
                val updateData = KeuanganItem(data.id, onGetType(), etNote.text.toString(), etNominal.text.toString().toInt(), data.date)
                presenter.onUpdateList(updateData, this.index.toString())
            }
        }
    }
}