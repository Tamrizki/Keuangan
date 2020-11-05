package tam.pa.keuangan.activity.mainPage.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import tam.pa.keuangan.R
import tam.pa.keuangan.R.drawable
import tam.pa.keuangan.R.drawable.*
import tam.pa.keuangan.activity.mainPage.IViewMain
import tam.pa.keuangan.activity.mainPage.MainPresenter
import tam.pa.keuangan.dialog.update.DialogUpdate
import tam.pa.keuangan.model.KeuanganItem
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ListAdapter(val ctx: Context, val list: MutableList<KeuanganItem>, val presenter: MainPresenter):
    RecyclerView.Adapter<ListAdapter.viewHolder>() {
    class viewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val rlDate: RelativeLayout = view.findViewById(R.id.rlDate)
        val tvNote: TextView = view.findViewById(R.id.tvNote)
        val tvNominal: TextView = view.findViewById(R.id.tvNominal)

        fun getData(get: KeuanganItem) {

            tvDate.text = get.date?.take(2)
            tvNote.text = get.title
            tvNominal.text = get.nominal.toString()
            if (!get.type?.toLowerCase().equals("pengeluaran")){
                rlDate.setBackgroundColor(custom_bg_green)
            }else{
                rlDate.setBackgroundColor(custom_bg_orange)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_list_note, parent, false)
        return viewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.getData(list.get(position))
        val dialog = DialogUpdate(ctx, list.get(position), position)

        holder.itemView.setOnClickListener {
            dialog.show()
            dialog.setCanceledOnTouchOutside(false)
        }
        dialog.setOnDismissListener {
            presenter.onReadDB()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}