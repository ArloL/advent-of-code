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
			var rows = input.lines()
					.map(
							line -> line.codePoints()
									.mapToObj(Character::toString)
									.collect(Collectors.toList())
					)
					.collect(Collectors.toList());
			this(rows);
		}

		long startBeam() {
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

		long countTimelines() {
			int start = rows.get(1).indexOf("|");
			return blarg(2, start);
		}

		private long blarg(int i, int start) {
			if (i >= rows.size()) {
				return 1;
			}
			var row = rows.get(i);
			if (start < 0 || start >= row.size()) {
				return 0;
			}
			var cell = row.get(start);
			if ("|".equals(cell)) {
				return blarg(i + 1, start);
			} else if ("X".equals(cell)) {
				return blarg(i + 1, start - 1) + blarg(i + 1, start + 1);
			}
			return 0;
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
		long goThrough = puzzleOne.startBeam();
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
		assertThat(actual).isEqualTo(40L);
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
		var puzzleOne = new PuzzleOne(input);
		puzzleOne.startBeam();
		puzzleOne.print();
		return puzzleOne.countTimelines();
	}

}
