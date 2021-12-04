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
        var oxygenSearch: MutableList<Int>
        var co2Found = false
        var co2Search: MutableList<Int>
        var pos = 0

        fun getOccurrences(search: List<String>, indices: List<Int>, searchPos: Int, target: Char): MutableList<Int> {
            val result = mutableListOf<Int>()
            for (i in indices) {
                if (search[i][searchPos] == target) {
                    result.add(i)
                }
            }
            return result
        }

        val initialCount0 = getOccurrences(input, input.indices.toList(), pos, '0')
        val initialCount1 = getOccurrences(input, input.indices.toList(), pos, '1')
        if (initialCount0.size > initialCount1.size) {
            // count0 > count1: count0 is oxygen candidate, count1 is co2 candidate
            oxygenSearch = initialCount0
            co2Search = initialCount1
        } else {
            // count0 <= count1: count1 is oxygen candidate, count0 is co2 candidate
            oxygenSearch = initialCount1
            co2Search = initialCount0
        }

        pos = 1
        while (!oxygenFound) {
            val count0 = getOccurrences(input, oxygenSearch, pos, '0')
            val count1 = getOccurrences(input, oxygenSearch, pos, '1')
            val newList = mutableListOf<Int>()
            if (count0.size > count1.size) {
                // count0 > count1: count0 is oxygen candidate, count1 is co2 candidate
                for (index in count0) {
                    if (oxygenSearch.contains(index)) {
                        newList.add(index)
                    }
                }
            } else {
                // count0 <= count1: count1 is oxygen candidate, count0 is co2 candidate
                for (index in count1) {
                    if (oxygenSearch.contains(index)) {
                        newList.add(index)
                    }
                }
            }
            oxygenSearch = newList
            if (oxygenSearch.size == 1) {
                oxygenFound = true
            }
            pos++
        }

        pos = 1
        while (!co2Found) {
            val count0 = getOccurrences(input, co2Search, pos, '0')
            val count1 = getOccurrences(input, co2Search, pos, '1')
            val newList = mutableListOf<Int>()
            if (count0.size > count1.size) {
                // count0 > count1: count0 is oxygen candidate, count1 is co2 candidate
                for (index in count1) {
                    if (co2Search.contains(index)) {
                        newList.add(index)
                    }
                }
            } else {
                // count0 <= count1: count1 is oxygen candidate, count0 is co2 candidate
                for (index in count0) {
                    if (co2Search.contains(index)) {
                        newList.add(index)
                    }
                }
            }
            co2Search = newList
            if (co2Search.size == 1) {
                co2Found = true
            }
            pos++
        }

        val oxygenVal = Integer.parseInt(input[oxygenSearch[0]], 2)
        val co2Val = Integer.parseInt(input[co2Search[0]], 2)
        // println("oxygenSearch: $oxygenSearch co2Search: $co2Search")
        // println("oxygenVal: $oxygenVal co2Val: $co2Val")
        return oxygenVal * co2Val
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
