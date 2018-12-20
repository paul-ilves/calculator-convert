package com.paulilves.currencycalculator

import com.paulilves.currencycalculator.utils.MathUtils
import org.koin.dsl.module.module

val appModule = module {
    single { MathUtils() }
}