package io.github.arlol.adventofcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Aoc2025Day02 {

	@ParameterizedTest
	@MethodSource("provideArgumentsForPuzzle1")
	void puzzle1Test(long expected, String input) throws Exception {
		var actual = puzzle1(input);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForPuzzle1() {
		return Stream.of(
				Arguments.of(33, "11-22"),
				Arguments.of(11, "1-19"),
				Arguments.of(99, "95-115"),
				Arguments.of(1010, "998-1012"),
				Arguments.of(1188511885, "1188511880-1188511890"),
				Arguments.of(222222, "222220-222224"),
				Arguments.of(0, "1698522-1698528"),
				Arguments.of(446446, "446443-446449"),
				Arguments.of(38593859, "38593856-38593862"),
				Arguments.of(990990, "990244-990990"),
				Arguments.of(990990, "990990-990990"),
				Arguments.of(991991, "991991-991991"),
				Arguments.of(992992, "992992-992992"),
				Arguments.of(2975973, "990990-992992"),
				Arguments.of(9954945, "990990-999999"),
				Arguments.of(9954945, "990244-1009337"),
				Arguments.of(0, "5518069-5608946"),
				Arguments.of(
						34273427 + 34283428 + 34293429 + 34303430 + 34313431
								+ 34323432 + 34333433 + 34343434 + 34353435
								+ 34363436 + 34373437 + 34383438 + 34393439,
						"34273134-34397466"
				),
				Arguments.of(
						34273427 + 34283428 + 34293429 + 34303430 + 34313431
								+ 34323432 + 34333433 + 34343434 + 34353435
								+ 34363436 + 34373437 + 34383438 + 34393439,
						"3636295061-3636388848"
				)
		);
	}

	@Test
	void puzzle1Example() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day02-example.txt")
		);
		assertThat(actual).isEqualTo(1227775554L);
	}

	@Test
	void puzzle1() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day02-input.txt")
		);
		System.out.println("Day 2 Puzzle 1: " + actual);
		assertThat(actual).isEqualTo(23560874270L);
	}

	private long puzzle1(String input) {
		var sum = 0L;
		for (var range : Stream.of(input.trim().split(",")).toList()) {
			var splitRange = range.split("-");
			var rangeStart = Long.parseLong(splitRange[0]);
			var rangeEnd = Long.parseLong(splitRange[1]);
			for (; rangeStart <= rangeEnd; rangeStart++) {
				if (!isValidId1(rangeStart)) {
					sum += rangeStart;
				}
			}
		}
		return sum;
	}

	private boolean isValidId1(long lId) {
		var sId = "" + lId;
		if (sId.length() % 2 == 1) {
			return true;
		}
		var firstHalf = sId.substring(0, sId.length() / 2);
		var secondHalf = sId.substring(sId.length() / 2);
		if (secondHalf.startsWith("0")) {
			return true;
		}
		return !firstHalf.equals(secondHalf);
	}

	@ParameterizedTest
	@MethodSource("provideArgumentsForPuzzle2")
	void puzzle2Test(long expected, String input) throws Exception {
		var actual = puzzle2(input);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForPuzzle2() {
		return Stream
				.of(Arguments.of(33, "11-22"), Arguments.of(210, "95-115"));
	}

	@Test
	void puzzle2Example() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day02-example.txt")
		);
		assertThat(actual).isEqualTo(4174379265L);
	}

	@Test
	void puzzle2() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day02-input.txt")
		);
		System.out.println("Day 2 Puzzle 2: " + actual);
		assertThat(actual).isEqualTo(44143124633L);
	}

	private long puzzle2(String input) {
		var sum = 0L;
		for (var range : Stream.of(input.trim().split(",")).toList()) {
			var splitRange = range.split("-");
			var rangeStart = Long.parseLong(splitRange[0]);
			var rangeEnd = Long.parseLong(splitRange[1]);
			for (; rangeStart <= rangeEnd; rangeStart++) {
				if (!isValidId2(rangeStart)) {
					sum += rangeStart;
				}
			}
		}
		return sum;
	}

	private boolean isValidId2(long lId) {
		var sId = "" + lId;
		for (int i = 1; i <= sId.length() / 2; i++) {
			var substring = sId.substring(0, i);
			substring = substring.repeat(sId.length() / substring.length());
			if (substring.equals(sId)) {
				return false;
			}
		}
		var firstHalf = sId.substring(0, sId.length() / 2);
		var secondHalf = sId.substring(sId.length() / 2);
		if (secondHalf.startsWith("0")) {
			return true;
		}
		return !firstHalf.equals(secondHalf);
	}

}
