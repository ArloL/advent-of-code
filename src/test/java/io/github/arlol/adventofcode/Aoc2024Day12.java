package io.github.arlol.adventofcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Aoc2024Day12 {

	@Test
	void dayTwelvePuzzleOneExample() throws Exception {
		var actual = dayTwelvePuzzleOne(
				ClassPathFiles.readString("aoc2024day12-example.txt")
		);
		assertThat(actual).isEqualTo(140);
	}

	@Test
	void dayTwelvePuzzleOneExampleTwo() throws Exception {
		var actual = dayTwelvePuzzleOne(
				ClassPathFiles.readString("aoc2024day12-example2.txt")
		);
		assertThat(actual).isEqualTo(772);
	}

	@Test
	void dayTwelvePuzzleOneExampleThree() throws Exception {
		var actual = dayTwelvePuzzleOne(
				ClassPathFiles.readString("aoc2024day12-example3.txt")
		);
		assertThat(actual).isEqualTo(1930);
	}

	@Test
	void dayTwelvePuzzleOne() throws Exception {
		var actual = dayTwelvePuzzleOne(
				ClassPathFiles.readString("aoc2024day12-input.txt")
		);
		System.out.println("Day Twelve Puzzle One: " + actual);
	}

	private long dayTwelvePuzzleOne(String input) {
		var lines = input.lines().toList();
		for (int y = 0; y < lines.size(); y++) {
			for (int x = 0; x < 12; x++) {
				System.out.println(x + ":" + y);
			}
		}
		return 0;
	}

	@Test
	void dayTwelvePuzzleTwo() throws Exception {
		var actual = dayTwelvePuzzleTwo(
				ClassPathFiles.readString("aoc2024day12-input.txt")
		);
		System.out.println("Day Twelve Puzzle Two: " + actual);
	}

	private long dayTwelvePuzzleTwo(String input) {
		return 0;
	}

}
