package io.github.arlol.adventofcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Aoc2025DayXXX {

	@ParameterizedTest
	@MethodSource("provideArgumentsForPuzzle1")
	void puzzle1Test(long expected, String input) throws Exception {
		var actual = puzzle1(input);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForPuzzle1() {
		return Stream.of();
	}

	@Test
	void puzzle1Example() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025dayXXX-example.txt")
		);
		assertThat(actual).isEqualTo(0L);
	}

	@Test
	void puzzle1() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025dayXXX-input.txt")
		);
		System.out.println("Day XXX Puzzle 1: " + actual);
		assertThat(actual).isEqualTo(0L);
	}

	private long puzzle1(String input) {
		return 0L;
	}

	@ParameterizedTest
	@MethodSource("provideArgumentsForPuzzle2")
	void puzzle2Test(long expected, String input) throws Exception {
		var actual = puzzle2(input);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForPuzzle2() {
		return Stream.of();
	}

	@Test
	void puzzle2Example() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day0XXX-example.txt")
		);
		assertThat(actual).isEqualTo(0L);
	}

	@Test
	void puzzle2() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025dayXXX-input.txt")
		);
		System.out.println("Day XXX Puzzle 2: " + actual);
		assertThat(actual).isEqualTo(0L);
	}

	private long puzzle2(String input) {
		return 0L;
	}

}
