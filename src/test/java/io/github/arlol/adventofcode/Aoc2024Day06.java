package io.github.arlol.adventofcode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
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
		int daySixPuzzleOne = daySixPuzzleOne(
				ClassPathFiles.readString("aoc2024day06-input.txt")
		);
		System.out.println("Day Six Puzzle One: " + daySixPuzzleOne);
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

	private static class GuardOne {

		String input;
		int width;
		int height;
		Point start;
		Set<Point> obstructions;

		public GuardOne(String input) {
			this.input = input;
			var lines = input.lines().toList();
			this.width = lines.getFirst().length();
			this.height = lines.size();
			this.obstructions = findObstructions();
			this.start = findStart();
		}

		Point findStart() {
			int indexOf = input.indexOf("^");
			int x = indexOf % (width + 1);
			int y = (indexOf - x) / (width + 1);
			return new Point(x, y);
		}

		Set<Point> findObstructions() {
			var result = new HashSet<Point>();

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
			var visited = new HashMap<Point, Set<Direction>>();
			var direction = Direction.UP;

			Point nextPoint = start;

			while (nextPoint.x() > -1 && nextPoint.x() < width
					&& nextPoint.y() > -1 && nextPoint.y() < height) {
				var visitedDirections = visited.computeIfAbsent(
						nextPoint,
						(k) -> new HashSet<Direction>()
				);
				if (visitedDirections.contains(direction)) {
					return -1;
				}
				visitedDirections.add(direction);

				var potentialPoint = nextPoint(nextPoint, direction);
				while (obstructions.contains(potentialPoint)) {
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
		var guard = new GuardTwo(
				ClassPathFiles.readString("aoc2024day06-example.txt")
		);
		var loopingPoints = guard.findAllLoopingPoints();
		assertThat(loopingPoints).contains(
				new Point(3, 6),
				new Point(6, 7),
				new Point(7, 7),
				new Point(1, 8),
				new Point(6, 7),
				new Point(3, 8),
				new Point(7, 9)
		);
		assertEquals(6, loopingPoints.size());
	}

	@Test
	void daySixPuzzleTwo() throws Exception {
		var guard = new GuardTwo(
				ClassPathFiles.readString("aoc2024day06-input.txt")
		);
		var loopingPoints = guard.findAllLoopingPoints();
		assertThat(loopingPoints).contains(new Point(5, 10));
		System.out.println("Day Six Puzzle Two: " + loopingPoints.size());
	}

	private static class GuardTwo extends GuardOne {

		public GuardTwo(String input) {
			super(input);
		}

		public Set<Point> findAllLoopingPoints() {
			var result = new HashSet<Point>();
			var originalObstructions = obstructions;
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					obstructions = new HashSet<>(originalObstructions);
					Point point = new Point(x, y);
					if (point.equals(start) || obstructions.contains(point)) {
						continue;
					}
					obstructions.add(point);
					if (calculate() == -1) {
						result.add(point);
					}
				}
			}
			return result;
		}

	}

}
