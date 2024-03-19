package config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object OsuConfig : AutoSavePluginConfig ("osuConfig") {

    @ValueDescription("从ppy那里获取的osuAPI")
    val osuAPI_key: String by value<String>()

    @ValueDescription("默认查询模式:可选值[0,1,2,3],对应模式分别为 Osu! / Osu!Tai ko / Osu!Catch / Osu!Mania")
    val modeInSearch:Int by value<Int>()

}