package com.paulilves.currencycalculator.service

import javax.inject.Inject

class CurrencyConverterService @Inject constructor() {


    fun convertCurrency(amount: Double, currencyFrom: String, currencyTo: String): Double {
        return amount * 28.4
    }

}