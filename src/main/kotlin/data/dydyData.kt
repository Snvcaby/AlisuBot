package xyz.irosoralive.data

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value
import net.mamoe.mirai.message.data.MessageChain

object dydyData : AutoSavePluginData("dydyData"){
    val dydyMessageList : MutableList<MessageChain> by value{}

}