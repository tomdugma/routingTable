class Trie {
    private val root = TrieNode()
    fun insert(ip_address: String) {
        var current: TrieNode? = root
        for (i in ip_address.indices) {
            if (ip_address[i] == '.') continue
            if (ip_address[i] == '0') { // left
                if (current!!.left == null) current.left = TrieNode()
                current = current.left
            } else {
                if (current!!.right == null) current.right = TrieNode()
                current = current.right
            }
        }
        current!!.existsSubnet(true)
    }

    fun isAllowed(incomingIp: String): Boolean {
        var current: TrieNode? = root
        for (element in incomingIp) {
            // we found a subnet
            current = if (current!!.existsSubnet()) {
                return false
            } else {
                val ch = element
                if (ch == '0') {
                    if (current.left != null) current.left else return true
                } else { // ch = 1
                    if (current.right != null) current.right else return true
                }
            }
        }
        return true
    }
}