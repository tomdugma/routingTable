import java.math.BigInteger
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.*

class main {

    private val trie = Trie()

    init {
        /**
         * at first, I was planning you use a Trie data structure,
         * where each node will have an hash map of 10 entrances (0 .. 9)
         * on second thought I went on a simple binary tree
         * yes its HUGE memory consuming, but searching is at max O(32)
         * O(32) for IPv4 according to the length of the ip format
         */

        // creating the data structure which holds the subnets
        val suspiciousIPs = Arrays.asList(
            "192.0.0.0/28",
            "192.168.16.00/20",
            "192.255.255.0/28",
            "255.255.255.255/30"
        )
        for (suspiciousIP in suspiciousIPs) {
            print("Subnet : ")
            val IpInBinaryForm = convertWIthMask(suspiciousIP)
            trie.insert(IpInBinaryForm)
        }

        // local tests

        // local tests

        // local tests
        val validIp: String = convertIpToBinary("192.1.1.0")
        val validIp2: String = convertIpToBinary("255.255.255.1")
        val validIp3: String = convertIpToBinary("255.255.255.100")
        println(trie.isAllowed(validIp))
        println(trie.isAllowed(validIp2))
        println(trie.isAllowed(validIp3))
        val notValidIp: String = convertIpToBinary("255.255.255.252")
        val notValidIp2: String = convertIpToBinary("255.255.255.253")
        val notValidIp3: String = convertIpToBinary("255.255.255.254")
        println(trie.isAllowed(notValidIp))
        println(trie.isAllowed(notValidIp2))
        println(trie.isAllowed(notValidIp3))
    }

    @Throws(UnknownHostException::class)
    fun convertIpToBinary(addr: String?): String {
        var addr = addr
        val bytes = InetAddress.getByName(addr).address
        addr = BigInteger(1, bytes).toString(2)
        println(addr)
        return addr
    }

        fun convertWIthMask(addr: String): String {
            val parts: Array<String> = addr.split("/").toTypedArray()
            var mask: Int = parts[1].toInt()
            val ip = parts[0]
            val ipArray: Array<String> = ip.split("\\.").toTypedArray()
            val ipArrayInBinary = ArrayList<String>()
            for (s in ipArray) {
                if (mask == 0) break
                val temp: Int = s.toInt()
                if (temp >= 0 && temp <= 255) {
                    val currByte = StringBuilder()
                    val tmp: String = String.format("%8s", Integer.toBinaryString(temp)).replace(' ', '0')
                    for (i in 0 until tmp.length) {
                        if (mask > 0) {
                            currByte.append(tmp[i])
                            mask--
                        } else break
                    }
                    ipArrayInBinary.add(currByte.toString())
                }
            }
            val final_str = java.lang.String.join(".", ipArrayInBinary)
            println(final_str)
            return final_str
        }
    }
