package io.github.arlol.adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class Aoc2024Day02 {

	@Test
	void dayTwoPuzzleOneExample() throws Exception {
		int actual = dayTwoPuzzleOne(
				ClassPathFiles.readString("aoc2024day02-example.txt")
		);
		assertEquals(2, actual);
	}

	@Test
	void dayTwoPuzzleOneExampleTwo() throws Exception {
		int actual = dayTwoPuzzleOne("7 6 4 2 1");
		assertEquals(1, actual);
	}

	@Test
	void dayTwoPuzzleOne() throws Exception {
		int dayTwoPuzzleOne = dayTwoPuzzleOne(
				ClassPathFiles.readString("aoc2024day02-input.txt")
		);
		System.out.println("Day Two Puzzle One: " + dayTwoPuzzleOne);
	}

	private int dayTwoPuzzleOne(String input) {
		int sum = 0;
		for (String report : input.lines().toList()) {
			var levels = Stream.of(report.split("\s+"))
					.map(Integer::valueOf)
					.toList();
			if (extracted(levels)) {
				sum++;
			}
		}
		return sum;
	}

	@Test
	void dayTwoPuzzleTwoExample() throws Exception {
		int actual = dayTwoPuzzleTwo(
				ClassPathFiles.readString("aoc2024day02-example.txt")
		);
		assertEquals(4, actual);
	}

	@Test
	void dayTwoPuzzleTwoExampleTwo() throws Exception {
		int actual = dayTwoPuzzleTwo("1 3 2 4 5");
		assertEquals(1, actual);
	}

	@Test
	void dayTwoPuzzleTwo() throws Exception {
		int dayTwoPuzzleTwo = dayTwoPuzzleTwo(
				ClassPathFiles.readString("aoc2024day02-input.txt")
		);
		System.out.println("Day Two Puzzle Two: " + dayTwoPuzzleTwo);
	}

	private int dayTwoPuzzleTwo(String input) {
		String[] reports = input.split("\r?\n");
		int sum = 0;
		for (String report : reports) {
			var levels = Stream.of(report.split("\s+"))
					.map(Integer::valueOf)
					.toList();
			for (int i = -1; i < levels.size(); i++) {
				List<Integer> reducedLevels = new ArrayList<>(levels);
				if (i > -1) {
					reducedLevels.remove(i);
				}
				if (extracted(reducedLevels)) {
					sum++;
					break;
				}
			}
		}
		return sum;
	}

	private boolean extracted(List<Integer> levels) {
		int lastDiff = 0;
		for (int i = 0; i < levels.size() - 1; i++) {
			int diff = levels.get(i) - levels.get(i + 1);
			if (lastDiff == 0) {
				lastDiff = diff;
			}
			if (Math.signum(lastDiff) != Math.signum(diff)) {
				return false;
			}
			int abs = Math.abs(diff);
			if (abs < 1 || abs > 3) {
				return false;
			}
			lastDiff = diff;
		}
		return true;
	}

}
