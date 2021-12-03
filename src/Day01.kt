fun main() {
    fun part1(input: List<String>): Int {
        var increases = 0
        var current = Integer.MIN_VALUE
        for (line in input) {
            val next = Integer.parseInt(line)
            if (current != Integer.MIN_VALUE && next > current) {
                increases++
            }
            current = next
        }
        return increases
    }

    fun part2(input: List<String>): Int {
        var increases = 0
        val window = mutableListOf(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE)
        for (line in input) {
            val next = Integer.parseInt(line)
            // println("next: " + next + " window: " + window)

            var init = false
            for (i in 0..window.size - 1) {
                if (window[i] == Integer.MIN_VALUE) {
                    window[i] = next
                    init = true
                    break
                }
            }
            if (init) continue

            val oldSum = window[0] + window[1] + window[2]
            val newSum = window[1] + window[2] + next
            if (newSum > oldSum) {
                increases++
            }
            // println("oldSum: " + oldSum + " newSum: " + newSum + " increases: " + increases)
            window[0] = window[1]
            window[1] = window[2]
            window[2] = next
        }
        return increases
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
