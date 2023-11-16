package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private var number1 : TextView? = null
    private var number2 : TextView? = null
    private var answer : TextView? = null
    private var operatorSymbol: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number1 = findViewById(R.id.number1)
        number2 = findViewById(R.id.number2)
        answer = findViewById(R.id.answer)
        operatorSymbol = findViewById(R.id.operatorSymbol)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnSub = findViewById<Button>(R.id.btnSub)
        val btnMul = findViewById<Button>(R.id.btnMul)
        val btnDiv = findViewById<Button>(R.id.btnDiv)
        val btnSq = findViewById<Button>(R.id.btnSq)
        val btnPow = findViewById<Button>(R.id.btnPow)
        val btnStat = findViewById<Button>(R.id.btnStat)

        btnAdd.setOnClickListener()
        {
            add()
        }

        btnSub.setOnClickListener()
        {
            subtract()
        }

        btnMul.setOnClickListener()
        {
            multiply()
        }

        btnDiv.setOnClickListener()
        {
            divide()
        }

        btnSq.setOnClickListener()
        {
            squareRoot()
        }

        btnPow.setOnClickListener()
        {
            power()
        }

        btnStat.setOnClickListener()
        {
            val intent = Intent(this, StatsFunction::class.java)
            startActivity(intent)
        }
    }

    private fun setOperatorSymbol(symbol: String) {
        operatorSymbol?.text = symbol
    }

    private fun add()
    {
        if (inputIsNotEmpty())
        {
            val input1 = number1?.text.toString().trim().toBigDecimal()
            val input2 = number2?.text.toString().trim().toBigDecimal()
            answer?.text = "${number1?.text.toString().trim().toBigDecimal()} + ${number2?.text.toString().trim().toBigDecimal()} = ${input1.add(input2)}"
            setOperatorSymbol("+")
        }
    }

    private fun subtract()
    {
        if (inputIsNotEmpty())
        {
            val input1 = number1?.text.toString().trim().toBigDecimal()
            val input2 = number2?.text.toString().trim().toBigDecimal()
            answer?.text = "${number1?.text.toString().trim().toBigDecimal()} - ${number2?.text.toString().trim().toBigDecimal()} = ${input1.subtract(input2)}"
            setOperatorSymbol("-")
        }
    }

    private fun multiply()
    {
        if (inputIsNotEmpty())
        {
            val input1 = number1?.text.toString().trim().toBigDecimal()
            val input2 = number2?.text.toString().trim().toBigDecimal()
            answer?.text = "${number1?.text.toString().trim().toBigDecimal()} x ${number2?.text.toString().trim().toBigDecimal()} = ${input1.multiply(input2)}"
            setOperatorSymbol("×")
        }
    }

    private fun divide()
    {
        if (inputIsNotEmpty())
        {
            val input1 = number1?.text.toString().trim().toBigDecimal()
            val input2 = number2?.text.toString().trim().toBigDecimal()
            if (input2.compareTo(BigDecimal.ZERO) == 0)
            {
                number2?.error = "Invalid Input"
            }
            else
            {
                val resultDiv = input1.divide(input2, 2, RoundingMode.HALF_UP)
            answer?.text = "${number1?.text.toString().trim().toBigDecimal()} ÷ ${number2?.text.toString().trim().toBigDecimal()} = ${resultDiv}"
            setOperatorSymbol("÷")
            }
        }
    }

    private fun squareRoot()
    {
            val input1 = number1?.text.toString().trim().toDouble()
            val input2 = number2?.text.toString().trim().toDoubleOrNull() ?: 0.0

        val result1: String
        val result2: String

        if (input1 < 0)
        {
            val resultNeg1 = sqrt(-input1)
            result1 = "${resultNeg1.toString()} i"
        }
        else
        {
            result1 = sqrt(input1).toString()
        }

        if (input2 < 0)
        {
            val resultNeg2 = sqrt(-input2)
            result2 = "${resultNeg2} i"
        }
        else
        {
            result2 = sqrt(input2).toString()
        }
        answer?.text = "sqrt(${number1?.text.toString().trim()}) = $result1 \nsqrt(${number2?.text.toString().trim()}) = $result2"
    }

    private fun power()
    {
        if (inputIsNotEmpty())
        {
            val input1 = number1?.text.toString().trim().toBigDecimal()
            val input2 = number2?.text.toString().trim().toInt()

            var result = 1.0
            for (i in 1..input2){
                result *= input1.toDouble()
            }

            answer?.text = "${number1?.text.toString().trim().toBigDecimal()} ^ ${number2?.text.toString().trim().toBigDecimal()} = ${result}"
        }
    }

    private fun inputIsNotEmpty(): Boolean
    {
        var b = true
        if (number1?.text.toString().trim().isEmpty())
        {
            number1?.error = "Required"
            Toast.makeText(this, "1st Number is required", Toast.LENGTH_LONG).show()
            b = false
        }
        if (number2?.text.toString().trim().isEmpty())
        {
            Toast.makeText(this, "2nd Number is required", Toast.LENGTH_LONG).show()
            number2?.error = "Required"
            b = false
        }
        return b
    }
}