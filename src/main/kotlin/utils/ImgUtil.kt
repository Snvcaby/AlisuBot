package xyz.irosoralive.utils

import xyz.irosoralive.AlisuBot.logger
import xyz.irosoralive.bean.attributeTier
import xyz.irosoralive.bean.cocBaseCard
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import javax.imageio.ImageIO
import xyz.irosoralive.utils.tierUtil.cardTier

class ImgUtil {
    val imgWidth = 800
    val radius = 20
    private val renderingHints = RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON)

    init {
        renderingHints[RenderingHints.KEY_RENDERING] = RenderingHints.VALUE_RENDER_QUALITY
    }
    fun drawCardAtTime(data: cocBaseCard,times:Int):BufferedImage{
        val cardHeight = 600
        val contentMargin = 20
        val imgHeight = cardHeight*times+contentMargin*(times+1)
        val fontSize = 30
        val fontMargin = 15
        val sCardWidth = 165
        val sCardHeight = 150
        val mCardHeight = 225

        val image = BufferedImage(imgWidth,imgHeight,BufferedImage.TYPE_INT_ARGB)
        image.createGraphics().apply {
            setRenderingHints(ImgUtil().renderingHints)
            background(this,imgHeight,Color(100,54,60))//底层背景颜色

            for (i in 1..times){
                //数据生成
                data.cardGen()
                //渲染卡片
                color = Color(215,196,187)//卡片颜色
                fillRoundRect(contentMargin,contentMargin*i+600*(i-1),imgWidth-contentMargin*2,cardHeight,radius,radius)
                //渲染小卡片
                color = Color(157,130,127)
                fillRoundRect(2*contentMargin,contentMargin*(i+1)+600*(i-1),imgWidth-contentMargin*4,50,radius,radius)

                color = Color(177,150,147)
                fillRoundRect(2*contentMargin,contentMargin*(i+2)+600*(i-1)+50,sCardWidth,sCardHeight,radius,radius)//1
                fillRoundRect(3*contentMargin+sCardWidth,contentMargin*(i+2)+600*(i-1)+50,sCardWidth,sCardHeight,radius,radius)//2
                fillRoundRect(4*contentMargin+2*sCardWidth,contentMargin*(i+2)+600*(i-1)+50,sCardWidth,sCardHeight,radius,radius)//3

                fillRoundRect(2*contentMargin,contentMargin*(i+3)+600*(i-1)+50+sCardHeight,sCardWidth,sCardHeight,radius,radius)//4
                fillRoundRect(3*contentMargin+sCardWidth,contentMargin*(i+3)+600*(i-1)+50+sCardHeight,sCardWidth,sCardHeight,radius,radius)//5
                fillRoundRect(4*contentMargin+2*sCardWidth,contentMargin*(i+3)+600*(i-1)+50+sCardHeight,sCardWidth,sCardHeight,radius,radius)//6

                fillRoundRect(2*contentMargin,contentMargin*(i+4)+600*(i-1)+50+2*sCardHeight,sCardWidth,sCardHeight,radius,radius)//7
                fillRoundRect(3*contentMargin+sCardWidth,contentMargin*(i+4)+600*(i-1)+50+2*sCardHeight,sCardWidth,sCardHeight,radius,radius)//8
                fillRoundRect(4*contentMargin+2*sCardWidth,contentMargin*(i+4)+600*(i-1)+50+2*sCardHeight,sCardWidth,sCardHeight,radius,radius)//9

                fillRoundRect(5*contentMargin+3*sCardWidth,contentMargin*(i+2)+600*(i-1)+50,sCardWidth,mCardHeight,radius,radius)//ex1
                fillRoundRect(5*contentMargin+3*sCardWidth,contentMargin*(i+4)+600*(i-1)+50+mCardHeight,sCardWidth,mCardHeight,radius,radius)//ex2


                color=Color(85,66,54) //字体颜色
                font = Font("微软雅黑",Font.BOLD,fontSize)
                val fixedOffsetY = this.fontMetrics.ascent - (this.fontMetrics.height / 2 - fontSize / 2)
                drawString("CARD  $i",2*contentMargin+15,contentMargin*(i+1)+600*(i-1)+fixedOffsetY+10)

                drawString("力量    "+data.STR,2*contentMargin+fontMargin,contentMargin*(i+2)+600*(i-1)+50+fixedOffsetY+fontMargin)
                drawString("敏捷    " + data.DEX,3*contentMargin+sCardWidth+fontMargin,contentMargin*(i+2)+600*(i-1)+50+fixedOffsetY+fontMargin)
                drawString("意志    " + data.POW,4*contentMargin+2*sCardWidth+fontMargin,contentMargin*(i+2)+600*(i-1)+50+fixedOffsetY+fontMargin)

                drawString("体质    " + data.CON,2*contentMargin+fontMargin,contentMargin*(i+3)+600*(i-1)+50+sCardHeight+fixedOffsetY+fontMargin)
                drawString("外貌    " + data.APP,3*contentMargin+sCardWidth+fontMargin,contentMargin*(i+3)+600*(i-1)+50+sCardHeight+fixedOffsetY+fontMargin)
                drawString("教育    " + data.EDU,4*contentMargin+2*sCardWidth+fontMargin,contentMargin*(i+3)+600*(i-1)+50+sCardHeight+fixedOffsetY+fontMargin)

                drawString("体型    " + data.SIZ,2*contentMargin+fontMargin,contentMargin*(i+4)+600*(i-1)+50+2*sCardHeight+fixedOffsetY+fontMargin)
                drawString("智力    " + data.INT,3*contentMargin+sCardWidth+fontMargin,contentMargin*(i+4)+600*(i-1)+50+2*sCardHeight+fixedOffsetY+fontMargin)
                drawString("幸运    " + data.Luck,4*contentMargin+2*sCardWidth+fontMargin,contentMargin*(i+4)+600*(i-1)+50+2*sCardHeight+fixedOffsetY+fontMargin)

                drawString("总能力值",5*contentMargin+3*sCardWidth+fontMargin,contentMargin*(i+2)+600*(i-1)+50+fixedOffsetY+fontMargin)
                drawString("总能力值",5*contentMargin+3*sCardWidth+fontMargin,contentMargin*(i+4)+600*(i-1)+50+mCardHeight+fixedOffsetY+fontMargin)
                drawString("计入幸运",5*contentMargin+3*sCardWidth+fontMargin,contentMargin*(i+4)+600*(i-1)+50+mCardHeight+fixedOffsetY+2*fontMargin+fontSize)

                color=Color(114,72,50)
                drawString(attributeTier.STRAtt[cardTier(data.STR)],2*contentMargin+2*fontMargin,contentMargin*(i+2)+600*(i-1)+50+fixedOffsetY+fontMargin+2*fontSize)
                drawString(attributeTier.DEXAtt[cardTier(data.DEX)],3*contentMargin+sCardWidth+2*fontMargin,contentMargin*(i+2)+600*(i-1)+50+fixedOffsetY+fontMargin+2*fontSize)
                drawString(attributeTier.POWAtt[cardTier(data.POW)],4*contentMargin+2*sCardWidth+2*fontMargin,contentMargin*(i+2)+600*(i-1)+50+fixedOffsetY+fontMargin+2*fontSize)

                drawString(attributeTier.CONAtt[cardTier(data.CON)],2*contentMargin+2*fontMargin,contentMargin*(i+3)+600*(i-1)+50+sCardHeight+fixedOffsetY+fontMargin+2*fontSize)
                drawString(attributeTier.APPAtt[cardTier(data.APP)],3*contentMargin+sCardWidth+2*fontMargin,contentMargin*(i+3)+600*(i-1)+50+sCardHeight+fixedOffsetY+fontMargin+2*fontSize)
                drawString(attributeTier.EDUAtt[cardTier(data.EDU)],4*contentMargin+2*sCardWidth+2*fontMargin,contentMargin*(i+3)+600*(i-1)+50+sCardHeight+fixedOffsetY+fontMargin+2*fontSize)

                drawString(attributeTier.SIZAtt[cardTier(data.SIZ)],2*contentMargin+2*fontMargin,contentMargin*(i+4)+600*(i-1)+50+2*sCardHeight+fixedOffsetY+fontMargin+2*fontSize)
                drawString(attributeTier.INTAtt[cardTier(data.INT)],3*contentMargin+sCardWidth+2*fontMargin,contentMargin*(i+4)+600*(i-1)+50+2*sCardHeight+fixedOffsetY+fontMargin+2*fontSize)
                drawString(attributeTier.LuckAtt[cardTier(data.Luck)],4*contentMargin+2*sCardWidth+2*fontMargin,contentMargin*(i+4)+600*(i-1)+50+2*sCardHeight+fixedOffsetY+fontMargin+2*fontSize)

                drawString(data.cardTotal().toString(),5*contentMargin+3*sCardWidth+fontMargin,contentMargin*(i+2)+600*(i-1)+50+fixedOffsetY+2*fontMargin+3*fontSize)
                drawString(data.cardTotalIncludeLuck().toString(),5*contentMargin+3*sCardWidth+fontMargin,contentMargin*(i+4)+600*(i-1)+50+mCardHeight+fixedOffsetY+2*fontMargin+3*fontSize)


            }


        }

        return image

    }

    private fun background(graphics2D: Graphics2D,imgHeight: Int,color: Color){
        graphics2D.color = color
        graphics2D.fillRect(0,0,imgWidth,imgHeight)
    }
    fun bufferedImageToInputStream(image: BufferedImage?): InputStream? {
        val os = ByteArrayOutputStream()
        try {
            ImageIO.write(image, "png", os)
            return ByteArrayInputStream(os.toByteArray())
        } catch (e: IOException) {
            logger.error("提示:", e)
        }
        return null
    }
}