package io.github.arlol.adventofcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongBinaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Aoc2025Day06 {

	@ParameterizedTest
	@MethodSource("provideArgumentsForPuzzle1")
	void puzzle1Test(long expected, String input) throws Exception {
		var actual = puzzle1(input);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForPuzzle1() {
		return Stream.of(Arguments.of(33210, """
				123
				45
				6
				*
				"""));
	}

	@Test
	void puzzle1Example() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day06-example.txt")
		);
		assertThat(actual).isEqualTo(4277556L);
	}

	@Test
	void puzzle1() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day06-input.txt")
		);
		System.out.println("Day 06 Puzzle 1: " + actual);
		assertThat(actual).isEqualTo(3261038365331L);
	}

	private long puzzle1(String input) {
		var lists = new ArrayList<>(input.lines().map(line -> {
			return Arrays.asList(line.trim().split("\s+"));
		}).toList());
		var operators = lists.removeLast();
		var longlists = lists.stream()
				.map(list -> list.stream().map(Long::parseLong).toList())
				.toList();
		var sum = 0L;
		for (int i = 0; i < lists.get(0).size(); i++) {
			LongBinaryOperator reducer = switch (operators.get(i)) {
			case "+" -> (left, right) -> left + right;
			case "*" -> (left, right) -> left * right;
			default -> (_, _) -> 0;
			};
			var index = i;
			sum += longlists.stream()
					.mapToLong(list -> list.get(index))
					.reduce(reducer)
					.getAsLong();
		}
		return sum;
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
				ClassPathFiles.readString("aoc2025day06-example.txt")
		);
		assertThat(actual).isEqualTo(3263827L);
	}

	@Test
	void puzzle2() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day06-input.txt")
		);
		System.out.println("Day 06 Puzzle 2: " + actual);
		assertThat(actual).isEqualTo(8342588849093L);
	}

	private long puzzle2(String input) {
		var sum = 0L;
		var lines = input.lines().toList();
		int height = lines.size();
		int width = lines.getFirst().length();
		List<Long> numbers = new ArrayList<>();
		for (int iw = width - 1; iw >= 0; iw--) {
			var charactersSoFar = "";
			for (int ih = 0; ih < height; ih++) {
				var index = ih * width + iw + ih;
				var character = input.substring(index, index + 1);
				if ("".equals(character) || " ".equals(character)
						|| "+".equals(character) || "*".equals(character)) {
					if (!charactersSoFar.isBlank()) {
						numbers.add(Long.parseLong(charactersSoFar));
						charactersSoFar = "";
					}
				} else {
					charactersSoFar += character;
				}
				if ("+".equals(character) || "*".equals(character)) {
					LongBinaryOperator operator = switch (character) {
					case "+" -> (l, r) -> l + r;
					case "*" -> (l, r) -> l * r;
					default -> null;
					};
					sum += numbers.stream()
							.mapToLong(l -> l)
							.reduce(operator)
							.orElse(0);
					numbers = new ArrayList<>();
				}
			}
		}

		return sum;
	}

}
