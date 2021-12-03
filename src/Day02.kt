fun main() {
    fun part1(input: List<String>): Int {
        var horizontal = 0
        var depth = 0
        for (line in input) {
            val elems = line.split(" ")
            val direction = elems[0]
            val degree = Integer.parseInt(elems[1])
            if (direction == "forward") {
                horizontal += degree
            } else if (direction == "down") {
                depth += degree
            } else if (direction == "up") {
                depth -= degree
            }
        }
        return horizontal * depth
    }

    fun part2(input: List<String>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0
        for (line in input) {
            val elems = line.split(" ")
            val direction = elems[0]
            val degree = Integer.parseInt(elems[1])
            if (direction == "forward") {
                horizontal += degree
                depth += aim * degree
            } else if (direction == "down") {
                aim += degree
            } else if (direction == "up") {
                aim -= degree
            }
        }
        return horizontal * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
