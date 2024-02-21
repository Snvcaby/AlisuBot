package xyz.irosoralive.command

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CommandSenderOnMessage
import net.mamoe.mirai.console.command.RawCommand
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.message.data.MessageSource.Key.quote
import xyz.irosoralive.AlisuBot
import xyz.irosoralive.bean.cocBaseCard
import xyz.irosoralive.utils.ImgUtil

object cocCardRoll: RawCommand(
    AlisuBot,
    primaryName = "card",
    prefixOptional = true,
    usage = "/card [number]",
    description = "cocRoll卡"
) {
    override suspend fun CommandSender.onCommand(args: MessageChain) {
        var num = 1
        try {
            num = args.toString().toInt()
        }catch (e:Exception){
            sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "非法输入,将默认执行一次roll卡")
        }

//        val mcb = MessageChainBuilder().append(QuoteReply(source = (this as CommandSenderOnMessage<*>).fromEvent.source))

        val data = cocBaseCard()
        data.cardGen()
        val img = ImgUtil().drawCardAtTime(data,num)
        val imgStream = ImgUtil().bufferedImageToInputStream(img)

        if (imgStream!=null){

            subject?.sendImage(imgStream,"png")
        }
//        sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + sb.toString())
    }
}