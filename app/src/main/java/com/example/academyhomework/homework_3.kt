package com.example.academyhomework

import kotlin.math.sqrt

fun main() {

    factorial(7)
    println(factorial2(4))

    isPrime(7)
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

//задание 3b

fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) {
            println("$n is not a prime number")
            return false
        }
    }
    println("$n is a prime number")
    return true
}