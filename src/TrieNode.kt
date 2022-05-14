class TrieNode {
    private var existsSubnet = false
    var left: TrieNode? = null
    var right: TrieNode? = null

    fun existsSubnet(): Boolean {
        return existsSubnet
    }

    fun existsSubnet(existsSubnet: Boolean) {
        this.existsSubnet = existsSubnet
    }
}