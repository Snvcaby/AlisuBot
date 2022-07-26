package xyz.irosoralive.bean

import com.google.gson.annotations.SerializedName
import java.util.Date

data class osu(
    val code:Int

)

data class osuUserBean(

    @SerializedName("user_id")
    val osuId:Long, //User ID
    @SerializedName("username")
    val osuName:String,  //User Name
    @SerializedName("join_date")
    val joinDate:String,
    @SerializedName("playcount")
    val playCount:Int,
    @SerializedName("pp_rank")
    val ppRank:Int,  //排名
    @SerializedName("pp_country_rank")
    val ppCountryRank:Int,  //国家排名
    @SerializedName("pp_raw")
    val ppRaw:Double,  //pp数
    @SerializedName("accuracy")
    val accuracy:Double, //准确度
    @SerializedName("country")
    val country:String,   //国家


)
