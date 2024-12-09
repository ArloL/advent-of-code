package io.github.arlol.adventofcode;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;

public class Aoc2024Day09 {

	@Test
	void dayNinePuzzleOneExample() throws Exception {
		var actual = dayNinePuzzleOne(
				ClassPathFiles.readString("aoc2024day09-example.txt")
		);
		assertEquals(1928, actual);
	}

	@Test
	void dayNinePuzzleOne() throws Exception {
		var dayFourPuzzleOne = dayNinePuzzleOne(
				ClassPathFiles.readString("aoc2024day09-input.txt")
		);
		System.out.println("Day Nine Puzzle One: " + dayFourPuzzleOne);
	}

	private long dayNinePuzzleOne(String diskMap) {
		var disk = diskMapToDisk(diskMap);
		compactDisk(disk);
		return calculateCheckSum(disk);
	}

	private List<Long> diskMapToDisk(String diskMap) {
		var disk = new ArrayList<Long>();
		char[] diskMapCharArray = diskMap.toCharArray();
		for (int i = 0, index = 0; i < diskMapCharArray.length; i++) {
			char c = diskMapCharArray[i];
			long count = Long.parseLong(String.valueOf(c));
			if (i % 2 == 0) {
				disk.addAll(repeat(index, count));
				index++;
			} else {
				disk.addAll(repeat(-1, count));
			}
		}
		return disk;
	}

	private void compactDisk(List<Long> disk) {
		int nextFreeIndex = indexOf(disk, -1L, 0);
		for (int i = disk.size() - 1; i >= 0; i--) {
			long block = disk.get(i);
			if (block == -1) {
				continue;
			}
			if (nextFreeIndex > i) {
				break;
			}
			disk.set(i, -1L);
			disk.set(nextFreeIndex, block);
			nextFreeIndex = indexOf(disk, -1L, nextFreeIndex + 1);
		}
	}

	private long calculateCheckSum(List<Long> disk) {
		long sum = 0;
		for (int i = 0; i < disk.size(); i++) {
			long block = disk.get(i);
			if (block == -1) {
				continue;
			}
			sum += block * i;
		}
		return sum;
	}

	private List<Long> repeat(long x, long count) {
		return LongStream.range(0, count).mapToObj(i -> x).toList();
	}

	private <E> int indexOf(List<E> list, E entry, int fromIndex) {
		for (int i = fromIndex; i < list.size(); i++) {
			if (list.get(i).equals(entry)) {
				return i;
			}
		}
		return -1;
	}

	@Test
	void dayNinePuzzleTwoExample() throws Exception {
		var actual = dayNinePuzzleTwo(
				ClassPathFiles.readString("aoc2024day09-example.txt")
		);
		assertEquals(2858, actual);
	}

	@Test
	void dayNinePuzzleTwoExampleTwo() throws Exception {
		var actual = dayNinePuzzleTwo(
				ClassPathFiles.readString("aoc2024day09-example2.txt")
		);
		assertEquals(14900, actual);
	}

	@Test
	void dayNinePuzzleTwoExampleThree() throws Exception {
		var actual = dayNinePuzzleTwo(
				ClassPathFiles.readString("aoc2024day09-example3.txt")
		);
		assertEquals(100049, actual);
	}

	@Test
	void dayNinePuzzleTwoExampleFour() throws Exception {
		var actual = dayNinePuzzleTwo(
				ClassPathFiles.readString("aoc2024day09-example4.txt")
		);
		assertEquals(97898222299196L, actual);
	}

	@Test
	void dayNinePuzzleTwoExampleFive() throws Exception {
		var actual = dayNinePuzzleTwo("11111111111111111111");
		assertEquals(215, actual);
	}

	@Test
	void dayNinePuzzleTwo() throws Exception {
		var dayFourPuzzleOne = dayNinePuzzleTwo(
				ClassPathFiles.readString("aoc2024day09-input.txt")
		);
		System.out.println("Day Nine Puzzle Two: " + dayFourPuzzleOne);
	}

	private long dayNinePuzzleTwo(String input) {
		var disk = diskMapToDisk(input);
		compactDiskTwo(disk);
		return calculateCheckSum(disk);
	}

	private void compactDiskTwo(List<Long> disk) {
		for (int max = disk.size() - 1,
				min = max; min >= 0; max = min - 1, min = max) {
			long block = disk.get(max);
			while (min - 1 > 0 && disk.get(min - 1).equals(block)) {
				min--;
			}
			int size = max - min + 1;
			int nextBlockStart = findNextBlockStart(disk, size, min);
			if (nextBlockStart != -1) {
				for (int i = 0; i < size; i++) {
					disk.set(min + i, -1L);
					disk.set(nextBlockStart + i, block);
				}
			}
		}
	}

	private int findNextBlockStart(List<Long> disk, int size, int max) {
		for (int x = 0; x <= max - size; x++) {
			int toIndex = x + size;
			if (toIndex > disk.size()) {
				continue;
			}
			if (disk.get(x) == -1 && disk.subList(x, toIndex)
					.stream()
					.allMatch(i -> i == -1)) {
				return x;
			}
		}
		return -1;
	}

	void print(List<Long> disk) {
		System.out.println(
				disk.stream().map("%04d"::formatted).collect(joining(","))
		);
	}

}
