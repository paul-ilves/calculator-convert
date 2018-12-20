package com.paulilves.currencycalculator.utils

import java.math.BigDecimal

/**
 * Utility class providing logic for making math conversion.
 */
class MathUtils {

    /**
     * Internal helper method used as part of calculations for #appendDigitDecimal
     */
    private fun calculateNumberOfDigits(input: BigDecimal): Int {
        var numberToCheck = input
        var counter = 0
        while (numberToCheck % BigDecimal.ONE == BigDecimal.ZERO) {
            numberToCheck *= 10.toBigDecimal()
            counter++
        }
        return counter
    }

    /**
     * Internal helper method used as part of calculations for #appendDigitDecimal
     */
    private fun convertToPositionAfterDecimal(input: BigDecimal, number: Int): BigDecimal {
        var counter = calculateNumberOfDigits(input)
        return number.toBigDecimal() / (Math.pow(10.0, (++counter).toDouble())).toBigDecimal()
    }

    /**
     * Used to append a digit to an end of a non-floating point number
     */
    fun appendDigitDecimal(input: BigDecimal, number: Int) = input + convertToPositionAfterDecimal(input, number)

    /**
     * Used to append a digit to an end of a floating point number
     */
    fun appendDigitInteger(input: BigDecimal, number: Int) = input * 10.toBigDecimal() + number.toBigDecimal()


    /**
     * Check whether the input has no digits after floating point.
     */
    fun isInteger(input: BigDecimal) = input % 1.toBigDecimal() == 0.toBigDecimal()


}