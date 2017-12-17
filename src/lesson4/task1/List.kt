@file:Suppress("UNUSED_PARAMETER")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.isPrime
import lesson3.task1.maxDivisor
import java.lang.Math.pow
import java.lang.Math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = Math.sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var absN = 0.0
    for (element in v) {
        absN += sqr(element)
    }
    return sqrt(absN)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var meanN = 0.0
    val k = list.size
    for (element in list) {
        meanN += element
    }
    if (k == 0) return 0.0
    else return meanN / k
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val meanN = mean(list)
    for (i in 0 until list.size) {
        list[i] -= meanN
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var c = 0.0
    for (i in 0 until b.size) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var r = 0.0
    var pow = 1.0
    for (i in p) {
        r += i * pow
        pow *= x
    }
    return r
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    for (i in 1 until list.size) list[i] += list[i - 1]
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var sp = 2
    var l = n
    while (sp <= sqrt(l.toDouble())) {
        while (l % sp == 0) {
            list += sp
            l /= sp
        }
        sp++
    }
    if (l != 1){
    list.add(l)
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString("*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val list = mutableListOf<Int>()
    var num = n
    while (num > 0) {
        list += num % base
        num /= base
    }
    return if (n == 0) listOf(0)
    else list.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val list = convert(n, base)
    val list2 = mutableListOf<String>()
    val alphabet = "abcdefghijklmnopqrstuvwxyz"
    for (i in 0 until list.size) {
        if (list[i] > 9) list2.add(alphabet[list[i] - 10].toString())
        else list2.add(list[i].toString())
    }
    return list2.joinToString("")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var n = 0
    var pow = 1
    for (i in digits.reversed()) {
        n += i * pow
        pow *= base
    }
    return n
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val list = listOf(Pair("I", 1), Pair("IV", 4), Pair("V", 5), Pair("IX", 9), Pair("X", 10), Pair("XL", 40)
            , Pair("L", 50), Pair("XC", 90), Pair("C", 100), Pair("CD", 400), Pair("D", 500), Pair("CM", 900)
            , Pair("M", 1000))
    var num = n
    val result = StringBuilder()
    var i = list.size - 1
    while (num > 0) {
        if (num >= list[i].second) {
            result.append(list[i].first)
            num -= list[i].second
        } else i -= 1
    }
    return result.toString()
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val arabic = mapOf("девятьсот" to 900, "восемьсот" to 800, "семьсот" to 700, "шестьсот" to 600,
            "пятьсот" to 500, "четыреста" to 400, "триста" to 300, "двести" to 200,
            "сто" to 100, "девяносто" to 90, "восемьдесят" to 80, "семьдесят" to 70,
            "шестьдесят" to 60, "пятьдесят" to 50, "сорок" to 40, "тридцать" to 30,
            "двадцать" to 20, "девятнадцать" to 19, "восемнадцать" to 18, "семнадцать" to 17,
            "шестнадцать" to 16, "пятнадцать" to 15, "четырнадцать" to 14, "тринадцать" to 13,
            "двенадцать" to 12, "одиннадцать" to 11, "десять" to 10, "девять" to 9,
            "восемь" to 8, "семь" to 7, "шесть" to 6, "пять" to 5,
            "четыре" to 4, "три" to 3, "два" to 2, "один" to 1)
            .entries
            .map { e -> e.value to e.key }
            .toMap()
    var hundreds = n % 1000
    val answer = StringBuilder()
    if (n == 0) return "ноль"
    var thousands = n / 1000
    if (thousands != 0) {
        if (thousands in 100..999) {
            answer.append(arabic[(thousands / 100) * 100] + " ")
            thousands %= 100
        }
        if (thousands in 20..99) {
            answer.append(arabic[(thousands / 10) * 10] + " ")
            thousands %= 10
        }
        if (thousands in 0..19) {
            when (thousands) {
                0 -> answer.append("тысяч" + " ")
                1 -> answer.append("одна тысяча" + " ")
                2 -> answer.append("две тысячи" + " ")
                3 -> answer.append("три тысячи" + " ")
                4 -> answer.append("четыре тысячи" + " ")
                else -> answer.append(arabic[thousands] + " " + "тысяч" + " ")
            }
        }
    }
    if (hundreds in 100..999) {
        answer.append(arabic[(hundreds / 100) * 100] + " ")
        hundreds %= 100
    }
    if (hundreds in 20..99) {
        answer.append(arabic[(hundreds / 10) * 10] + " ")
        hundreds %= 10
    }
    if (hundreds in 1..19) {
        answer.append(arabic[hundreds])
    }
    return answer.trim().toString()
}