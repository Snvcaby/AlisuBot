package xyz.irosoralive.command

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CommandSenderOnMessage
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.descriptor.ExperimentalCommandDescriptors
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.QuoteReply
import xyz.irosoralive.AlisuBot
import xyz.irosoralive.data.dydyData
import xyz.irosoralive.utils.QuoteReplyOriginalMessageChain.originalMessageFormLocal

object dydyCom: CompositeCommand(
    AlisuBot,
    primaryName = "dy",


)  {
    @SubCommand("add")
    @Description("添加dydy")
    suspend fun CommandSender.add(args:MessageChain){
        try {
            dydyData.dydyMessageSet.add(args)
            sendMessage("添加成功")
        }catch (e:Exception){
            e.printStackTrace()
            sendMessage("添加错误。请查看控制台日志。")
            return
        }

    }

    @SubCommand("add")
    @Description("添加dydy")
    suspend fun CommandSender.add(){
        val ms = (this as CommandSenderOnMessage<*>).fromEvent.message[QuoteReply]
        if(ms!=null){
            try{
                dydyData.dydyMessageSet.add(ms.originalMessageFormLocal)
                sendMessage("添加成功")
            }catch (e:Exception){
                e.printStackTrace()
                sendMessage("添加错误。请查看控制台日志。")
                return
            }
        }

    }


    @SubCommand("del")
    @Description("删除dydy")
    suspend fun CommandSender.delete(args: MessageChain){
        try {
            if (!dydyData.dydyMessageSet.contains(args)){
                sendMessage("删除失败:无法删除不存在的消息。")
            }
            dydyData.dydyMessageSet.remove(args)
            sendMessage("删除成功。")

        }catch (e:Exception){
            e.printStackTrace()
            sendMessage("删除错误。请查看控制台日志。")
        }
    }

    @ExperimentalCommandDescriptors
    @ConsoleExperimentalApi
    override val prefixOptional: Boolean = true
}