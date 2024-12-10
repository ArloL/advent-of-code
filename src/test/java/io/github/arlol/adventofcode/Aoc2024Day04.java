package io.github.arlol.adventofcode;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class Aoc2024Day04 {

	private enum Direction {
		UP, LEFT, DOWN, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
	}

	@Test
	void dayFourPuzzleOneExampleOne() throws Exception {
		int actual = dayFourPuzzleOne(
				ClassPathFiles.readString("aoc2024day04-example.txt")
		);
		assertEquals(4, actual);
	}

	@Test
	void dayFourPuzzleOneExampleTwo() throws Exception {
		int actual = dayFourPuzzleOne(
				ClassPathFiles.readString("aoc2024day04-example2.txt")
		);
		assertEquals(18, actual);
	}

	@Test
	void dayFourPuzzleOneExampleThree() throws Exception {
		int actual = dayFourPuzzleOne(
				ClassPathFiles.readString("aoc2024day04-example3.txt")
		);
		assertEquals(18, actual);
	}

	@Test
	void dayFourPuzzleOneExampleFour() throws Exception {
		int actual = dayFourPuzzleOne(
				ClassPathFiles.readString("aoc2024day04-example4.txt")
		);
		assertEquals(18, actual);
	}

	@Test
	void dayFourPuzzleOne() throws Exception {
		int actual = dayFourPuzzleOne(
				ClassPathFiles.readString("aoc2024day04-input.txt")
		);
		System.out.println("Day Four Puzzle One: " + actual);
	}

	private int dayFourPuzzleOne(String input) {
		var wordSearch = new WordSearchOne(input);
		// wordSearch.print();
		return wordSearch.count();
	}

	public static class WordSearchOne {

		int width;
		int height;
		String input;

		public WordSearchOne(String input) {
			var lines = input.lines().toList();
			this.width = lines.getFirst().length();
			this.height = lines.size();
			this.input = input;
		}

		String characterAt(int x, int y) {
			if (x < 0 || y < 0 || x >= width || y >= height) {
				return null;
			}
			int index = y * (width + 1) + x;
			return input.substring(index, index + 1);
		}

		void print() {
			var output = new StringBuilder();
			for (int y = 0; y < height; y++) {
				output.append(
						IntStream.range(y * width, y * width + width)
								.mapToObj("%02d"::formatted)
								.collect(joining(","))
				);
				output.append("\n");
				for (int x = 0; x < width; x++) {
					output.append(" ");
					output.append(characterAt(x, y));
					output.append(",");
				}
				output.append("\n");
			}
			System.out.println(output.toString());
		}

		boolean characterInDirectionMatches(
				Direction direction,
				String input,
				int x,
				int y,
				String lookforCharacter
		) {
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
			if (lookforCharacter.equals(characterAt(x, y))) {
				if ("S".equals(lookforCharacter)) {
					return true;
				}
				return characterInDirectionMatches(
						direction,
						input,
						x,
						y,
						lookforCharacter.equals("A") ? "S" : "A"
				);
			}
			return false;
		}

		int count() {
			int count = 0;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					if ("X".equals(characterAt(x, y))) {
						for (Direction direction : Direction.values()) {
							if (characterInDirectionMatches(
									direction,
									input,
									x,
									y,
									"M"
							)) {
								count++;
							}
						}
					}
				}
			}
			return count;
		}

	}

	@Test
	void dayFourPuzzleTwoExample() throws Exception {
		int actual = dayFourPuzzleTwo(
				ClassPathFiles.readString("aoc2024day04-puzzle2-example.txt")
		);
		assertEquals(9, actual);
	}

	@Test
	void dayFourPuzzleTwo() throws Exception {
		int dayFourPuzzleTwo = dayFourPuzzleTwo(
				ClassPathFiles.readString("aoc2024day04-input.txt")
		);
		System.out.println("Day Four Puzzle Two: " + dayFourPuzzleTwo);
	}

	private int dayFourPuzzleTwo(String input) {
		var wordSearch = new WordSearchTwo(input);
		// wordSearch.print();
		return wordSearch.count();
	}

	public static class WordSearchTwo {

		int width;
		int height;
		String input;

		public WordSearchTwo(String input) {
			var lines = input.lines().toList();
			this.width = lines.getFirst().length();
			this.height = lines.size();
			this.input = input;
		}

		String characterAt(int x, int y) {
			if (x < 0 || y < 0 || x >= width || y >= height) {
				return null;
			}
			int index = y * (width + 1) + x;
			return input.substring(index, index + 1);
		}

		void print() {
			var output = new StringBuilder();
			for (int y = 0; y < height; y++) {
				output.append(
						IntStream.range(y * width, y * width + width)
								.mapToObj("%02d"::formatted)
								.collect(joining(","))
				);
				output.append("\n");
				for (int x = 0; x < width; x++) {
					output.append(" ");
					output.append(characterAt(x, y));
					output.append(",");
				}
				output.append("\n");
			}
			System.out.println(output.toString());
		}

		boolean characterInDirectionMatches(
				Direction direction,
				int x,
				int y,
				String lookforCharacter
		) {
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
			return lookforCharacter.equals(characterAt(x, y));
		}

		int count() {
			int count = 0;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					if ("A".equals(characterAt(x, y))) {
						boolean mUpLeft = characterInDirectionMatches(
								Direction.UP_LEFT,
								x,
								y,
								"M"
						);
						boolean mUpRight = characterInDirectionMatches(
								Direction.UP_RIGHT,
								x,
								y,
								"M"
						);
						boolean sDownLeft = characterInDirectionMatches(
								Direction.DOWN_LEFT,
								x,
								y,
								"S"
						);
						boolean sDownRight = characterInDirectionMatches(
								Direction.DOWN_RIGHT,
								x,
								y,
								"S"
						);
						boolean sUpLeft = characterInDirectionMatches(
								Direction.UP_LEFT,
								x,
								y,
								"S"
						);
						boolean sUpRight = characterInDirectionMatches(
								Direction.UP_RIGHT,
								x,
								y,
								"S"
						);

						boolean mDownLeft = characterInDirectionMatches(
								Direction.DOWN_LEFT,
								x,
								y,
								"M"
						);
						boolean mDownRight = characterInDirectionMatches(
								Direction.DOWN_RIGHT,
								x,
								y,
								"M"
						);

						if (mUpLeft && mUpRight && sDownRight && sDownLeft) {
							count++;
						}
						if (sUpLeft && mUpRight && mDownRight && sDownLeft) {
							count++;
						}
						if (sUpLeft && sUpRight && mDownRight && mDownLeft) {
							count++;
						}
						if (mUpLeft && sUpRight && sDownRight && mDownLeft) {
							count++;
						}
					}
				}
			}
			return count;
		}

	}

}
