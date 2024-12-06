package io.github.arlol.adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class Aoc2024Day06 {

	@Test
	void daySixPuzzleOneExample() throws Exception {
		int actual = daySixPuzzleOne(
				ClassPathFiles.readString("aoc2024day06-example.txt")
		);
		assertEquals(41, actual);
	}

	@Test
	void daySixPuzzleOne() throws Exception {
		int dayFourPuzzleOne = daySixPuzzleOne(
				ClassPathFiles.readString("aoc2024day06-input.txt")
		);
		System.out.println("Day Six Puzzle One: " + dayFourPuzzleOne);
	}

	private int daySixPuzzleOne(String input) {
		var guardOne = new GuardOne(input);
		return guardOne.calculate();
	}

	public static enum Direction {
		UP, DOWN, RIGHT, LEFT
	}

	public static record Point(
			int x,
			int y
	) {

	}

	public static class GuardOne {

		private String input;
		private int width;
		private int height;
		private List<Point> obstructions;
		private Set<Point> visited = new HashSet<>();

		public GuardOne(String input) {
			this.input = input;
			var lines = input.lines().toList();
			this.width = lines.getFirst().length();
			this.height = lines.size();
			this.obstructions = findObstructions();
		}

		Point findStart() {
			int indexOf = input.indexOf("^");
			int x = indexOf % (width + 1);
			int y = (indexOf - x) / (width + 1);
			return new Point(x, y);
		}

		List<Point> findObstructions() {
			var result = new ArrayList<Point>();

			for (int indexOf = input.indexOf(
					"#"
			), lastIndexOf = 0; indexOf != -1; lastIndexOf = indexOf, indexOf = input
					.indexOf("#", lastIndexOf + 1)) {
				int x = indexOf % (width + 1);
				int y = (indexOf - x) / (width + 1);
				Point point = new Point(x, y);
				result.add(point);
			}

			return result;
		}

		Point nextPoint(Point point, Direction direction) {
			return switch (direction) {
			case UP -> new Point(point.x(), point.y() - 1);
			case DOWN -> new Point(point.x(), point.y() + 1);
			case RIGHT -> new Point(point.x() + 1, point.y());
			case LEFT -> new Point(point.x() - 1, point.y());
			};
		}

		Direction nextDirection(Direction direction) {
			return switch (direction) {
			case UP -> Direction.RIGHT;
			case DOWN -> Direction.LEFT;
			case RIGHT -> Direction.DOWN;
			case LEFT -> Direction.UP;
			};
		}

		int calculate() {
			Point start = findStart();
			var direction = Direction.UP;

			Point nextPoint = start;

			while (nextPoint.x() > -1 && nextPoint.x() < width
					&& nextPoint.y() > -1 && nextPoint.y() < height) {
				visited.add(nextPoint);

				var potentialPoint = nextPoint(nextPoint, direction);
				if (obstructions.contains(potentialPoint)) {
					direction = nextDirection(direction);
					potentialPoint = nextPoint(nextPoint, direction);
				}
				nextPoint = potentialPoint;
			}

			return visited.size();
		}

	}

	@Test
	void daySixPuzzleTwoExample() throws Exception {
		int actual = daySixPuzzleTwo(
				ClassPathFiles.readString("aoc2024day06-example.txt")
		);
		assertEquals(6, actual);
	}

	@Test
	void daySixPuzzleTwo() throws Exception {
		int dayFourPuzzleOne = daySixPuzzleOne(
				ClassPathFiles.readString("aoc2024day06-input.txt")
		);
		System.out.println("Day Six Puzzle Two: " + dayFourPuzzleOne);
	}

	private int daySixPuzzleTwo(String input) {
		var guardOne = new GuardTwo(input);
		return guardOne.calculate();
	}

	public static class GuardTwo {

		public GuardTwo(String input) {
		}

		public int calculate() {
			return 0;
		}

	}

}
