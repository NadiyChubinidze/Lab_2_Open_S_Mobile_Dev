package com.example.lab_2


class Calculator {
    var firstNum: Double = 0.0
    var secondNum: Double = 0.0
    var operator: Char = '\u0000'

    fun calculate(){
        when(operator){
            '+' -> firstNum+=secondNum
            '-' -> firstNum-=secondNum
            '\u00D7' -> firstNum*=secondNum
            '^' -> firstNum = Math.pow(firstNum, secondNum)
            '\u00F7' -> if (secondNum != 0.0) firstNum /= secondNum
        }
        operator = '\u0000'
        secondNum= 0.0
        firstNum = Math.round(firstNum * 100000.0) / 100000.0
    }

    fun clearAll(){
        firstNum = 0.0
        secondNum = 0.0
        operator = '\u0000'
    }


}