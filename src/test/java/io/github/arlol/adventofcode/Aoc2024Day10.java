package io.github.arlol.adventofcode;

import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class Aoc2024Day10 {

	@Test
	void dayTenPuzzleOneExample() throws Exception {
		var actual = dayTenPuzzleOne(
				ClassPathFiles.readString("aoc2024day10-example.txt")
		);
		assertEquals(1, actual);
	}

	@Test
	void dayTenPuzzleOneExampleTwo() throws Exception {
		var actual = dayTenPuzzleOne(
				ClassPathFiles.readString("aoc2024day10-example2.txt")
		);
		assertEquals(36, actual);
	}

	@Test
	void dayTenPuzzleOne() throws Exception {
		var actual = dayTenPuzzleOne(
				ClassPathFiles.readString("aoc2024day10-input.txt")
		);
		System.out.println("Day Ten Puzzle One: " + actual);
	}

	private long dayTenPuzzleOne(String input) {
		var grid = new GridOne(input);
		return grid.sumOfTrailheadScores();
	}

	private static class GridOne {

		List<List<Integer>> grid;
		int height;
		int width;

		public GridOne(String input) {
			grid = input.lines()
					.map(line -> line.chars().mapToObj(x -> x - 48).toList())
					.toList();
			height = grid.size();
			width = grid.getFirst().size();
		}

		long sumOfTrailheadScores() {
			long sum = 0;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					sum += findWayToTop(x, y, 0).size();
				}
			}
			return sum;
		}

		Set<String> findWayToTop(int x, int y, int nextHeight) {
			if (x < 0 || y < 0 || x >= width || y >= height) {
				return emptySet();
			}
			if (grid.get(y).get(x) != nextHeight) {
				return emptySet();
			}
			if (nextHeight == 9) {
				return Set.of(x + "x" + y);
			}
			var result = new HashSet<String>();
			result.addAll(findWayToTop(x - 1, y, nextHeight + 1));
			result.addAll(findWayToTop(x + 1, y, nextHeight + 1));
			result.addAll(findWayToTop(x, y - 1, nextHeight + 1));
			result.addAll(findWayToTop(x, y + 1, nextHeight + 1));
			return result;

		}

	}

	@Test
	void dayTenPuzzleTwoExample() throws Exception {
		var actual = dayTenPuzzleTwo(
				ClassPathFiles.readString("aoc2024day10-example2.txt")
		);
		assertEquals(81, actual);
	}

	@Test
	void dayTenPuzzleTwo() throws Exception {
		var actual = dayTenPuzzleTwo(
				ClassPathFiles.readString("aoc2024day10-input.txt")
		);
		System.out.println("Day Ten Puzzle Two: " + actual);
	}

	private long dayTenPuzzleTwo(String input) {
		return new GridTwo(input).sumOfTrailheadRatings();
	}

	private static class GridTwo {

		List<List<Integer>> grid;
		int height;
		int width;

		public GridTwo(String input) {
			grid = input.lines()
					.map(line -> line.chars().mapToObj(x -> x - 48).toList())
					.toList();
			height = grid.size();
			width = grid.getFirst().size();
		}

		long sumOfTrailheadRatings() {
			long sum = 0;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					sum += findWaysToTop(x, y, 0);
				}
			}
			return sum;
		}

		long findWaysToTop(int x, int y, int nextHeight) {
			if (x < 0 || y < 0 || x >= width || y >= height) {
				return 0;
			}
			if (grid.get(y).get(x) != nextHeight) {
				return 0;
			}
			if (nextHeight == 9) {
				return 1;
			}
			var result = 0;
			result += findWaysToTop(x - 1, y, nextHeight + 1);
			result += findWaysToTop(x + 1, y, nextHeight + 1);
			result += findWaysToTop(x, y - 1, nextHeight + 1);
			result += findWaysToTop(x, y + 1, nextHeight + 1);
			return result;

		}

	}

}
