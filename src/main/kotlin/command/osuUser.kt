package xyz.irosoralive.commandOsu

import com.google.gson.Gson
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CommandSenderOnMessage
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.message.data.MessageSource.Key.quote
import xyz.irosoralive.AlisuBot
import config.OsuConfig.modeInSearch
import config.OsuConfig.osuAPI_key
import io.ktor.http.*
import xyz.irosoralive.utils.JsonUtil
import xyz.irosoralive.bean.osuUserBean
import java.net.URL

object osuUser : CompositeCommand(
    AlisuBot,
    primaryName = "osu!"
) {

    @SubCommand("user")
    @Description("自由用户查询")
    suspend fun CommandSender.userinfo(username: String) {
        try {

//            val tempAPI = "e82d1aa1289f2d36cb747c843f8bc88ea82956d2"
            val url = "https://osu.ppy.sh/api/get_user?k=${osuAPI_key}&u=${username}&m=${modeInSearch}"

//            val url = "https://osu.ppy.sh/api/get_user?k=${tempAPI}&u=${username}&m=${modeInSearch}"

            val rowJs:String = URL(url).readText()

            val userJsonS: String = if (rowJs != ""){
                URL(url).readText().drop(1).dropLast(1)
            }else{
                null.toString()
            }
            if (userJsonS == "\"error\":\"Please provide a valid API key.\"") {
                sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "非法API，请核对配置：osuConfig中的osuAPI_key是否填写错误.")
            } else if (modeInSearch != 0 && modeInSearch != 1 && modeInSearch != 2 && modeInSearch != 3) {
                sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "非法的模式，请核对配置：osuConfig中的modeInSearch是否填写错误.")
            } else if (userJsonS == "") {
                sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "找不到用户，打错了还是销号跑路了?")
            } else {
                val user: osuUserBean = Gson().fromJson(userJsonS, osuUserBean::class.java)
                val out = JsonUtil.findUser(user)
                sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + out)
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


//    @SubCommand("self")
//    @Description("查询自身用户信息")
//    suspend fun CommandSender.userinfoSelf(){
//        try {
//            val id = bindMap.get((this as CommandSenderOnMessage<*>).user!!.id)
//            if (id != null){
//                val username: String? = bindMap[id.toLong()]
//                val url = "https://osu.ppy.sh/api/get_user?k=${osuAPI_key}&u=${username}&m=${modeInSearch}"
//                val userJsonS: String = URL(url).readText().drop(1).dropLast(1)
//                if (userJsonS.equals("\"error\":\"Please provide a valid API key.\"")) {
//                    sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "非法API，请核对配置：osuConfig中的osuAPI_key是否填写错误。")
//                } else if (modeInSearch != 0 && modeInSearch != 1 && modeInSearch != 2 && modeInSearch != 3) {
//                    sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "非法的模式，请核对配置：osuConfig中的modeInSearch是否填写错误")
//                } else {
//                    val user: osuUserBean = Gson().fromJson(userJsonS, osuUserBean::class.java)
//                    when (userJsonS) {
//                        "" -> sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "用户不存在")
//                        else -> {
//                            val out = JsonUtil.findUser(user)
//                            sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + out)
//                        }
//
//                    }
//                }
//
//            }else{
//                sendMessage("没有绑定osu用户名，请先用 /osu! bind [user] 绑定用户名")
//            }
//        }catch (e:Exception){
//            e.printStackTrace()
//        }
//
//    }
//
//    @SubCommand("bind")
//    @Description("qq号与用户名绑定")
//    suspend fun CommandSender.userBind(username: String){
//        try {
//            val qq:Long = (this as CommandSenderOnMessage<*>).user!!.id
//
//
//            if(bindMap[qq] ==null){
//                bindMap += Pair(qq,username)
//                sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "绑定成功:${qq} -> $username")
//            }else{
//                sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "失败:已绑定")
//            }
//
//        }catch (e: Exception){
//            e.printStackTrace()
//        }
//    }

    //todo
    //preview
}