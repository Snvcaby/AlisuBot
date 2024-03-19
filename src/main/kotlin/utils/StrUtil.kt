package xyz.irosoralive.utils

import kotlin.math.pow


object StrUtil {
    val reMath = Regex("Math\\.(.*)")
    fun String.redirect(){
        //todo

    }

    //四则运算+D运算
    fun String.cal(): Int {
        val stack: ArrayDeque<Double> = ArrayDeque()
        var tempResult = 0
        for ((c,i) in this.withIndex()) {
            if (i.isDigit()) {
                if (this[c+1].isDigit()){
                    tempResult=tempResult*10 + (i-'0')
                }else{
                    tempResult=tempResult*10 + (i-'0')
                    stack.add(tempResult.toDouble())
                    tempResult=0
                }
            }
            else when (i) {
                '+' -> {
                    val temp = stack[stack.size - 2] + stack[stack.size - 1]
                    stack.removeLastOrNull()
                    stack.removeLastOrNull()
                    stack.add(temp)
                }
                '-' -> {
                    val temp = stack[stack.size - 2] - stack[stack.size - 1]
                    stack.removeLastOrNull()
                    stack.removeLastOrNull()
                    stack.add(temp)
                }
                '*' -> {
                    val temp = stack[stack.size - 2] * stack[stack.size - 1]
                    stack.removeLastOrNull()
                    stack.removeLastOrNull()
                    stack.add(temp)
                }
                '/' -> {
                    val temp = stack[stack.size - 2] / stack[stack.size - 1]
                    stack.removeLastOrNull()
                    stack.removeLastOrNull()
                    stack.add(temp)
                }
                'D' -> {
                    val temp = NDmUtil.nDmForResult(stack[stack.size - 2].toInt(),stack[stack.size - 1].toInt())
                    stack.removeLastOrNull()
                    stack.removeLastOrNull()
                    stack.add(temp.toDouble())
                }
                '^' -> {
                    val temp = stack[stack.size - 2].pow(stack[stack.size - 1])
                    stack.removeLastOrNull()
                    stack.removeLastOrNull()
                    stack.add(temp)
                }
            }
        }

        return stack.first().toInt()
    }

    //中缀变后缀
    fun String.sufExp(): String {
        val sb = StringBuilder()
        val stack = ArrayDeque<Char>()
        var ret = 0

        fun handleOperator(operator: Char) {
            while (stack.isNotEmpty() && ret(operator) <= ret(stack.last())) {
                sb.append("${stack.removeLast()} ")
            }
            stack.add(operator)
            ret = ret(operator)
        }

        for ((n, i) in this.withIndex()) {
            when {
                i.isDigit() -> {
                    if (n < this.length - 1 && this[n + 1].isDigit()) {
                        sb.append(i)
                    } else {
                        sb.append("$i ")
                    }
                }
                i in "+-*/D^" -> handleOperator(i)
                i == '(' -> {
                    stack.add(i)
                    ret = 0
                }
                i == ')' -> {
                    while (stack.last() != '(') {
                        sb.append("${stack.removeLast()} ")
                    }
                    stack.removeLast()
                    ret = ret(stack.lastOrNull())
                }
                else -> continue
            }
        }

        while (stack.isNotEmpty()) {
            sb.append(stack.removeLast())
        }

        return sb.toString()
    }
}
fun ret(c: Char?): Int {
    return when (c) {
        '+', '-' -> 1
        '*', '/' -> 2
        'D' -> 3
        '^' -> 4
        '(', ')' -> 0
        null -> 0
        else -> -1
    }
}



