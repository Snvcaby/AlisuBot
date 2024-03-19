package xyz.irosoralive.command

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CommandSenderOnMessage
import net.mamoe.mirai.console.command.RawCommand
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.MessageSource.Key.quote
import xyz.irosoralive.AlisuBot
import xyz.irosoralive.utils.StrUtil.cal
import xyz.irosoralive.utils.StrUtil.sufExp
import java.util.*
import kotlin.random.Random

object cocRoll : RawCommand(

    AlisuBot,
    primaryName = "roll",
    prefixOptional = true,
    usage = "/roll nDm",
    description = "cocRoll点"
) {
    override suspend fun CommandSender.onCommand(args: MessageChain) {
//        sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote()+ calculateNDmExpression(args.toString()))
        sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote()+ args.toString().sufExp().cal().toString())
//        println(calculateNDmExpression(args.toString()))
    }


}