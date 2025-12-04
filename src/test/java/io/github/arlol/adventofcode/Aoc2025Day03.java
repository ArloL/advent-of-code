package io.github.arlol.adventofcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Aoc2025Day03 {

	@ParameterizedTest
	@MethodSource("provideArgumentsForPuzzle1")
	void puzzle1Test(long expected, String input) throws Exception {
		var actual = puzzle1(input);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForPuzzle1() {
		return Stream.of(
				Arguments.of(45, "12345"),
				Arguments.of(98, "987654321111111"),
				Arguments.of(89, "811111111111119"),
				Arguments.of(78, "234234234234278"),
				Arguments.of(92, "818181911112111")
		);
	}

	@Test
	void puzzle1Example() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day03-example.txt")
		);
		assertThat(actual).isEqualTo(357);
	}

	@Test
	void puzzle1() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day03-input.txt")
		);
		System.out.println("Day 3 Puzzle 1: " + actual);
		assertThat(actual).isEqualTo(17113);
	}

	private long puzzle1(String input) {
		return input.lines().mapToLong(line -> {
			var listOfIndividualNumbers = line.chars()
					.mapToObj(i -> Character.toString((char) i))
					.map(Long::parseLong)
					.toList();
			var greatestLongWithNumberOfDecimals = findGreatestLongWithNumberOfDecimals(
					listOfIndividualNumbers,
					0,
					1
			);
			return Long.parseLong(greatestLongWithNumberOfDecimals);
		}).sum();
	}

	@ParameterizedTest
	@MethodSource("provideArgumentsForPuzzle2")
	void puzzle2Test(long expected, String input) throws Exception {
		var actual = puzzle2(input);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForPuzzle2() {
		return Stream.of(
				Arguments.of(987654321111L, "987654321111111"),
				Arguments.of(811111111119L, "811111111111119"),
				Arguments.of(434234234278L, "234234234234278"),
				Arguments.of(888911112111L, "818181911112111")
		);
	}

	@Test
	void puzzle2Example() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day03-example.txt")
		);
		assertThat(actual).isEqualTo(3121910778619L);
	}

	@Test
	void puzzle2() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day03-input.txt")
		);
		System.out.println("Day 3 Puzzle 2: " + actual);
		assertThat(actual).isEqualTo(169709990062889L);
	}

	private long puzzle2(String input) {
		return input.lines().mapToLong(line -> {
			var listOfIndividualNumbers = line.chars()
					.mapToObj(i -> Character.toString((char) i))
					.map(Long::parseLong)
					.toList();
			var greatestLongWithNumberOfDecimals = findGreatestLongWithNumberOfDecimals(
					listOfIndividualNumbers,
					0,
					11
			);
			return Long.parseLong(greatestLongWithNumberOfDecimals);
		}).sum();
	}

	private String findGreatestLongWithNumberOfDecimals(
			List<Long> list,
			int start,
			int remaining
	) {
		if (remaining == -1) {
			return "";
		}
		var partOfListToConsider = list.subList(start, list.size() - remaining);
		long greatestLongInPartOfList = partOfListToConsider.stream()
				.reduce(Long::max)
				.orElseThrow();
		int indexOfMax = partOfListToConsider.indexOf(greatestLongInPartOfList);
		var greatestLongInRestOfList = findGreatestLongWithNumberOfDecimals(
				list,
				start + indexOfMax + 1,
				remaining - 1
		);
		return greatestLongInPartOfList + greatestLongInRestOfList;
	}

}
