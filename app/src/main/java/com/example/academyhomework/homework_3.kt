package com.example.academyhomework

fun main() {

    factorial(7)
    println(factorial2(4))
}

//задание 3a

fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i
    }
    println("$n! = $result")
    return result
}

//Второй способ
fun factorial2(n: Int): Double = if (n < 2) 1.0 else n * factorial(n - 1)