package com.example.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.maven.math.Calculator;

public class ParameterizedDemoTest {
	
	Calculator calculator = new Calculator();
	
	@ParameterizedTest
	@ValueSource(ints = {1,3,5,7,9})
	public void testCase1(int num) {
		assertTrue(calculator.isSumAllowed(num));
	}
	
	
	@ParameterizedTest
//	@EmptySource
//	@NullSource
//	@NullAndEmptySource     //@NullAndEmptySource = @EmptySource + @NullSource
	@ValueSource(strings = {"helllo", "there", "hi"})
	public void testCase2(String arg) {
		assertNotNull(arg);
	}
	
	@ParameterizedTest
	@MethodSource("IntFactory")
	public void testCase3(int num) {
		assertTrue(calculator.isSumAllowed(num));
	}
	
	private static IntStream IntFactory() {
		return IntStream.range(30, 40);
	}
	
	
	@ParameterizedTest
	@CsvSource(value = { "1,2,3",
			             "3,4,7",
			             "-4, -3, -7" })
	public void testCase4(int n1, int n2, int expected) {
		  assertEquals(expected, calculator.add(n1, n2));
	}
	
	
	@ParameterizedTest
	//filepath use relative file path
	@CsvFileSource(resources = "/sample-data.csv", numLinesToSkip = 1)
	public void testCase5(int n1, int n2, int expected) {
		  assertEquals(expected, calculator.add(n1, n2));
	}

}
