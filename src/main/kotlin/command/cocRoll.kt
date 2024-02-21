package xyz.irosoralive.command

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CommandSenderOnMessage
import net.mamoe.mirai.console.command.RawCommand
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.MessageSource.Key.quote
import xyz.irosoralive.AlisuBot
import java.util.*
import kotlin.random.Random

object cocRoll : RawCommand(

    AlisuBot,
    primaryName = "roll",
    prefixOptional = true,
    usage = "/roll nDm",
    description = "cocRoll点"
) {
    override suspend fun CommandSender.onCommand(args: MessageChain) {
        sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote()+ calculateNDmExpression(args.toString()))
//        println(calculateNDmExpression(args.toString()))
    }
    fun calculateNDmExpression(input: String): String {
        val expressions = input.split(Regex("[+]")) // 根据加号和减号拆分表达式
        val stringBuilder = StringBuilder()

        expressions.forEachIndexed { index, expression ->
            val operator = if (expression.startsWith("-")) -1 else 1
            val (n, m) = parseInput(expression)

            if (index > 0) {
                if (operator > 0) {
                    stringBuilder.append(" + ")
                } else {
                    stringBuilder.append(" - ")
                }
            }

            for (i in 1..n) {
                if (i > 1) {
                    if(operator > 0){
                        stringBuilder.append(" + ")
                    } else {
                        stringBuilder.append(" - ")
                    }
                }
                stringBuilder.append(Random.nextInt(1, m + 1))
            }
        }

        val totalResult = evaluateExpression(stringBuilder.toString().replace(Regex("[()]"),""))
        stringBuilder.append(" = $totalResult")
        return stringBuilder.toString()
    }

    fun parseInput(input: String): Pair<Int, Int> {
        val regex = Regex("(\\d+)D(\\d+)") // 匹配数字D数字格式的正则表达式
        val matchResult = regex.find(input)
            ?: throw IllegalArgumentException("Invalid input format: $input")

        val (nStr, mStr) = matchResult.destructured
        val n = nStr.toInt()
        val m = mStr.toInt()

        return Pair(n, m)
    }


    fun evaluateExpression(exp: String): Int {
        val stack = Stack<Int>()
        var operand = 0
        var result = 0
        var sign = 1
        val expression = exp.replace("\\s".toRegex(), "")

        for (char in expression) {
            when {
                char.isDigit() -> operand = operand * 10 + char.toString().toInt()
                char == '+' -> {
                    result += sign * operand
                    sign = 1
                    operand = 0
                }
                char == '-' -> {
                    result += sign * operand
                    sign = -1
                    operand = 0
                }
                char == '(' -> {
                    stack.push(result)
                    stack.push(sign)
                    result = 0
                    sign = 1
                }
                char == ')' -> {
                    result += sign * operand
                    result *= stack.pop()
                    result += stack.pop()
                    operand = 0
                }
            }
        }

        return result + sign * operand
    }


}