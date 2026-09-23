package io.github.arlol.adventofcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Aoc2025Day10 {

	@Test
	void puzzle1Example() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day10-example.txt")
		);
		assertThat(actual).isEqualTo(7L);
	}

	@Test
	void puzzle1() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day10-input.txt")
		);
		System.out.println("Day 10 Puzzle 1: " + actual);
		assertThat(actual).isEqualTo(486L);
	}

	private long puzzle1(String input) {
		return input.lines().mapToLong(line -> {
			var lightsGoal = line
					.substring(line.indexOf("[") + 1, line.indexOf("]"))
					.codePoints()
					.mapToObj(Character::toString)
					.map(s -> s.equals(".") ? 0 : 1)
					.toList();
			var buttons = Arrays.stream(
					line.substring(line.indexOf("(") + 1, line.lastIndexOf(")"))
							.split("\\) \\(")
			)
					.map(
							button -> Arrays.stream(button.split(","))
									.map(Integer::parseInt)
									.toList()
					)
					.toList();
			var start = IntStream.range(0, lightsGoal.size())
					.mapToObj(_ -> 0)
					.toList();
			var allButtonPermutations = getAllButtonPermutations(buttons.size());
			var max = Integer.MAX_VALUE;
			for (var permutationButtons : allButtonPermutations) {
				var lightPermutation = new ArrayList<>(start);
				var count = 0;
				for (int i = 0; i < permutationButtons.length; i++) {
					if (permutationButtons[i]) {
						for (var index : buttons.get(i)) {
							lightPermutation.set(
									index,
									lightPermutation.get(index) == 0 ? 1 : 0
							);
						}
						count++;
					}
				}
				if (lightPermutation.equals(lightsGoal)) {
					max = Math.min(count, max);
				}
			}

			return max;
		}).sum();
	}

	public boolean[][] getAllButtonPermutations(int n) {
		int combinations = 1 << n; // 2^n
		boolean[][] result = new boolean[combinations][n];
		for (int i = 0; i < combinations; i++) {
			for (int j = 0; j < n; j++) {
				result[i][j] = (i >> n - 1 - j & 1) == 1;
			}
		}
		return result;
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
				ClassPathFiles.readString("aoc2025day10-example.txt")
		);
		assertThat(actual).isEqualTo(33L);
	}

	@Test
	void puzzle2() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day10-input.txt")
		);
		System.out.println("Day 10 Puzzle 2: " + actual);
		assertThat(actual).isEqualTo(0L);
	}

	private long puzzle2(String input) {
		return input.lines().mapToLong(line -> {
			var lightsGoal = line
					.substring(line.indexOf("[") + 1, line.indexOf("]"))
					.codePoints()
					.mapToObj(Character::toString)
					.map(s -> s.equals(".") ? 0 : 1)
					.toList();
			var buttons = Arrays.stream(
					line.substring(line.indexOf("(") + 1, line.lastIndexOf(")"))
							.split("\\) \\(")
			)
					.map(
							button -> Arrays.stream(button.split(","))
									.map(Integer::parseInt)
									.toList()
					)
					.toList();
			var start = IntStream.range(0, lightsGoal.size())
					.mapToObj(_ -> 0)
					.toList();
			var allButtonPermutations = getAllButtonPermutations(buttons.size());
			var max = Integer.MAX_VALUE;
			for (var permutationButtons : allButtonPermutations) {
				var lightPermutation = new ArrayList<>(start);
				var count = 0;
				for (int i = 0; i < permutationButtons.length; i++) {
					if (permutationButtons[i]) {
						for (var index : buttons.get(i)) {
							lightPermutation.set(
									index,
									lightPermutation.get(index) == 0 ? 1 : 0
							);
						}
						count++;
					}
				}
				if (lightPermutation.equals(lightsGoal)) {
					max = Math.min(count, max);
				}
			}

			var joltage = Arrays.stream(
					line.substring(line.indexOf("{") + 1, line.indexOf("}"))
							.split(",")
			).map(Integer::parseInt).toList();
			return max;
		}).sum();
	}

}
