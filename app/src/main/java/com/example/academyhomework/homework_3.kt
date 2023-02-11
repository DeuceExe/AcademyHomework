package com.example.academyhomework

import kotlin.math.sqrt

fun main() {

    factorial(7)
    println(factorial2(4))

    isPrime(7)
    calculationPrime()
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

fun calculationPrime() {
    var counter = 0
    val primeList = mutableListOf<Int>()
    val primeArray = arrayListOf<Int>()

    for (n in 2..10000) {
        isPrime(n)
        if (isPrime(n).and(true)) {
            if (primeList.size != 20) primeList.add(0, n)
            if (primeList.size == 20 && primeArray.size != 10) primeArray.add(0, n)
            counter++
        }
    }
    println("First 20 numbers in list $primeList")
    println("Next 10 numbers in array $primeArray")
    println("Prime numbers in range 2..10000 - $counter")
}