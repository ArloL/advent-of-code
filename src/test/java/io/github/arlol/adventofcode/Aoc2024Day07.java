package io.github.arlol.adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class Aoc2024Day07 {

	@Test
	void daySevenPuzzleOneExample() throws Exception {
		var actual = daySevenPuzzleOne(
				ClassPathFiles.readString("aoc2024day07-example.txt")
		);
		assertEquals(3749, actual);
	}

	@Test
	void daySevenPuzzleOne() throws Exception {
		var actual = daySevenPuzzleOne(
				ClassPathFiles.readString("aoc2024day07-input.txt")
		);
		System.out.println("Day Seven Puzzle One: " + actual);
	}

	private long daySevenPuzzleOne(String input) {
		long sum = 0;
		for (var line : input.lines().toList()) {
			int indexOfColon = line.indexOf(":");
			var result = Long.parseLong(line.substring(0, indexOfColon));
			var numbers = Stream
					.of(line.substring(indexOfColon + 2).split("\s+"))
					.map(Long::parseLong)
					.toList();
			if (calc(result, 0, numbers)) {
				sum += result;
			}
		}
		return sum;
	}

	private boolean calc(long expected, long current, List<Long> rest) {
		if (rest.isEmpty()) {
			return expected == current;
		}
		var int1 = rest.get(0);
		if (calc(expected, current + int1, rest.subList(1, rest.size()))) {
			return true;
		}
		return calc(expected, current * int1, rest.subList(1, rest.size()));
	}

	@Test
	void daySevenPuzzleTwoExample() throws Exception {
		var actual = daySevenPuzzleTwo(
				ClassPathFiles.readString("aoc2024day07-example.txt")
		);
		assertEquals(11387, actual);
	}

	@Test
	void daySevenPuzzleTwo() throws Exception {
		var actual = daySevenPuzzleTwo(
				ClassPathFiles.readString("aoc2024day07-input.txt")
		);
		System.out.println("Day Seven Puzzle Two: " + actual);
	}

	private long daySevenPuzzleTwo(String input) {
		long sum = 0;
		for (var line : input.lines().toList()) {
			int indexOfColon = line.indexOf(":");
			var result = Long.parseLong(line.substring(0, indexOfColon));
			var numbers = Stream
					.of(line.substring(indexOfColon + 2).split("\s+"))
					.map(Long::parseLong)
					.toList();
			if (calcTwo(result, 0, numbers)) {
				sum += result;
			}
		}
		return sum;
	}

	private boolean calcTwo(long expected, long current, List<Long> rest) {
		if (rest.isEmpty()) {
			return expected == current;
		}
		var int1 = rest.get(0);
		if (calcTwo(expected, current + int1, rest.subList(1, rest.size()))) {
			return true;
		}
		if (calcTwo(
				expected,
				Long.parseLong("" + current + int1),
				rest.subList(1, rest.size())
		)) {
			return true;
		}
		return calcTwo(expected, current * int1, rest.subList(1, rest.size()));
	}

}
