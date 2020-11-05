package tam.pa.keuangan.dialog.add

interface IViewDialog {
    fun onSuccesCreateData(msg: String)
    fun onFailur(msg: String)
    fun init()
}