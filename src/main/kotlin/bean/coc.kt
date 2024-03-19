package xyz.irosoralive.bean

import xyz.irosoralive.utils.NDmUtil.nDmForResult

data class coc(
    val code:Int
)

data class cocBaseCard(

    var STR:Int = 0,
    var DEX:Int = 0,
    var POW:Int = 0,
    var CON:Int = 0,
    var APP:Int = 0,
    var Luck:Int = 0,
    var EDU:Int = 0,
    var SIZ:Int = 0,
    var INT:Int = 0



){
    fun cardGen(){
        this.STR = nDmForResult(3,6) * 5
        this.DEX = nDmForResult(3,6) * 5
        this.POW = nDmForResult(3,6) * 5
        this.CON = nDmForResult(3,6) * 5
        this.APP = nDmForResult(3,6) * 5
        this.Luck = nDmForResult(3,6) * 5
        this.EDU = nDmForResult(2,6) + 6 * 5
        this.SIZ = nDmForResult(2,6) + 6 * 5
        this.INT = nDmForResult(2,6) + 6 * 5
    }

    fun cardTotal():Int{
        return STR+DEX+POW+CON+APP+EDU+SIZ+INT
    }
    fun cardTotalIncludeLuck():Int{
        return STR+DEX+POW+CON+APP+EDU+SIZ+INT+Luck
    }
}
object attributeTier {
    val STRAtt = arrayListOf("棉花拳","普通人","拳击手","项羽")
    val DEXAtt = arrayListOf("残疾人","有腿","短跑员","闪电侠")
    val POWAtt = arrayListOf("奴才","一般人","有主见","老板")
    val CONAtt = arrayListOf("弱鸡","锻炼过","猛男","筋肉人")
    val APPAtt = arrayListOf("哥布林","人","美少女","貂蝉")
    val EDUAtt = arrayListOf("文盲","高中生","大学牲","博士")
    val SIZAtt = arrayListOf("矮子","一米七","高个子","姚明")
    val INTAtt = arrayListOf("笨比","正常人","聪明人","爱迪生")
    val LuckAtt = arrayListOf("倒霉蛋","平庸","狗","开了")
}

