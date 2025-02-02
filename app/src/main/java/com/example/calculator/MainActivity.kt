package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() , View.OnClickListener {
    lateinit var display : TextView
    lateinit var btnClear : Button
    lateinit var btnBackspace : Button
    lateinit var btnDivide : Button
    lateinit var btnMultiply : Button
    lateinit var btnSubtract : Button
    lateinit var btnAdd : Button
    lateinit var btnEquals : Button
    lateinit var btnNegative : Button
    lateinit var btnDot : Button
    lateinit var btn1 : Button
    lateinit var btn2 : Button
    lateinit var btn3 : Button
    lateinit var btn4 : Button
    lateinit var btn5 : Button
    lateinit var btn6 : Button
    lateinit var btn7 : Button
    lateinit var btn8 : Button
    lateinit var btn9 : Button
    lateinit var btn0 : Button
    var operand : Double = 0.0
    var operation : Int = 0 // 0:None 1:Add 2:Sub 3:Mul 4:Div
    var clear : Boolean = false
    var doCalculation : Boolean = false // quick fix that im not proud of

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)
        btnClear = findViewById(R.id.button_clear)
        btnBackspace = findViewById(R.id.button_backspace)
        btnDivide = findViewById(R.id.button_divide)
        btnMultiply = findViewById(R.id.button_multiply)
        btnSubtract = findViewById(R.id.button_subtract)
        btnAdd = findViewById(R.id.button_add)
        btnEquals = findViewById(R.id.button_equals)
        btnNegative = findViewById(R.id.button_negative)
        btnDot = findViewById(R.id.button_decimal)
        btn1 = findViewById(R.id.button_1)
        btn2 = findViewById(R.id.button_2)
        btn3 = findViewById(R.id.button_3)
        btn4 = findViewById(R.id.button_4)
        btn5 = findViewById(R.id.button_5)
        btn6 = findViewById(R.id.button_6)
        btn7 = findViewById(R.id.button_7)
        btn8 = findViewById(R.id.button_8)
        btn9 = findViewById(R.id.button_9)
        btn0 = findViewById(R.id.button_0)

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
        btn0.setOnClickListener(this)
        btnClear.setOnClickListener(this)
        btnBackspace.setOnClickListener(this)
        btnDivide.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnSubtract.setOnClickListener(this)
        btnAdd.setOnClickListener(this)
        btnEquals.setOnClickListener(this)
        btnNegative.setOnClickListener(this)
        btnDot.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.button_1 -> {
                numberPressed("1")
            }
            R.id.button_2 -> {
                numberPressed("2")
            }
            R.id.button_3 -> {
                numberPressed("3")
            }
            R.id.button_4 -> {
                numberPressed("4")
            }
            R.id.button_5 -> {
                numberPressed("5")
            }
            R.id.button_6 -> {
                numberPressed("6")
            }
            R.id.button_7 -> {
                numberPressed("7")
            }
            R.id.button_8 -> {
                numberPressed("8")
            }
            R.id.button_9 -> {
                numberPressed("9")
            }
            R.id.button_0 -> {
                numberPressed("0")
            }
            R.id.button_0 -> {
                numberPressed("0")
            }
            R.id.button_decimal -> {
                numberPressed(".")
            }
            R.id.button_backspace -> {
                if(display.text.length == 1){
                    display.text = "0"
                }
                else{
                    display.text = display.text.substring(0, display.text.length-1)
                }
            }

            R.id.button_negative -> {
                if(display.text[0] == '-')
                    display.text = display.text.substring(1,display.text.length)
                else
                    display.text = "-${display.text}"
                if(!doCalculation)
                    operand *= -1
            }

            R.id.button_add -> {
                if(doCalculation)
                    calculate()
                clear = true
                operation = 1
            }
            R.id.button_subtract -> {
                if(doCalculation)
                    calculate()
                clear = true
                operation = 2
            }
            R.id.button_multiply -> {
                if(doCalculation)
                    calculate()
                clear = true
                operation = 3
            }
            R.id.button_divide -> {
                if(doCalculation)
                    calculate()
                clear = true
                operation = 4
            }
            R.id.button_equals -> {
                if(doCalculation)
                    calculate()
                operation = 0
                clear = true
            }
            R.id.button_clear -> {
                operation = 0
                operand = 0.0
                display.text = "0"
                clear = false
            }
        }
    }

    private fun numberPressed(n: String)
    {
        doCalculation = true
        if(clear){
            display.text = "0"
            clear = false
        }

        if(n == "." && display.text == "0")
        {
            display.text = "0."
            return
        }

        if(display.text.toString()=="0")
        {
            display.text = n
        }
        else if(display.text.length < 10)
            display.text = display.text.toString() + n
        println("Pressed $n")
    }

    private fun calculate()
    {
        doCalculation = false
        var res : Double = display.text.toString().toDouble()
        println("$operand and ${display.text.toString().toDouble()}")
        when(operation){
            1 -> res = (operand + display.text.toString().toDouble())
            2 -> res = (operand - display.text.toString().toDouble())
            3 -> res = (operand * display.text.toString().toDouble())
            4 -> res = (operand / display.text.toString().toDouble())
        }
        var resString = res.toString()
        if(resString.length > 10)
        {
            resString = resString.substring(0,11)
            if(resString[10] == '.'){
                resString = resString.substring(0,10)
            }
        }

        if(res % 1.0 == 0.0)
            display.text = resString.toDouble().toInt().toString()
        else
            display.text = resString
        operand = display.text.toString().toDouble()
    }
}