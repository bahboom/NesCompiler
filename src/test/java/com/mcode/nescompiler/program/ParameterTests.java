package com.mcode.nescompiler.program;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParameterTests {

	@Test
	public void testOrderOfOperations() {
		Parameter p;
		p = new Parameter("5 - (2 + 3)");
		assertEquals(0, p.evaluate());
		
		p = new Parameter("(5 - 2) + 3");
		assertEquals(6, p.evaluate());
		
		p = new Parameter("5 - 2 + 3");
		assertEquals(6, p.evaluate());
	}

}
