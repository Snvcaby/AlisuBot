package xyz.irosoralive.commandOsu

import com.google.gson.Gson
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CommandSenderOnMessage
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.message.data.MessageSource.Key.quote
import xyz.irosoralive.AlisuBot
import config.osuConfig.modeInSearch
import config.osuConfig.osuAPI_key
import utils.JsonUtil
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


            val url: String = "https://osu.ppy.sh/api/get_user?k=${osuAPI_key}&u=${username}&m=${modeInSearch}"
            val userJsonS: String = URL(url).readText().drop(1).dropLast(1)

            if (userJsonS.equals("\"error\":\"Please provide a valid API key.\"")) {
                sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "非法API，请核对配置：osuConfig中的osuAPI_key是否填写错误。")
            } else if (modeInSearch != 0 && modeInSearch != 1 && modeInSearch != 2 && modeInSearch != 3) {
                sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "非法的模式，请核对配置：osuConfig中的modeInSearch是否填写错误")
            } else {
                val user: osuUserBean = Gson().fromJson(userJsonS, osuUserBean::class.java)
                when (userJsonS) {
                    "" -> sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "未知的用户")
                    else -> {
                        val out = JsonUtil.findUser(user)
                        sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + out)
                    }

                }
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SubCommand("bind")
    @Description("qq号与用户名绑定")
    suspend fun CommandSender.userBind(username: String){

    }

    //todo
    //preview
}