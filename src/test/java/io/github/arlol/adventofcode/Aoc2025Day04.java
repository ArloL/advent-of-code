package io.github.arlol.adventofcode;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class Aoc2025Day04 {

	private enum Direction {
		UP, LEFT, DOWN, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
	}

	private static class Grid {

		List<List<String>> lines;
		int width;
		int height;

		public Grid(String input) {
			this.lines = input.lines()
					.map(
							line -> (List<String>) new ArrayList<>(
									line.chars()
											.mapToObj(
													i -> Character
															.toString((char) i)
											)
											.toList()
							)
					)
					.toList();
			this.height = lines.size();
			this.width = lines.get(0).size();
		}

		boolean paperAt(int x, int y) {
			if (x < 0 || y < 0 || x >= width || y >= height) {
				return false;
			}
			return lines.get(x).get(y).equals("@");
		}

		void removeCharacterAt(int x, int y) {
			lines.get(x).set(y, "x");
		}

		@SuppressWarnings("unused")
		void print() {
			var output = new StringBuilder();
			for (int y = 0; y < lines.size(); y++) {
				output.append(
						IntStream.range(y * width, y * width + width)
								.mapToObj("%02d"::formatted)
								.collect(joining(","))
				);
				output.append("\n");
				for (String cell : lines.get(y)) {
					output.append(" ");
					output.append(cell);
					output.append(",");
				}
				output.append("\n");
			}
			System.out.println(output.toString());
		}

		boolean isPaperInDirection(Direction direction, int x, int y) {
			switch (direction) {
			case DOWN:
				y++;
				break;
			case DOWN_LEFT:
				x--;
				y++;
				break;
			case DOWN_RIGHT:
				x++;
				y++;
				break;
			case LEFT:
				x--;
				break;
			case RIGHT:
				x++;
				break;
			case UP:
				y--;
				break;
			case UP_LEFT:
				x--;
				y--;
				break;
			case UP_RIGHT:
				x++;
				y--;
				break;
			default:
				break;
			}
			return paperAt(x, y);
		}

		long collectPaper() {
			long collectedPaperCount = 0L;
			List<Entry<Integer, Integer>> collectablePaper = new ArrayList<>();
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					if (paperAt(x, y)) {
						int surroundingPaperCount = 0;
						for (Direction direction : Direction.values()) {
							if (isPaperInDirection(direction, x, y)) {
								surroundingPaperCount++;
							}
						}
						if (surroundingPaperCount < 4) {
							collectablePaper.add(Map.entry(x, y));
							collectedPaperCount++;
						}
					}
				}
			}
			for (var entry : collectablePaper) {
				removeCharacterAt(entry.getKey(), entry.getValue());
			}
			return collectedPaperCount;
		}

	}

	@Test
	void puzzle1Example() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day04-example.txt")
		);
		assertThat(actual).isEqualTo(13);
	}

	@Test
	void puzzle1() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day04-input.txt")
		);
		System.out.println("Day 4 Puzzle 1: " + actual);
		assertThat(actual).isEqualTo(1491);
	}

	private long puzzle1(String input) {
		var grid = new Grid(input);
		return grid.collectPaper();
	}

	@Test
	void puzzle2Example() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day04-example.txt")
		);
		assertThat(actual).isEqualTo(43);
	}

	@Test
	void puzzle2() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day04-input.txt")
		);
		System.out.println("Day 4 Puzzle 2: " + actual);
		assertThat(actual).isEqualTo(8722);
	}

	private long puzzle2(String input) {
		var grid = new Grid(input);
		var sum = 0L;
		var collectPaper = 0L;
		do {
			collectPaper = grid.collectPaper();
			sum += collectPaper;
		} while (collectPaper > 0);
		return sum;
	}

}
