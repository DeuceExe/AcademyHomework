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

    test()
}

//Задание 4c

fun test() {
    val test = mutableMapOf(
        "Family" to "Abramov",
        "Correct answers" to 0, 1 to "correct", 2 to "correct", 3 to "correct", 4 to "correct",
        5 to "correct", 6 to "correct", 7 to "correct", 8 to "correct", 9 to "correct",
        10 to "correct", 11 to "correct", 12 to "correct", 13 to "correct", 14 to "correct",
        15 to "incorrect", 16 to "correct", 17 to "correct", 18 to "correct", 19 to "correct",
        20 to "correct", 21 to "incorrect", 22 to "correct", 23 to "correct", 24 to "correct",
        25 to "correct", 26 to "correct", 27 to "correct", 28 to "correct", 29 to "incorrect",
        30 to "correct", 31 to "correct", 32 to "incorrect", 33 to "correct", 34 to "correct",
        35 to "correct", 36 to "correct", 37 to "correct", 38 to "correct", 39 to "correct",
        40 to "correct"
    )

    var goodCount = 0
    var badCount = 0
    test.forEach {
        if (it.value == "correct") goodCount++
        else if (it.value == "incorrect") badCount++
    }

    test += "Correct answers" to goodCount
    test += "Incorrect answers" to badCount
    when (goodCount) {
        40 -> test["Grade"] = 10
        39 -> test["Grade"] = 9
        38 -> test["Grade"] = 8
        in 35..37 -> test["Grade"] = 7
        in 32..34 -> test["Grade"] = 6
        in 29..31 -> test["Grade"] = 5
        in 25..28 -> test["Grade"] = 4
        in 22..24 -> test["Grade"] = 3
        in 19..21 -> test["Grade"] = 2
        else -> test["Grade"] = 1
    }
    println(test)
}