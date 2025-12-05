package io.github.arlol.adventofcode;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@NullMarked
public class Aoc2025Day05 {

	private static record Range(
			long start,
			long end
	) {

		Range(String range) {
			var splitRange = range.split("-");
			var start = Long.parseLong(splitRange[0]);
			var end = Long.parseLong(splitRange[1]);
			this(start, end);
		}

		boolean isInRange(long id) {
			return id >= start && id <= end;
		}

		long size() {
			return end - start + 1;
		}

	}

	@ParameterizedTest
	@MethodSource("provideArgumentsForPuzzle1")
	void puzzle1Test(long expected, String input) throws Exception {
		var actual = puzzle1(input);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForPuzzle1() {
		return Stream.of(Arguments.of(0, """
				3-5
				10-14
				16-20
				12-18

				1
				"""), Arguments.of(1, """
				3-5
				10-14
				16-20
				12-18

				5
				"""), Arguments.of(0, """
				3-5
				10-14
				16-20
				12-18

				8
				"""));
	}

	@Test
	void puzzle1Example() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day05-example.txt")
		);
		assertThat(actual).isEqualTo(3L);
	}

	@Test
	void puzzle1() throws Exception {
		var actual = puzzle1(
				ClassPathFiles.readString("aoc2025day05-input.txt")
		);
		System.out.println("Day 05 Puzzle 1: " + actual);
		assertThat(actual).isEqualTo(758L);
	}

	private long puzzle1(String input) {
		String[] split = input.split("\n\n");
		var ranges = split[0].lines().map(Range::new).toList();
		return split[1].lines().map(Long::parseLong).filter(id -> {
			return ranges.stream().anyMatch(range -> range.isInRange(id));
		}).count();
	}

	@ParameterizedTest
	@MethodSource("provideArgumentsForPuzzle2")
	void puzzle2Test(long expected, String input) throws Exception {
		var actual = puzzle2(input);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForPuzzle2() {
		return Stream.of(Arguments.of(3, """
				3-5

				0
				"""), Arguments.of(3, """
				1-2
				2-3

				0
				"""));
	}

	@Test
	void puzzle2Example() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day05-example.txt")
		);
		assertThat(actual).isEqualTo(14L);
	}

	@Test
	void puzzle2() throws Exception {
		var actual = puzzle2(
				ClassPathFiles.readString("aoc2025day05-input.txt")
		);
		System.out.println("Day 05 Puzzle 2: " + actual);
		assertThat(actual).isEqualTo(343143696885053L);
	}

	private long puzzle2(String input) {
		String[] split = input.split("\n\n");
		var ranges = split[0].lines().map(Range::new).collect(toSet());
		return simplifyRanges(ranges).stream().mapToLong(Range::size).sum();
	}

	private Set<@NonNull Range> simplifyRanges(Set<@NonNull Range> ranges) {
		Set<Range> result;
		long updatedRanges;
		do {
			updatedRanges = 0L;
			result = new HashSet<Range>();
			for (var range : ranges) {
				Range newRange = range;
				for (var otherRange : ranges) {
					if (range.equals(otherRange)) {
						continue;
					}
					boolean startInOtherRange = otherRange
							.isInRange(range.start());
					boolean endInOtherRange = otherRange.isInRange(range.end());
					if (startInOtherRange && endInOtherRange) {
						updatedRanges++;
						newRange = otherRange;
					} else if (startInOtherRange) {
						updatedRanges++;
						newRange = new Range(
								Math.min(range.start(), otherRange.start()),
								range.end()
						);
					} else if (endInOtherRange) {
						updatedRanges++;
						newRange = new Range(
								range.start(),
								Math.max(range.end(), otherRange.end())
						);
					}
				}
				result.add(newRange);
			}

			ranges = result;

		} while (updatedRanges > 0);

		return result;
	}

}
