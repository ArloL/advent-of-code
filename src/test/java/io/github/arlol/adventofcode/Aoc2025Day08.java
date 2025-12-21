package io.github.arlol.adventofcode;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Aoc2025Day08 {

	@ParameterizedTest
	@MethodSource("provideArgumentsForPuzzle1")
	void puzzle1Test(long expected, String input) throws Exception {
		var actual = puzzle1(input, 10);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForPuzzle1() {
		return Stream.of();
	}

	@Test
	void puzzle1Example() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day08-example.txt"),
				10
		);
		assertThat(actual).isEqualTo(40L);
	}

	@Test
	void puzzle1() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day08-input.txt"),
				1000
		);
		System.out.println("Day 08 Puzzle 1: " + actual);
		assertThat(actual).isEqualTo(98696L);
	}

	private long puzzle1(String input, int numberOfConnections) {
		var points = input.lines().map(Point::new).toList();
		var pointsSortedByDistance = distanceMap(points).entrySet()
				.stream()
				.sorted(Comparator.comparingDouble(Entry::getValue))
				.map(Entry::getKey)
				.toList();
		var pointToJunctionBox = points.stream()
				.collect(toMap(point -> point, Set::of));
		for (int i = 0; i < numberOfConnections; i++) {
			var entry = pointsSortedByDistance.get(i);
			var newJunctionBox = Stream
					.of(
							pointToJunctionBox.get(entry.first()),
							pointToJunctionBox.get(entry.second())
					)
					.flatMap(Set::stream)
					.collect(Collectors.toSet());
			for (var point : newJunctionBox) {
				pointToJunctionBox.put(point, newJunctionBox);
			}
		}
		var sortedCircuits = pointToJunctionBox.values()
				.stream()
				.distinct()
				.sorted(Comparator.comparing(Set::size))
				.toList();
		return (long) sortedCircuits.get(sortedCircuits.size() - 1).size()
				* sortedCircuits.get(sortedCircuits.size() - 2).size()
				* sortedCircuits.get(sortedCircuits.size() - 3).size();
	}

	private Map<Pair<Point>, Double> distanceMap(List<Point> points) {
		Map<Pair<Point>, Double> distanceMap = new HashMap<>();
		for (var point : points) {
			for (var other : points) {
				if (point.equals(other)) {
					continue;
				}
				var key1 = new Pair<>(point, other);
				var key2 = new Pair<>(other, point);
				if (!distanceMap.containsKey(key1)
						&& !distanceMap.containsKey(key2)) {
					distanceMap.put(key1, point.distance(other));
				}
			}
		}
		return distanceMap;
	}

	private record Pair<T>(
			T first,
			T second
	) {
	}

	private record Point(
			int x,
			int y,
			int z
	) {

		Point(String line) {
			var numbers = Arrays.stream(line.split(","))
					.map(Integer::parseInt)
					.toList();
			this(numbers.get(0), numbers.get(1), numbers.get(2));
		}

		double distance(Point other) {
			var xdis = Math.abs(this.x - other.x);
			var ydis = Math.abs(this.y - other.y);
			var zdis = Math.abs(this.z - other.z);
			return Math.sqrt(
					Math.pow(xdis, 2) + Math.pow(ydis, 2) + Math.pow(zdis, 2)
			);
		}

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
				ClassPathFiles.readString("aoc2025day08-example.txt")
		);
		assertThat(actual).isEqualTo(25272L);
	}

	@Test
	void puzzle2() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day08-input.txt")
		);
		System.out.println("Day 08 Puzzle 2: " + actual);
		assertThat(actual).isEqualTo(2245203960L);
	}

	private long puzzle2(String input) {
		var points = input.lines().map(Point::new).toList();
		var pointsSortedByDistance = distanceMap(points).entrySet()
				.stream()
				.sorted(Comparator.comparingDouble(Entry::getValue))
				.map(Entry::getKey)
				.toList();
		var pointToJunctionBox = points.stream()
				.collect(toMap(point -> point, Set::of));
		for (var entry : pointsSortedByDistance) {
			var newJunctionBox = Stream
					.of(
							pointToJunctionBox.get(entry.first()),
							pointToJunctionBox.get(entry.second())
					)
					.flatMap(Set::stream)
					.collect(Collectors.toSet());
			if (newJunctionBox.size() == points.size()) {
				return (long) entry.first().x() * entry.second().x();
			}
			for (var point : newJunctionBox) {
				pointToJunctionBox.put(point, newJunctionBox);
			}
		}
		throw new IllegalStateException();
	}

}
