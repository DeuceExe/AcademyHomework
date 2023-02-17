package com.example.academyhomework

//задание 1d
const val constanta = 5

fun main() {

    //задание 1а

    val text: String
    val number = 14
    val pointNumber: Double
    var text2 = "Random text"
    var number2: Int
    var pointNumber2 = 11.2

    //задание 1b

    val byteNumber: Byte = 41
    val intNumber = byteNumber.toInt()
    val textValue = intNumber.toString()

    //задание 1c

    println("Задание 1c \nValues: Byte - $byteNumber, Int - $intNumber, String - $textValue")

    //задание 1e

    print("Input number: ")
    val inputNumber: Int = readln().toInt()
}