package com.example.academyhomework

fun main() {

    //задание 4a

    val listOfNumber = listOf(4, 1, -42, 18, 123, 0, -10, 124)
    val doubleFigures = listOfNumber.any {
        it in 0..10
    }
    println("Does the array contain numbers from 0 to 10: $doubleFigures")

    //задание 4b

    val listOfNumber2 = mutableListOf(2, 10, 10, 5, -2, 3, 6, 2)
    listOfNumber2.add(4)
    listOfNumber2 += -2
    println("Add to list \n$listOfNumber2")

    val distinct = listOfNumber2.distinct()
    println("Unique numbers \n$distinct")
    val filter = listOfNumber2.filter { it % 2 != 0 }
    println("Odd numbers \n$filter\n")

    print("All elements\n")
    listOfNumber2.forEach {
        print("$it ")
    }

    println("\n\nPrime numbers in list")
    for (n in listOfNumber2.indices) {
        isPrime(listOfNumber2[n])
    }

    val find = listOfNumber2.find { it % 2 == 0 }
    println("Find \n$find")
    val group = listOfNumber2.groupBy { it.plus(1) }
    println("Group \n$group")
    val all = listOfNumber2.all { it > 0 }
    println("All \n$all")
    val any = listOfNumber2.any { it > 0 }
    println("Any \n$any")
    val (x1, x2) = listOfNumber2
    println("Destructuring $x1, $x2")
}