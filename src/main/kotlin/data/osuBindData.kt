package xyz.irosoralive.data

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.ValueDescription

object osuBindData : AutoSavePluginData("osuBind") {
    //QQ号与osu name绑定
    @ValueDescription("绑定表")
    var bindMap : Map<Long,String> = mutableMapOf()

}