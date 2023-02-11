package com.example.academyhomework

fun main() {

    sum(1.1, 22.5, 6.3, -20.6)

    print("Login: ")
    val login = readln()
    print("Password: ")
    val password = readln()
    isValid(login, password)
}

//задание 2a

fun sum(vararg value: Double) {
    var result = 0.0
    for (n in value) {
        result += n
    }
    println("Sum result: $result")
}

//задание 2b

fun isValid(login: String, password: String) {

    fun notNull(login: String, password: String) =
        login.isNotEmpty() && password.isNotEmpty()

    if (!notNull(login, password)) {
        println("Login or password is empty")
        return
    }

    if (login.contains("@gmail.com") && (password.length in 6..12)
        && !password.contains(" ")
    ) {
        println("Correct data")
    } else {
        println("Incorrect data")
        return
    }

}
