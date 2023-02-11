package com.example.academyhomework

fun main() {

    sum(1.1, 22.5, 6.3, -20.6)

}

//задание 2a

fun sum(vararg value: Double) {
    var result = 0.0
    for (n in value) {
        result += n
    }
    println("Sum result: $result")
}