package xyz.irosoralive.command

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.RawCommand
import net.mamoe.mirai.message.data.MessageChain
import xyz.irosoralive.AlisuBot

object alisu: RawCommand(
    AlisuBot,
    primaryName = "alisu",
    prefixOptional = true,
    usage = "/alisu args",
    description = "alisu插件自定义帮助"
) {
    override suspend fun CommandSender.onCommand(args:MessageChain){
        when(args.toString()){
            ""-> sendMessage("")//全部输出

        }
    }
}