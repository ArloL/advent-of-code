package io.github.arlol.adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Aoc2024Day05 {

	@Test
	void dayFivePuzzleOneExample() throws Exception {
		int actual = dayFivePuzzleOne(
				ClassPathFiles.readString("aoc2024day05-example.txt")
		);
		assertEquals(143, actual);
	}

	@Test
	void dayFivePuzzleOne() throws Exception {
		int dayFourPuzzleOne = dayFivePuzzleOne(
				ClassPathFiles.readString("aoc2024day05-input.txt")
		);
		System.out.println("Day Five Puzzle One: " + dayFourPuzzleOne);
	}

	private int dayFivePuzzleOne(String input) {
		var lines = input.lines().toList();
		int indexOf = lines.indexOf("");
		var rules = lines.subList(0, indexOf)
				.stream()
				.map(rule -> rule.split("\\|"))
				.toList();
		var updates = lines.subList(indexOf + 1, lines.size())
				.stream()
				.map(rule -> rule.split(","))
				.map(Arrays::asList)
				.toList();
		int sum = 0;
		for (var update : updates) {
			if (isUpdateValid(rules, update)) {
				sum += Integer.parseInt(update.get(update.size() / 2));
			}
		}
		return sum;
	}

	private boolean isUpdateValid(List<String[]> rules, List<String> update) {
		boolean valid = true;
		for (var rule : rules) {
			int indexOf1 = update.indexOf(rule[0]);
			int indexOf2 = update.indexOf(rule[1]);
			if (indexOf1 != -1 && indexOf2 != -1) {
				if (indexOf1 >= indexOf2) {
					valid = false;
					break;
				}
			}
		}
		return valid;
	}

	@Test
	void dayFivePuzzleTwoExample() throws Exception {
		int actual = dayFivePuzzleTwo(
				ClassPathFiles.readString("aoc2024day05-example.txt")
		);
		assertEquals(123, actual);
	}

	@Test
	void dayFivePuzzleTwo() throws Exception {
		int dayFourPuzzleOne = dayFivePuzzleTwo(
				ClassPathFiles.readString("aoc2024day05-input.txt")
		);
		System.out.println("Day Five Puzzle Two: " + dayFourPuzzleOne);
	}

	private int dayFivePuzzleTwo(String input) {
		var lines = input.lines().toList();
		int indexOf = lines.indexOf("");
		var rules = lines.subList(0, indexOf)
				.stream()
				.map(rule -> rule.split("\\|"))
				.toList();
		var updates = lines.subList(indexOf + 1, lines.size())
				.stream()
				.map(rule -> rule.split(","))
				.map(Arrays::asList)
				.toList();
		int sum = 0;
		for (var update : updates) {
			if (!isUpdateValid(rules, update)) {
				applyRulesToUpdate(rules, update);
				sum += Integer.parseInt(update.get(update.size() / 2));
			}
		}
		return sum;
	}

	private void applyRulesToUpdate(List<String[]> rules, List<String> update) {
		boolean ruleApplied;
		do {
			ruleApplied = false;
			for (var rule : rules) {
				int indexOf1 = update.indexOf(rule[0]);
				int indexOf2 = update.indexOf(rule[1]);
				if (indexOf1 != -1 && indexOf2 != -1) {
					if (indexOf1 >= indexOf2) {
						var h = update.get(indexOf1);
						update.set(indexOf1, update.get(indexOf2));
						update.set(indexOf2, h);
						ruleApplied = true;
						break;
					}
				}
			}
		} while (ruleApplied);
	}

}
