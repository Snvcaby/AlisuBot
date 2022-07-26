package xyz.irosoralive

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info
import xyz.irosoralive.Config.osuConfig
import xyz.irosoralive.commandOsu.osuUser

object AlisuBot : KotlinPlugin(
    JvmPluginDescription(
        id = "xyz.irosoralive.AlisuBot",
        name = "AlisuBot",
        version = "0.1",
    ) {
        author("IroSorA")
        info("""A plugin for Mirai bot.""")
    }
) {
    override fun onEnable() {
        logger.info { "AlisuBot Loading Complete!" }
        osuUser.register()
        osuConfig.reload()
    }
}