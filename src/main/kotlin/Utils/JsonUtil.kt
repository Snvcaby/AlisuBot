package utils

import xyz.irosoralive.bean.osuUserBean

object JsonUtil {
    fun findUser(userBean: osuUserBean): String {

        val outStrBuilder = StringBuilder()
        outStrBuilder.appendLine("Name:${userBean.osuName}")
        outStrBuilder.appendLine("OsuID:${userBean.osuId}")
        outStrBuilder.appendLine("Country:${userBean.country}")
        outStrBuilder.appendLine("JoinDate:${userBean.joinDate}")
        outStrBuilder.appendLine("PlayCount:${userBean.playCount}")
        outStrBuilder.appendLine("PP:${String.format("%.2f",userBean.ppRaw)}")
        outStrBuilder.appendLine("Accuracy:${String.format("%.2f",userBean.accuracy)}%")
        outStrBuilder.appendLine("Rank:${userBean.ppRank}")
        outStrBuilder.appendLine("SelfCountryRank:${userBean.ppCountryRank}")


        return outStrBuilder.toString()
    }
}