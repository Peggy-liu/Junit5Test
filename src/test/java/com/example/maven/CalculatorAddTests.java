package com.example.maven;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.Timeout;

import com.example.maven.math.Calculator;
import com.example.maven.math.TooLargeNumbers;

/**
 * Maven build test: the default configuration for maven to run a test is that 
 * the class name have to start or finish with "Test"
 * maven command: mvn clean test
 */

@Tag(value="fast")
public class CalculatorAddTests 
{
    private Calculator calculator = new Calculator();
    
    @Nested
    @DisplayName("add method positive cases")
    class PositiveCases{
    	@Test
	    @DisplayName("add method test case: when numbers are positive")
		public void addTestCase1() {
			int n1 = 20;
			int n2 = 30;
			int expected = 50;
			int sum = calculator.add(n1, n2);
			
			assertEquals(expected, sum);
		}
    		
    	    
    	    
    	    @Test
    	    @DisplayName("add method test case: when numbers are negative")
    		public void addTestCase2() {
    			int n1 = -20;
    			int n2 = -30;
    			int expected = -50;
    			int sum = calculator.add(n1, n2);
    			
    			assertEquals(expected, sum);
    		}
    }
    
   @Nested
   @DisplayName("add method exception test cases")
    class ExceptionalCases{
    	 @Test
    	    @DisplayName("add method test case: when any numbers are less than -100")
    		public void addTestCase3() {
    			int n1 = 200;
    			int n2 = -200;
    			
    			RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.add(n1, n2));
    			assertEquals("Numbers less than -100 are not allowed", exception.getMessage());
    		}
    	    
    	    
    	    @Test
    	    @DisplayName("add method test case: when any numbers are larger than 50000")
    		public void addTestCase4() {
    			int n1 = 50001;
    			int n2 = 200;
    			
    			RuntimeException exception = assertThrows(TooLargeNumbers.class, () -> calculator.add(n1, n2));
    			assertEquals("Numbers larger than 50000 are not allowed", exception.getMessage());
    		}
    }
    
   
    
    
    @Test
    @DisplayName("add method timeout test case1: when both numbers are larger than 40000")
    @Timeout(value = 1020, unit= TimeUnit.MILLISECONDS )
	public void addTestCase5() {
		int n1 = 40001;
		int n2 = 40001;
		
		assertEquals(80002, calculator.add(n1, n2));
	}

    
    @Test
    @DisplayName("add method timeout test case2: when both numbers are larger than 40000")
	public void addTestCase6() {
		int n1 = 40001;
		int n2 = 40001;
		
		assertEquals(80002, assertTimeout(Duration.ofMillis(1006), () -> calculator.add(n1, n2)));
	}
    
    @Test
    @DisplayName("add method tests")
	public void addTestCase7() {
    	assertAll("multiple test cases", 
    			 () -> assertEquals(80002, assertTimeout(Duration.ofMillis(1006), () -> calculator.add(40001, 40001))),
    			 () -> assertEquals(-50, calculator.add(-30, -20))
    			 );
	}
    
    
 /*
  * Repeated Test method normally used on time-intensive or time sensitive methods testing
  */
    @Nested
    class RepeatedTest1{
    	
    	
    	@BeforeEach
    	public void before(RepetitionInfo info, TestInfo testInfo){
    		System.out.println("current repetition is " + info.getCurrentRepetition());
    		System.out.println(String.format("Repetition %s out of %s on method %s",info.getCurrentRepetition()
    				, info.getTotalRepetitions(), testInfo.getTestMethod().get().getName()));
    	}
    	
    	@RepeatedTest(value = 5)
    	@DisplayName("repeated test for timeout")
    	public void repeatedTest() {
    		int n1 = 40001;
    		int n2 = 40001;
    		
    		assertEquals(80002, assertTimeout(Duration.ofMillis(1006), () -> calculator.add(n1, n2)));
    	}
    	
    	/*
    	 * RepetitionInfo can only be injected into @BeforeEach method when the corresponding 
    	 * methods are all @RepeatedTest method. If there is @Test method, exception will be thrown
    	 */
//    	@Test
//		public void addTestCase1() {
//			
//		}
    	
    	@RepeatedTest(4)
        @DisplayName("repeated test 2")
    	public void repeatedTest2() {
        	assertAll("multiple test cases", 
        			 () -> assertEquals(80002, assertTimeout(Duration.ofMillis(1006), () -> calculator.add(40001, 40001))),
        			 () -> assertEquals(-50, calculator.add(-30, -20))
        			 );
    	}
    }
}
