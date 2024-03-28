package id.fahrizal.spellchecker

object SpellChecker {

    fun differentiate(word1: String, word2: String): Int {
        val dp = ArrayList<IntArray>()
        val w1 = " "+word1
        val w2 = " "+word2
        for(i in 0 until w1.length){
            val x = IntArray(w2.length)
            x[0]=i
            dp.add(x)
        }

        for(y in 0 until w1.length){
            val chr1 = w1[y]

            for (x in 1 until w2.length){
                if(y == 0){
                    dp[0][x] = x
                    continue
                }

                val chr2 = w2[x]
                val corner = dp[y-1][x-1]
                val top = dp[y-1][x]
                val left= dp[y][x-1]

                val minimum = Math.min(Math.min(top, left), corner)
                if(chr1 != chr2){
                    dp[y][x]= 1+ minimum
                }
                else{
                    dp[y][x]= corner
                }
            }
        }

        return dp.last().last()
    }
}