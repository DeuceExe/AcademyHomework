package com.example.academyhomework

import kotlin.math.sqrt

fun main() {

    factorial(7)
    println("4! = ${factorial2(3)}")

    val checkNumber = 7
    val check = isPrime(checkNumber)
    if (check) println("$checkNumber is a prime number")
    else println("$checkNumber is not a prime number")
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
fun factorial2(n: Int): Double = if (n < 2) 1.0 else n * factorial(n)

//задание 3b

fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) {
            return false
        }
    }
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