package com.paulilves.currencycalculator.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.paulilves.currencycalculator.R
import com.paulilves.currencycalculator.service.CurrencyConverterService
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException
import javax.inject.Inject

class MainActivity : Activity() {

    @Inject
    lateinit var currencyConverterService: CurrencyConverterService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickConvertCurrencies(view: View) {
        val inputCashAmount = try {
            cashAmountFrom.text.toString().toDouble()
        } catch (e: NumberFormatException) {
            0.0
        }
        val textView = cashAmountTo

        val convertedCashAmount = currencyConverterService.convertCurrency(inputCashAmount, "", "")
        textView.text = convertedCashAmount.toString()
    }

}
