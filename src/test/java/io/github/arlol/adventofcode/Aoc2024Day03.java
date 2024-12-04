package io.github.arlol.adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class Aoc2024Day03 {

	@Test
	void dayThreePuzzleOneExample() throws Exception {
		int actual = dayThreePuzzleOne(
				ClassPathFiles.readString("aoc2024day03-example.txt")
		);
		assertEquals(161, actual);
	}

	@Test
	void dayThreePuzzleOne() throws Exception {
		int dayThreePuzzleOne = dayThreePuzzleOne(
				ClassPathFiles.readString("aoc2024day03-input.txt")
		);
		System.out.println("Day Three Puzzle One: " + dayThreePuzzleOne);
	}

	private int dayThreePuzzleOne(String input) {
		Pattern compile = Pattern.compile("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)");
		Matcher matcher = compile.matcher(input);
		return matcher.results().mapToInt(mul -> {
			return Integer.parseInt(mul.group(1))
					* Integer.parseInt(mul.group(2));
		}).sum();
	}

	@Test
	void dayThreePuzzleTwoExample() throws Exception {
		int actual = dayThreePuzzleTwo(
				ClassPathFiles.readString("aoc2024day03-example2.txt")
		);
		assertEquals(48, actual);
	}

	@Test
	void dayThreePuzzleTwo() throws Exception {
		int dayThreePuzzleTwo = dayThreePuzzleTwo(
				ClassPathFiles.readString("aoc2024day03-input.txt")
		);
		System.out.println("Day Three Puzzle Two: " + dayThreePuzzleTwo);
	}

	private int dayThreePuzzleTwo(String input) {
		Pattern compile = Pattern.compile(
				"don't\\(\\)|do\\(\\)|mul\\(([0-9]{1,3}),([0-9]{1,3})\\)"
		);
		Matcher matcher = compile.matcher(input);
		int sum = 0;
		boolean enabled = true;
		for (var match : matcher.results().toList()) {
			switch (match.group()) {
			case "do()" -> enabled = true;
			case "don't()" -> enabled = false;
			default -> {
				if (enabled) {
					sum += Integer.parseInt(match.group(1))
							* Integer.parseInt(match.group(2));
				}
			}
			}
		}
		return sum;
	}

}
