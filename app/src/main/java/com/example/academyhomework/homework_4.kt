package com.example.academyhomework

fun main() {

    //задание 4a
    val listOfNumber = listOf(4, 1, -42, 18, 123, 0, -10, 124)
    val doubleFigures = listOfNumber.any {
        it in 0..10
    }
    println("Does the array contain numbers from 0 to 10: $doubleFigures")

}