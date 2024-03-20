package xyz.irosoralive.utils

import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.QuoteReply
import xyz.cssxsh.mirai.hibernate.MiraiHibernateRecorder

object QuoteReplyOriginalMessageChain {
    val QuoteReply.originalMessageFormLocal : MessageChain
        get() = MiraiHibernateRecorder[source].firstOrNull()?.toMessageChain() ?: source.originalMessage
}