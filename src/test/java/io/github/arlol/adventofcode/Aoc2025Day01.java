package io.github.arlol.adventofcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Aoc2025Day01 {

	@ParameterizedTest
	@MethodSource("provideArgumentsForPuzzle1")
	void puzzle1Test(long expected, String input) throws Exception {
		var actual = puzzle1(input);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForPuzzle1() {
		return Stream.of(Arguments.of(1, """
				L55
				R5
				"""));
	}

	@Test
	void puzzle1Example() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day01-example.txt")
		);
		assertThat(actual).isEqualTo(3);
	}

	@Test
	void puzzle1() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day01-input.txt")
		);
		assertThat(actual).isEqualTo(1034);
		System.out.println("Day 1 Puzzle 1: " + actual);
	}

	private long puzzle1(String input) {
		long dial = 50;
		long zeroCount = 0;
		for (var line : input.lines().toList()) {
			var direction = line.substring(0, 1);
			var number = Integer.parseInt(line.substring(1));
			if ("R".equals(direction)) {
				dial += number;
			} else {
				dial -= number;
			}
			while (dial > 99) {
				dial -= 100;
			}
			while (dial < 0) {
				dial += 100;
			}
			if (dial == 0) {
				zeroCount++;
			}
		}
		return zeroCount;
	}

	@ParameterizedTest
	@MethodSource("provideArgumentsForPuzzle2")
	void puzzle2Test(long expected, String input) throws Exception {
		var actual = puzzle2(input);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForPuzzle2() {
		return Stream.of(
				Arguments.of(1, "L50"),
				Arguments.of(1, "R50"),
				Arguments.of(1, "L51"),
				Arguments.of(1, "R51"),
				Arguments.of(10, "R1000"),
				Arguments.of(10, "L1000"),
				Arguments.of(2, """
						L55
						R5
						"""),
				Arguments.of(1, """
						L50
						L1
						"""),
				Arguments.of(1, """
						L50
						R1
						"""),
				Arguments.of(2, """
						R150
						R1
						"""),
				Arguments.of(1, """
						L68
						"""),
				Arguments.of(1, """
						L68
						L30
						"""),
				Arguments.of(2, """
						L68
						L30
						R48
						"""),
				Arguments.of(2, """
						L68
						L30
						R48
						L5
						"""),
				Arguments.of(3, """
						L68
						L30
						R48
						L5
						R60
						""")
		);
	}

	@Test
	void puzzle2Example() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day01-example.txt")
		);
		assertThat(actual).isEqualTo(6);
	}

	@Test
	void puzzle2() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day01-input.txt")
		);

		System.out.println("Day 1 Puzzle 2: " + actual);
	}

	private long puzzle2(String input) {
		long dial = 50;
		long zeroCount = 0;
		for (var line : input.lines().toList()) {
			var direction = line.substring(0, 1);
			var number = Integer.parseInt(line.substring(1));
			while (number > 100) {
				number -= 100;
				zeroCount++;
			}
			if ("R".equals(direction)) {
				dial += number;
				if (dial > 99) {
					dial -= 100;
					if (dial != 0) {
						zeroCount++;
					}
				}
			} else {
				if (dial == 0) {
					dial -= number;
					if (dial < 0) {
						dial += 100;
					}
				} else {
					dial -= number;
					if (dial < 0) {
						dial += 100;
						if (dial != 0) {
							zeroCount++;
						}
					}
				}
			}

			if (dial == 0) {
				zeroCount++;
			}

		}
		return zeroCount;
	}

}
