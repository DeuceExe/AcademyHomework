package com.example.academyhomework

//задание 6a
class A {
    private lateinit var prop: String
    fun setUp() {
        prop = "100 + 200"
    }

    fun display() {
        println(prop)
    }
}

fun main(args: Array<String>) {
    val a = A()
    a.setUp()
    a.display()
}