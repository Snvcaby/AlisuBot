package xyz.irosoralive.command

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.RawCommand
import net.mamoe.mirai.message.data.MessageChain
import xyz.irosoralive.AlisuBot
import xyz.irosoralive.data.dydyData
import kotlin.random.Random

object dydy :RawCommand(
    AlisuBot,
    primaryName = "dydy",
    prefixOptional = true,
    usage = "/dydy",
    description = "dydy"
){
    override suspend fun CommandSender.onCommand(args: MessageChain) {
        sendMessage(dydyData.dydyMessageList[Random.nextInt(0,dydyData.dydyMessageList.size)])
    }
}