package xyz.irosoralive.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object AlisuCommon:AutoSavePluginConfig("AlisuCommon") {

    @ValueDescription("关于/osu指令的帮助")
    val desOsu : String by value<String>("")

    @ValueDescription("关于/dydy指令的帮助")
    val desDy : String by value<String>("")

    @ValueDescription("关于coc相关指令的帮助")
    val desCoc : String by value<String>("")
}