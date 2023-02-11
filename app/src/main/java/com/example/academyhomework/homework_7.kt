package com.example.academyhomework

fun main() {

    try {
        checkAge(18)
        checkAge(27)
        checkAge(100)
    } catch (e: Exception) {
        println(e.message)
    }

    val message = "123"
    val result = parseText(message)
    println(result)

}

//задание 7a

fun checkAge(age: Int): Int {
    if (age < 10 || age > 90) throw  Exception("Invalid age $age")
    println("Age $age is valid")
    return age
}

//задание 7b

fun parseText(text: String): Int? {
    return try {
        Integer.parseInt(text)
    } catch (e: NumberFormatException) {
        null
    } catch (e: Exception) {
        null
    } catch (e: IllegalArgumentException) {
        null
    } finally {
        println("Function end")
    }
}