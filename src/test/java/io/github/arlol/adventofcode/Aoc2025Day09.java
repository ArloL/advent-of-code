package io.github.arlol.adventofcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class Aoc2025Day09 {

	@Test
	void puzzle1Example() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day09-example.txt")
		);
		assertThat(actual).isEqualTo(50L);
	}

	@Test
	void puzzle1() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day09-input.txt")
		);
		System.out.println(
				"Day 09 Puzzle 1: " + new BigDecimal(actual).toPlainString()
		);
		assertThat(actual).isEqualTo(4755429952L);
	}

	private long puzzle1(String input) {
		var redTiles = input.lines().map(Point::new).toList();
		var rectangles = new HashSet<Rectangle>();
		for (var point : redTiles) {
			for (var other : redTiles) {
				if (point.equals(other)) {
					continue;
				}
				rectangles.add(new Rectangle(point, other));
			}
		}
		return rectangles.stream()
				.max(Comparator.comparingDouble(Rectangle::area))
				.orElseThrow()
				.area();
	}

	private record Point(
			int x,
			int y
	) {

		Point(String line) {
			var numbers = Arrays.stream(line.split(","))
					.map(Integer::parseInt)
					.toList();
			this(numbers.get(0), numbers.get(1));
		}

	}

	private record Rectangle(
			Point first,
			Point last
	) {

		private Rectangle(Point first, Point last) {
			this.first = new Point(
					Math.min(first.x(), last.x()),
					Math.min(first.y(), last.y())
			);
			this.last = new Point(
					Math.max(first.x(), last.x()),
					Math.max(first.y(), last.y())
			);
		}

		long area() {
			long xDistance = last().x() - first().x() + 1;
			long yDistance = last().y() - first().y() + 1;
			return xDistance * yDistance;
		}

		Rectangle inner() {
			return new Rectangle(
					new Point(first().x() + 1, first().y() + 1),
					new Point(last().x() - 1, last().y() - 1)
			);
		}

		boolean overlaps(Rectangle other) {
			return Math.max(first().x(), other.first().x()) <= Math
					.min(last().x(), other.last().x())
					&& Math.max(first().y(), other.first().y()) <= Math
							.min(last().y(), other.last().y());
		}

	}

	@Test
	void puzzle2Example() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day09-example.txt")
		);
		assertThat(actual).isEqualTo(24L);
	}

	@Test
	void puzzle2() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day09-input.txt")
		);
		System.out.println(
				"Day 09 Puzzle 2: " + new BigDecimal(actual).toPlainString()
		);
		assertThat(actual).isEqualTo(1429596008L);
	}

	private long puzzle2(String input) throws Exception {
		Set<Rectangle> lines = new HashSet<>();
		Set<Rectangle> rectangles = new HashSet<>();
		var redTiles = input.lines().map(Point::new).toList();
		for (int i = 0; i < redTiles.size(); i++) {
			var point = redTiles.get(i);
			var nextPoint = i + 1 < redTiles.size() ? redTiles.get(i + 1)
					: redTiles.getFirst();
			lines.add(new Rectangle(point, nextPoint));
		}
		for (var point : redTiles) {
			for (var other : redTiles) {
				if (point.equals(other)) {
					continue;
				}
				rectangles.add(new Rectangle(point, other));
			}
		}
		return rectangles.stream()
				.sorted(
						Collections.reverseOrder(
								Comparator.comparingDouble(Rectangle::area)
						)
				)
				.filter(
						rectangle -> lines.stream()
								.noneMatch(
										line -> line.overlaps(rectangle.inner())
								)
				)
				.findFirst()
				.orElseThrow()
				.area();
	}

}
