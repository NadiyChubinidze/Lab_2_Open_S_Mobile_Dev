package com.example.lab_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickOnDigit(v: View?){
        val text= findViewById<TextView>(R.id.textView2)
        if (text.text == "0") text.text=(v as Button).text
        else text.text = text.text.toString() + (v as Button).text.toString()
    }

    fun onClickOperator(v: View?){
        val eps = 1e-6
        val text= findViewById<TextView>(R.id.textView2)
        val operator = (v as Button).text
        if (operator == "=") {
            if (calculator.operator != '\u0000') {
                if (text.text[text.text.length - 1].isDigit()) {
                    val index = text.text.indexOf(calculator.operator,1)
                    calculator.secondNum = text.text.toString().substring(index + 1).toDouble()
                    calculator.calculate()
                }
                else calculator.operator = '\u0000'
            }
            else calculator.firstNum = text.text.toString().toDouble()
            val num = calculator.firstNum.toInt()
            if (Math.abs(calculator.firstNum-num)<eps) text.text = num.toString()
            else text.text = calculator.firstNum.toString()
        }
        else {
            if (calculator.operator != '\u0000') {
                if (text.text[text.text.length-1].isDigit()) {
                    val index = text.text.indexOf(calculator.operator,1)
                    calculator.secondNum = text.text.toString().substring(index + 1).toDouble()
                }
                calculator.calculate()
                val num = calculator.firstNum.toInt()
                if (Math.abs(calculator.firstNum-num)<eps) text.text = num.toString()
                else text.text =  calculator.firstNum.toString()
                calculator.operator = (v as Button).text[0]
                text.text = text.text.toString()+ calculator.operator.toString()
            }
            else {
                calculator.firstNum = text.text.toString().toDouble()
                calculator.operator = (v as Button).text[0]
                if (text.text == "0") {
                    text.text = calculator.operator.toString()
                    calculator.operator = '\u0000'
                }
                else text.text = text.text.toString() + calculator.operator
            }
        }
    }

    fun delAll(v: View?){
        val text= findViewById<TextView>(R.id.textView2)
        calculator.clearAll()
        text.text = "0"
    }

    fun del(v: View?){
        val text= findViewById<TextView>(R.id.textView2)
        if (text.text!="0"){
            val chr = text.text[text.text.length-1]
            if(!chr.isDigit() && chr!='.') {
                calculator.firstNum=0.0
                calculator.operator='\u0000'
            }
            text.text = text.text.subSequence(0,text.text.length-1)
        }
    }

    fun onClickDot(v: View?){
        val text= findViewById<TextView>(R.id.textView2)
        if (text.text.indexOf('.') >= 0){
            if (calculator.operator!='\u0000') {
                val index = text.text.indexOf(calculator.operator, 1)
                val temp = text.text.toString().substring(index)
                if (temp.indexOf('.') < 0) text.text = text.text.toString() + "."
            }
        }
        else text.text = text.text.toString() + "."
    }
}