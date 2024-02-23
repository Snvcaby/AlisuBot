package xyz.irosoralive

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info
import config.osuConfig
import xyz.irosoralive.command.cocCardRoll
import xyz.irosoralive.command.cocRoll
import xyz.irosoralive.command.dydy
import xyz.irosoralive.command.dydyCom
import xyz.irosoralive.commandOsu.osuUser
import xyz.irosoralive.data.dydyData
import xyz.irosoralive.data.osuBindData

object AlisuBot : KotlinPlugin(
    JvmPluginDescription(
        id = "xyz.irosoralive.AlisuBot",
        name = "AlisuBot",
        version = "0.1",
    ) {
        author("kkry")
        info("""A plugin for Mirai bot.""")
    }
) {
    override fun onEnable() {
        logger.info { "AlisuBot Loaded." }
        osuUser.register()
        cocRoll.register()
        cocCardRoll.register()
        dydy.register()
        dydyCom.register()

        osuConfig.reload()


        osuBindData.reload()
        dydyData.reload()


    }
}