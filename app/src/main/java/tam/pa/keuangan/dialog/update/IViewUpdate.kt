package tam.pa.keuangan.dialog.update

interface IViewUpdate {
    fun onSuccessUpdate(msg: String)
    fun onSuccessDelete(msg: String)
    fun onFailur(msg: String)
    fun init()
}