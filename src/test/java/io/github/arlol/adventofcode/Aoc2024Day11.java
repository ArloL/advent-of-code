package io.github.arlol.adventofcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class Aoc2024Day11 {

	@Test
	void dayElevenPuzzleOneExample() throws Exception {
		var actual = dayElevenPuzzleOne("0 1 10 99 999", 1);
		assertThat(actual).isEqualTo(7);
	}

	@Test
	void dayElevenPuzzleOneExampleTwo() throws Exception {
		var actual = dayElevenPuzzleOne("125 17", 1);
		assertThat(actual).isEqualTo(3);
	}

	@Test
	void dayElevenPuzzleOneExampleThree() throws Exception {
		var actual = dayElevenPuzzleOne("125 17", 2);
		assertThat(actual).isEqualTo(4);
	}

	@Test
	void dayElevenPuzzleOneExampleFour() throws Exception {
		var actual = dayElevenPuzzleOne("125 17", 25);
		assertThat(actual).isEqualTo(55312);
	}

	@Test
	void dayElevenPuzzleOne() throws Exception {
		var actual = dayElevenPuzzleOne(
				ClassPathFiles.readString("aoc2024day11-input.txt"),
				25
		);
		System.out.println("Day Eleven Puzzle One: " + actual);
	}

	private long dayElevenPuzzleOne(String input, int blinkCount) {
		var stones = Stream.of(input.split("\s+"))
				.map(Long::parseLong)
				.toList();
		long count = 0;
		for (var stone : stones) {
			count += stone(stone, blinkCount);
		}
		return count;
	}

	private Map<String, Long> cache = new HashMap<>();

	private long stone(long stone, int remainingBlinks) {
		var cacheKey = stone + ":" + remainingBlinks;
		var cacheHit = cache.get(cacheKey);
		if (cacheHit != null) {
			return cacheHit;
		}

		long result = -1;
		var text = "" + stone;
		if (remainingBlinks == 0) {
			result = 1;
		} else if (stone == 0) {
			result = stone(1L, remainingBlinks - 1);
		} else if (text.length() % 2 == 0) {
			var left = text.substring(0, text.length() / 2);
			var right = text.substring(left.length(), text.length());
			result = stone(Long.parseLong(left), remainingBlinks - 1)
					+ stone(Long.parseLong(right), remainingBlinks - 1);
		} else {
			result = stone(stone * 2024, remainingBlinks - 1);
		}

		cache.put(cacheKey, result);

		return result;
	}

	@Test
	void dayElevenPuzzleTwo() throws Exception {
		var actual = dayElevenPuzzleOne(
				ClassPathFiles.readString("aoc2024day11-input.txt"),
				75
		);
		System.out.println("Day Eleven Puzzle Two: " + actual);
	}

}
