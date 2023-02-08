import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

fun readFile(file: File): Sequence<String> {
    return BufferedReader(FileReader(file)).lineSequence()
}

fun readAndAsInt(positiveBits: BitSet, negativeBits: BitSet, ipAddress: CharSequence){
        var base = 0
        var part = 0
        var i = 0
        val n = ipAddress.length
        while (i < n) {
            val symbol = ipAddress[i]
            if (symbol == '.') {
                base = base shl java.lang.Byte.SIZE or part
                part = 0
            } else {
                part = part * 10 + symbol.code - '0'.code
            }
            ++i
        }
        val num = base shl java.lang.Byte.SIZE or part
        if (num >= 0) {
            positiveBits.set(num)
        } else {
            negativeBits.set(num.inv())
        }
}

fun countUniqueAddresses(positiveBits: BitSet, negativeBits: BitSet): Long {
    return (positiveBits.cardinality() + negativeBits.cardinality()).toLong()
}

fun main() {
    val file = File("D:\\тест\\ip_addresses.txt")

    val positiveBits = BitSet(Int.MAX_VALUE)
    val negativeBits = BitSet(Int.MAX_VALUE)
    val start = System.currentTimeMillis()
    val addresses = readFile(file)
    addresses.forEach { IpAddress ->
        readAndAsInt(positiveBits, negativeBits, IpAddress)
    }
    println(countUniqueAddresses(positiveBits, negativeBits))
    val endTime = System.currentTimeMillis() - start
    var timeProgram = endTime.toDouble()/1000
    if (timeProgram>60) {
        println("Time: ${endTime.toDouble()/60000} s")
    }
    else {
        println("Time: ${endTime.toDouble()/1000} s")
    }
}

