package xyz.irosoralive.command

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CommandSenderOnMessage
import net.mamoe.mirai.console.command.RawCommand
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.MessageSource.Key.quote
import xyz.irosoralive.AlisuBot

object cocRoll : RawCommand(

    AlisuBot,
    primaryName = "roll",
    prefixOptional = true,
    usage = "/roll nDm",
    description = "cocRoll点"
) {
    override suspend fun CommandSender.onCommand(args: MessageChain) {


        val (count, score) = args.toString().split("D")
        val resultStr: StringBuilder = StringBuilder()
        var result: Int
        var sum = 0


        if (count.toInt() == 1) {
            sendMessage(
                (this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "结果为:" + (1..score.toInt()).random()
                    .toString()
            )
        } else {
            for (i in 0 until count.toInt()) {
                result = (1..score.toInt()).random()
                sum += result
                resultStr.append(result.toString())
                if (i < count.toInt() - 1) {
                    resultStr.append(" + ")
                } else {
                    resultStr.append(" = ")
                }
            }

            sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "结果为:${resultStr}$sum")
        }


    }

    fun set_D(str:String){
    }


}