package io.github.arlol.adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Aoc2024Day01 {

	@Test
	void dayOnePuzzleOneExample() throws Exception {
		long actual = dayOnePuzzleOne(
				ClassPathFiles.readString("aoc2024day01-example.txt")
		);
		assertEquals(11, actual);
	}

	@Test
	void dayOnePuzzleOne() throws Exception {
		long dayOnePuzzleOne = dayOnePuzzleOne(
				ClassPathFiles.readString("aoc2024day01-input.txt")
		);
		System.out.println("Day One Puzzle One: " + dayOnePuzzleOne);
	}

	private long dayOnePuzzleOne(String input) {
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		input.lines().map(line -> line.split("\s+")).forEach(split -> {
			left.add(Integer.valueOf(split[0]));
			right.add(Integer.valueOf(split[1]));
		});
		Collections.sort(left);
		Collections.sort(right);
		long sum = 0;
		for (int i = 0; i < left.size(); i++) {
			int abs = Math.abs(left.get(i) - right.get(i));
			sum += abs;
		}
		return sum;
	}

	@Test
	void dayOnePuzzleTwoExample() throws Exception {
		long actual = dayOnePuzzleTwo(
				ClassPathFiles.readString("aoc2024day01-example.txt")
		);
		assertEquals(31, actual);
	}

	@Test
	void dayOnePuzzleTwo() throws Exception {
		long dayOnePuzzleTwo = dayOnePuzzleTwo(
				ClassPathFiles.readString("aoc2024day01-input.txt")
		);
		System.out.println("Day One Puzzle Two: " + dayOnePuzzleTwo);
	}

	private long dayOnePuzzleTwo(String input) {
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		input.lines().map(line -> line.split("\s+")).forEach(split -> {
			left.add(Integer.valueOf(split[0]));
			right.add(Integer.valueOf(split[1]));
		});
		long sum = 0;
		for (int l : left) {
			sum += l * right.stream().filter(r -> r.equals(l)).count();
		}
		return sum;
	}

}
