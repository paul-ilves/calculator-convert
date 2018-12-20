package com.paulilves.currencycalculator.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.paulilves.currencycalculator.R
import com.paulilves.currencycalculator.enums.OperandViewMode
import com.paulilves.currencycalculator.enums.Operator
import com.paulilves.currencycalculator.utils.MathUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.math.BigDecimal
import java.text.DecimalFormat

class MainActivity : Activity() {

    private val mathUtils: MathUtils by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        operand1Text.setText(operand1.toString())
    }

    private var operand1: BigDecimal = 0.toBigDecimal()
    private var operand2: BigDecimal = 0.toBigDecimal()
    private var operator: Operator = Operator.NONE
    private var operand2Selected = false
    private var inputAfterDot = false
    private var operatorSelection = false

    private var operand1ViewMode = OperandViewMode.INTEGER
    private var operand2ViewMode = OperandViewMode.INTEGER

    fun clickNumber(view: View) {
        view as Button
        val inputNumber = view.text.toString().toInt()


        //Check whether the input number should be appended to operand1
        if (!operand2Selected) {
            operand1 = if (mathUtils.isInteger(operand1) && !inputAfterDot)
                mathUtils.appendDigitInteger(operand1, inputNumber)
            else
                mathUtils.appendDigitDecimal(operand1, inputNumber)
        } else {
            operand2 = if (mathUtils.isInteger(operand2) && !inputAfterDot)
                mathUtils.appendDigitInteger(operand2, inputNumber)
            else
                mathUtils.appendDigitDecimal(operand2, inputNumber)
        }

        updateView()
    }

    fun clickClear(view: View) {
        operand1 = 0.toBigDecimal()
        operand2 = 0.toBigDecimal()
        operator = Operator.NONE
        updateView()
    }

    fun clickChangeSign(view: View) {
        if (!operand2Selected)
            operand1 = -operand1
        else
            operand2 = -operand2
        updateView()
    }

    fun clickDot(view: View) {
        inputAfterDot = true
        updateView()
    }


    private fun updateView() {

        operand1ViewMode = if (mathUtils.isInteger(operand1)) OperandViewMode.INTEGER
        else OperandViewMode.DOUBLE


        val decimalFormat1 = if (operand1ViewMode == OperandViewMode.INTEGER) DecimalFormat("###") else DecimalFormat("###.000000")
        operand1Text.setText(decimalFormat1.format(operand1))

        operand2ViewMode = if (mathUtils.isInteger(operand2)) OperandViewMode.INTEGER
        else OperandViewMode.DOUBLE


        val decimalFormat2 = if (operand2ViewMode == OperandViewMode.INTEGER) DecimalFormat("###") else DecimalFormat("###.000000")
        if (operand2 != 0.toBigDecimal()) operand1Text.text.append(decimalFormat2.format(operand2))

    }

}
