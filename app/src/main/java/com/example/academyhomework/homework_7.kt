package com.example.academyhomework

fun main() {

    try {
        checkAge(18)
        checkAge(27)
        checkAge(100)
    } catch (e: Exception) {
        println(e.message)
    }

}

//задание 7a

fun checkAge(age: Int): Int {
    if (age < 10 || age > 90) throw  Exception("Invalid age $age")
    println("Age $age is valid")
    return age
}