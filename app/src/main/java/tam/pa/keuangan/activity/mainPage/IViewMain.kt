package tam.pa.keuangan.activity.mainPage

import tam.pa.keuangan.model.KeuanganItem

interface IViewMain {
    fun onSuccessInput(msg: String)
    fun onFail(msg: String)
    fun onReadDB(list: MutableList<KeuanganItem>)
    fun init()
}