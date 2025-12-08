package io.github.arlol.adventofcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
			List<List<String>> rows,
			int start,
			int width,
			Map<String, Long> cache
	) {

		PuzzleOne(String input) {
			var rows = input.lines()
					.map(
							line -> line.codePoints()
									.mapToObj(Character::toString)
									.collect(Collectors.toList())
					)
					.collect(Collectors.toList());
			int start = rows.get(0).indexOf("S");
			this(rows, start, rows.get(0).size(), new HashMap<>());
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
			startBeam();
			return countTimelines(2, start);
		}

		private long countTimelines(int depth, int index) {
			String key = "" + depth + index;
			Long cached = cache.get(key);
			if (cached != null) {
				return cached;
			}
			if (depth >= rows.size()) {
				cache.put(key, 1L);
				return 1;
			}
			if (index < 0 || index >= width) {
				cache.put(key, 0L);
				return 0;
			}
			if ("|".equals(rows.get(depth).get(index))) {
				long numberOfTimelines = countTimelines(depth + 2, index);
				cache.put(key, numberOfTimelines);
				return numberOfTimelines;
			} else {
				long numberOfTimelines = countTimelines(depth + 2, index - 1)
						+ countTimelines(depth + 2, index + 1);
				cache.put(key, numberOfTimelines);
				return numberOfTimelines;
			}
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
		return Stream.of(Arguments.of(4, """
				.......S.......
				...............
				.......^.......
				...............
				......^.^......
				...............
				"""), Arguments.of(8, """
				.......S.......
				...............
				.......^.......
				...............
				......^.^......
				...............
				.....^.^.^.....
				...............
				"""));
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
		assertThat(actual).isEqualTo(62943905501815L);
	}

	private long puzzle2(String input) {
		var puzzleOne = new PuzzleOne(input);
		long countTimelines = puzzleOne.countTimelines();
		puzzleOne.print();
		return countTimelines;
	}

}
