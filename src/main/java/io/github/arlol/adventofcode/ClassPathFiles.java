package io.github.arlol.adventofcode;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;

public abstract class ClassPathFiles {

	private ClassPathFiles() {
	}

	public static byte[] readAllBytes(String path) {
		try (InputStream stream = newInputStream(path)) {
			return stream.readAllBytes();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public static String readString(String path) {
		return readString(path, UTF_8);
	}

	public static String readString(String path, Charset cs) {
		return new String(readAllBytes(path), cs);
	}

	public static InputStream newInputStream(String path) {
		InputStream inputStream = ClassPathFiles.class
				.getResourceAsStream(path);
		if (inputStream == null) {
			throw new IllegalStateException("Could not find " + path);
		}
		return inputStream;
	}

}
