package xyz.irosoralive.command

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.message.data.MessageChain
import xyz.irosoralive.AlisuBot
import xyz.irosoralive.data.dydyData

object dydyCom: CompositeCommand(
    AlisuBot,
    primaryName = "dy",
)  {
    @SubCommand("add")
    @Description("添加dydy")
    suspend fun CommandSender.add(args:MessageChain){
        try {
            if(!dydyData.dydyMessageList.contains(args)){
                dydyData.dydyMessageList.add(args)
                sendMessage("添加成功")
            }
        }catch (e:Exception){
            e.printStackTrace()
            sendMessage("添加失败")
            return
        }
    }


    @SubCommand("del")
    @Description("删除dydy")
    suspend fun CommandSender.delete(args: MessageChain){
        try {
            dydyData.dydyMessageList.remove(args)
            sendMessage("删除成功")

        }catch (e:Exception){
            e.printStackTrace()
            sendMessage("删除失败")
        }
    }
}