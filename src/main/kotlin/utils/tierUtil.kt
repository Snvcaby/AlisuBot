package xyz.irosoralive.utils

object tierUtil {
    fun cardTier(i: Int):Int{
        if (i>=85) return 3
        return i/35
    }
}