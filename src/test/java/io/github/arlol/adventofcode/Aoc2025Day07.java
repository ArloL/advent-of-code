package io.github.arlol.adventofcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Aoc2025Day07 {

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
				ClassPathFiles.readString("aoc2025day07-example.txt")
		);
		assertThat(actual).isEqualTo(21L);
	}

	@Test
	void puzzle1() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day07-input.txt")
		);
		System.out.println("Day 07 Puzzle 1: " + actual);
		assertThat(actual).isEqualTo(0L);
	}

	private record PuzzleOne(
			List<List<String>> rows
	) {

		PuzzleOne(String input) {
			this(
					input.lines()
							.map(
									line -> line.chars()
											.mapToObj(
													i -> Character
															.toString((char) i)
											)
											.collect(Collectors.toList())
							)
							.collect(Collectors.toList())
			);
		}

		long goThrough() {
			var splitCount = 0L;
			for (int i = 1; i < rows.size(); i++) {
				var previousRow = rows.get(i - 1);
				var row = rows.get(i);
				for (int j = 0; j < previousRow.size(); j++) {
					var previousCell = previousRow.get(j);
					var cell = row.get(j);
					switch (previousCell) {
					case "S" -> row.set(j, "|");
					case "|" -> {
						switch (cell) {
						case "." -> {
							row.set(j, "|");
						}
						case "^" -> {
							if (row.get(j - 1).equals(".")) {
								row.set(j - 1, "|");
							}
							row.set(j, "X");
							if (row.get(j + 1).equals(".")) {
								row.set(j + 1, "|");
							}
							splitCount++;
						}
						}
					}
					}
				}
			}
			return splitCount;
		}

		void print() {
			for (var row : rows) {
				for (var cell : row) {
					System.out.print(cell);
				}
				System.out.println();
			}
		}

	}

	private long puzzle1(String input) {
		var puzzleOne = new PuzzleOne(input);
		long goThrough = puzzleOne.goThrough();
		puzzleOne.print();
		return goThrough;
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
				ClassPathFiles.readString("aoc2025day07-example.txt")
		);
		assertThat(actual).isEqualTo(0L);
	}

	@Test
	void puzzle2() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day07-input.txt")
		);
		System.out.println("Day 07 Puzzle 2: " + actual);
		assertThat(actual).isEqualTo(0L);
	}

	private long puzzle2(String input) {
		return 0L;
	}

}
