import kotlin.math.ceil


fun main() {
    val clients: Array<Client> = arrayOf(
        Client(100.0, false),
        Client(1000.0, false),
        Client(1001.0, true),
        Client(10000.0, false),
        Client(10001.0, true)
    )
    clients.forEach { it.calculatePurchase(100.0) }
}


fun getRubKop(sum: Double): Array<Long> {
    val rub = sum.toLong()
    val kop = ceil(sum % 1 * 100).toLong()
    return arrayOf(rub, kop)
}


class Client(val purchasesTotal: Double, val isMusicLover: Boolean) {
    val discount3From = 1001.0
    val discount5From = 10_001.0

    fun calculatePurchase(purchase: Double) {
        val (initRub, initKop) = getRubKop(purchase)
        println("Покупка: $initRub руб. $initKop коп.")

        var sum = purchase
        val discount = when (this.purchasesTotal) {
            in discount3From..(discount5From - 1) -> 0.03
            in discount5From..Double.MAX_VALUE -> 0.05
            else -> 0.0
        }
        sum -= sum * discount
        sum = if (this.isMusicLover) sum - (sum * 0.01) else sum

        val (rub, kop) = getRubKop(sum)
        println("После применения скидок: $rub руб. $kop коп.\n")
    }
}