package com.example.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.io.TempDir;


@Tag(value="slow")
public class tempDirectoryDemoTest {

	
	public @TempDir Path fileDir;
	
	@FastTest
	public void test1() throws IOException {
		Path file = fileDir.resolve("test.txt");
		String hello = "hello world";
		Files.write(file, hello.getBytes());
		List<String> data = Files.readAllLines(file);
		List<String> expected = Collections.singletonList(hello);
		assertEquals(expected, data);
		
	   

	}
}
