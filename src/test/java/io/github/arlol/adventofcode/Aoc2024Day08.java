package io.github.arlol.adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class Aoc2024Day08 {

	@Test
	void dayEightPuzzleOneExample() throws Exception {
		var actual = dayEightPuzzleOne(
				ClassPathFiles.readString("aoc2024day08-example.txt")
		);
		assertEquals(14, actual);
	}

	@Test
	void dayEightPuzzleOneExampleTwo() throws Exception {
		var actual = dayEightPuzzleOne(
				ClassPathFiles.readString("aoc2024day08-example2.txt")
		);
		assertEquals(2, actual);
	}

	@Test
	void dayEightPuzzleOneExampleThree() throws Exception {
		var actual = dayEightPuzzleOne(
				ClassPathFiles.readString("aoc2024day08-example3.txt")
		);
		assertEquals(4, actual);
	}

	@Test
	void dayEightPuzzleOneExampleFour() throws Exception {
		var actual = dayEightPuzzleOne(
				ClassPathFiles.readString("aoc2024day08-example4.txt")
		);
		assertEquals(4, actual);
	}

	@Test
	void dayEightPuzzleOne() throws Exception {
		var actual = dayEightPuzzleOne(
				ClassPathFiles.readString("aoc2024day08-input.txt")
		);
		System.out.println("Day Eight Puzzle One: " + actual);
	}

	private long dayEightPuzzleOne(String input) {
		var gridOne = new GridOne(input);
		return gridOne.calculate();
	}

	public static record Point(
			int x,
			int y
	) {

	}

	public static class GridOne {

		String input;
		int width;
		int height;
		List<String> lines;
		Map<String, List<Point>> antennasForFrequencies = new HashMap<>();

		public GridOne(String input) {
			this.input = input;
			this.lines = input.lines().toList();
			this.width = lines.getFirst().length();
			this.height = lines.size();
		}

		public long calculate() {
			Set<Point> antinodes = new HashSet<>();

			for (int y = 0; y < lines.size(); y++) {
				var line = lines.get(y);
				for (int x = 0; x < line.length(); x++) {
					String frequency = line.substring(x, x + 1);
					if (!frequency.equals(".")) {
						antennasForFrequencies
								.computeIfAbsent(
										frequency,
										(k) -> new ArrayList<>()
								)
								.add(new Point(x, y));
					}
				}
			}

			System.out.println(antennasForFrequencies);

			antennasForFrequencies.forEach((frequency, antennas) -> {
				for (Point antenna : antennas) {
					for (Point otherAntenna : antennas) {
						if (antenna.equals(otherAntenna)) {
							continue;
						}
						var xDelta = otherAntenna.x() - antenna.x();
						var yDelta = otherAntenna.y() - antenna.y();
						Point potentialAntinode = new Point(
								antenna.x() + 2 * xDelta,
								antenna.y() + 2 * yDelta
						);
						if (potentialAntinode.x() >= 0
								&& potentialAntinode.x() < width
								&& potentialAntinode.y() >= 0
								&& potentialAntinode.y() < height) {
							antinodes.add(potentialAntinode);
							System.out.println(
									"%s: %s via %s to %s".formatted(
											frequency,
											antenna,
											otherAntenna,
											potentialAntinode
									)
							);
						}
					}
				}
			});

			return antinodes.size();
		}

	}

	@Test
	void dayEightPuzzleTwoExample() throws Exception {
		var actual = dayEightPuzzleTwo(
				ClassPathFiles.readString("aoc2024day08-puzzle2-example.txt")
		);
		assertEquals(9, actual);
	}

	@Test
	void dayEightPuzzleTwo() throws Exception {
		var actual = dayEightPuzzleTwo(
				ClassPathFiles.readString("aoc2024day08-input.txt")
		);
		System.out.println("Day Eight Puzzle Two: " + actual);
	}

	private long dayEightPuzzleTwo(String input) {
		var grid = new GridTwo(input);
		return grid.calculate();
	}

	public static class GridTwo {

		String input;
		int width;
		int height;
		List<String> lines;
		Map<String, List<Point>> antennasForFrequencies = new HashMap<>();

		public GridTwo(String input) {
			this.input = input;
			this.lines = input.lines().toList();
			this.width = lines.getFirst().length();
			this.height = lines.size();
		}

		public long calculate() {
			Set<Point> antinodes = new HashSet<>();

			for (int y = 0; y < lines.size(); y++) {
				var line = lines.get(y);
				for (int x = 0; x < line.length(); x++) {
					String frequency = line.substring(x, x + 1);
					if (!frequency.equals(".")) {
						antennasForFrequencies
								.computeIfAbsent(
										frequency,
										(k) -> new ArrayList<>()
								)
								.add(new Point(x, y));
					}
				}
			}

			System.out.println(antennasForFrequencies);

			antennasForFrequencies.forEach((frequency, antennas) -> {
				for (Point antenna : antennas) {
					for (Point otherAntenna : antennas) {
						if (antenna.equals(otherAntenna)) {
							continue;
						}
						var xDelta = otherAntenna.x() - antenna.x();
						var yDelta = otherAntenna.y() - antenna.y();
						for (int i = -100; i < 100; i++) {
							Point potentialAntinode = new Point(
									antenna.x() + i * xDelta,
									antenna.y() + i * yDelta
							);
							if (potentialAntinode.x() >= 0
									&& potentialAntinode.x() < width
									&& potentialAntinode.y() >= 0
									&& potentialAntinode.y() < height) {
								antinodes.add(potentialAntinode);
								System.out.println(
										"%s: %s via %s to %s".formatted(
												frequency,
												antenna,
												otherAntenna,
												potentialAntinode
										)
								);
							}
						}
					}
				}
			});

			System.out.println(antinodes);

			return antinodes.size();
		}

	}

}
