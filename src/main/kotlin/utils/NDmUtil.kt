package xyz.irosoralive.utils

import kotlin.random.Random

object NDmUtil {
    fun nDmForResult(n:Int,m:Int):Int{
        var sum = 0
        repeat(n){
            sum+= Random.nextInt(1,m+1)
        }
        return sum
    }
}