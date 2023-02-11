package com.example.academyhomework

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