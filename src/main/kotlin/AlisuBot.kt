package xyz.irosoralive

import config.OsuConfig
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info
import xyz.irosoralive.command.cocCardRoll
import xyz.irosoralive.command.cocRoll
import xyz.irosoralive.command.dydy
import xyz.irosoralive.command.dydyCom
import xyz.irosoralive.commandOsu.osuUser
import xyz.irosoralive.config.AlisuCommon
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
        dependsOn("xyz.cssxsh.mirai.plugin.mirai-hibernate-plugin", false)
    }
) {
    override fun onEnable() {
        logger.info { "AlisuBot Loaded." }
        osuUser.register()
        cocRoll.register()
        cocCardRoll.register()
        dydy.register()
        dydyCom.register()

        OsuConfig.reload()


        osuBindData.reload()
        dydyData.reload()
        AlisuCommon.reload()


//        val replyChannel = AlisuBot.globalEventChannel()
//        replyChannel.subscribeAlways<MessageEvent> { it ->
//            val chain = it.message
//            if (chain.contains(QuoteReply) && chain.contains(PlainText("dyadd"))) {
//                val chainAdd = it.message[QuoteReply.Key]!!.source.originalMessage
//                logger.info(chainAdd.contentToString())
//                dydyData.dydyMessageSet.add(chainAdd)
//                subject.sendMessage("success")
//            }
//        }
//        replyChannel.registerListenerHost(QuoteReplyHandler)
    }
}