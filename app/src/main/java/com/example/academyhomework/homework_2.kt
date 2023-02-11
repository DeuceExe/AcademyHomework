package com.example.academyhomework

import kotlin.math.max

fun main() {

    sum(1.1, 22.5, 6.3, -20.6)

    print("Login: ")
    val login = readln()
    print("Password: ")
    val password = readln()
    isValid(login, password)

    print("Date(dd.mm.yyyy): ")
    val date = readln()
    checkDate(date)

    doOperation(41, 13, '+')
    doOperation(20, 200, '-')
    doOperation(15, 5, '*')
    doOperation(49, 7, '/')

    val array = intArrayOf(-2, 16, -35, 20, 0)
    array.indexOfMax(array)

    val text1 = "Example text for testing method"
    val text2 = "This method use for text"
    val countWords = text1.coincidence(text1, text2)
    println("Coincidence - $countWords")
}

//задание 2a

fun sum(vararg value: Double) {
    var result = 0.0
    for (n in value) {
        result += n
    }
    println("Sum result: $result")
}

//задание 2b

fun isValid(login: String, password: String) {

    fun notNull(login: String, password: String) =
        login.isNotEmpty() && password.isNotEmpty()

    if (!notNull(login, password)) {
        println("Login or password is empty")
        return
    }

    if (login.contains("@gmail.com") && (password.length in 6..12)
        && !password.contains(" ")
    ) {
        println("Correct data")
    } else {
        println("Incorrect data")
        return
    }
}

//задание 2c

enum class Holidays(val date: String) {
    NEWYEAR("01.01.2023"),
    CHRISTMAS("07.01.2023"),
    VICTORYDAY("09.05.2023")
}

fun checkDate(day: String) {
    println()
    if (day.isNotEmpty()) {
        when (day) {
            Holidays.NEWYEAR.date, Holidays.VICTORYDAY.date, Holidays.CHRISTMAS.date -> println("Holiday")
            else -> println("Weekday")
        }
    } else {
        println("Incorrect date")
        return
    }
}

//задание 2d

fun doOperation(a: Int, b: Int, operation: Char): Double {
    var result = 0.0
    when (operation) {
        '+' -> result = a.plus(b).toDouble()
        '-' -> result = a.minus(b).toDouble()
        '*' -> result = a.times(b).toDouble()
        '/' -> result = a.div(b).toDouble()
        else -> println("Incorrect operation")
    }
    println("$a $operation $b = $result")
    return result
}

//задание 2e

fun IntArray.indexOfMax(a: IntArray): Int? {
    var maxValue = 0
    return if (a.isNotEmpty()) {
        var count = 0
        maxValue = a.fold(Int.MIN_VALUE) { acc, i -> max(acc, i) }
        a.forEach {
            if (it == maxValue) count++
        }
        if (count <= 1) {
            println("max value index - ${a.indexOf(maxValue)}")
            a.indexOf(maxValue)
        } else {
            println("More than 1 values")
            return null
        }
    } else {
        println("Array is empty")
        return null
    }
}

//задание 2f

fun String.coincidence(text: String, text2: String): Int {
    var count = 0
    for (n in text.split(" ")) {
        for (m in text2.split(" ")) {
            if (n == m) count++
        }
    }
    return count
}