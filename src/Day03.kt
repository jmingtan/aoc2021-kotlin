fun main() {
    fun part1(input: List<String>): Int {
        val count0 = mutableListOf<Int>()
        val count1 = mutableListOf<Int>()
        for (line in input) {
            for (i in 0..line.length - 1) {
                val c = line[i]
                if (c == '0') {
                    if (count0.size < i + 1) {
                        count0.add(1)
                    } else {
                        count0[i] += 1
                    }
                } else if (c == '1') {
                    if (count1.size < i + 1) {
                        count1.add(1)
                    } else {
                        count1[i] += 1
                    }
                }
            }
        }
        // println(count0)
        // println(count1)
        val gamma = mutableListOf<Int>()
        val epsilon = mutableListOf<Int>()
        for (i in 0..count0.size - 1) {
            if (count0[i] > count1[i]) {
                gamma.add(0)
                epsilon.add(1)
            } else {
                gamma.add(1)
                epsilon.add(0)
            }
        }
        val gammaVal = Integer.parseInt(gamma.joinToString(""), 2)
        val epsilonVal = Integer.parseInt(epsilon.joinToString(""), 2)
        return gammaVal * epsilonVal
    }

    fun part2(input: List<String>): Int {
        var oxygenFound = false
        var oxygenSearch = mutableListOf<Int>()
        var co2Found = false
        var co2Search = mutableListOf<Int>()
        var initial = true
        var pos = 0

        while (!oxygenFound || !co2Found) {
            val count0 = mutableListOf<Int>()
            val count1 = mutableListOf<Int>()
            for (i in 0..input.size - 1) {
                val c = input[i][pos]
                if (c == '0') {
                    count0.add(i)
                } else if (c == '1') {
                    count1.add(i)
                }
            }

            if (count0.size > count1.size) {
                // count0 > count1: count0 is oxygen candidate, count1 is co2 candidate
                if (!oxygenFound) {
                    val newList = mutableListOf<Int>()
                    for (index in count0) {
                        if (initial) {
                            newList.add(index)
                            continue
                        }

                        if (oxygenSearch.contains(index)) {
                            newList.add(index)
                        }
                    }
                    oxygenSearch = newList
                }
                if (!co2Found) {
                    val newList = mutableListOf<Int>()
                    for (index in count1) {
                        if (initial) {
                            newList.add(index)
                            continue
                        }

                        if (co2Search.contains(index)) {
                            newList.add(index)
                        }
                    }
                    co2Search = newList
                }
            } else {
                // count0 <= count1: count1 is oxygen candidate, count0 is co2 candidate
                if (!oxygenFound) {
                    val newList = mutableListOf<Int>()
                    for (index in count1) {
                        if (initial) {
                            newList.add(index)
                            continue
                        }

                        if (oxygenSearch.contains(index)) {
                            newList.add(index)
                        }
                    }
                    oxygenSearch = newList
                }
                if (!co2Found) {
                    val newList = mutableListOf<Int>()
                    for (index in count0) {
                        if (initial) {
                            newList.add(index)
                            continue
                        }

                        if (co2Search.contains(index)) {
                            newList.add(index)
                        }
                    }
                    co2Search = newList
                }
            }

            if (co2Search.size == 1) {
                co2Found = true
            }

            if (oxygenSearch.size == 1) {
                oxygenFound = true
            }

            println("pos: $pos oxygenSearch: $oxygenSearch co2Search: $co2Search count0>count1: ${count0.size > count1.size} count0: $count0 count1: $count1")
            initial = false
            pos++
        }

        val oxygenVal = Integer.parseInt(input[oxygenSearch[0]], 2)
        val co2Val = Integer.parseInt(input[co2Search[0]], 2)
        println("oxygenSearch: $oxygenSearch co2Search: $co2Search")
        println("oxygenVal: $oxygenVal co2Val: $co2Val")
        return oxygenVal * co2Val
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
//    println(part2(input))
}
