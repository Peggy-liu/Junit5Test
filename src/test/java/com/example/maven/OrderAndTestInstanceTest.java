package com.example.maven;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@Tag(value="fast")
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class OrderAndTestInstanceTest {

	private boolean flag;
	private int total ;
	
	public OrderAndTestInstanceTest() {
		this.flag = true;
		total = 0;
		System.out.println("test instance created");
	}
	
	/***********************Method Order Annotation**********************/

	@Test
	@Order(2)
	public void test1() {
		assertTrue(true);
	}
	
	@Test
	@Order(1)
	public void test2() {
		assertFalse(false);
	}
	
	/***********************Test Instance Demo--instance per method**********************/
	
	
	@Test
	@Order(4)
	public void test3() {
		assertTrue(this.flag);
		this.flag = false;
	}
	
	
	@Test
	@Order(5)
	public void test4() {
		assertFalse(this.flag);
		//this.flag = false;
	}
	
	
	@ParameterizedTest
	@Order(3)
	@ValueSource(ints = {1,1})
	public void test7(int num) {
		this.total += num;
		System.out.println("total is "+ total);
	}
	
	
	
	/***********************Test Instance Demo--instance per class**********************/
	
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
	class SingleInstance{
		private boolean flag;
		private int total ;
		
		public SingleInstance() {
			this.flag = true;
			this.total = 0;
			System.out.println("inner class object created");
		}
		
		@Test
		@Order(3)
		public void test5() {
			assertTrue(this.flag);
			this.flag = false;
		}
		
		
		@Test
		@Order(4)
		public void test6() {
			assertFalse(this.flag);
			//this.flag = false;
		}
		
		@ParameterizedTest
		@Order(5)
		@ValueSource(ints = {1,1})
		public void test8(int num) {
			this.total += num;
			System.out.println("total is "+ total);
		}
	}
}
