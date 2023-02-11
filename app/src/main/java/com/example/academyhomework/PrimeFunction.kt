package com.example.academyhomework

import kotlin.math.sqrt

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