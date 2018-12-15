package edu.lu.uni.serval.method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import edu.lu.uni.serval.method.parser.MethodNameParser;

public class TestMethodNameParser {
	
	private MethodNameParser parser = new MethodNameParser();

	@Test
	public void testParseMethodName() {
		assertEquals("get,id", parser.parseMethodName("getId"));
		assertEquals("get,id", parser.parseMethodName("get_Id"));
		assertEquals("get,id", parser.parseMethodName("get_Id0"));
	}
	
	@Test public void testParseWithGenTest() {
		try {
			assertEquals("get,id,", parser.parseWithGenTest("getId").toString());
			assertEquals("get,id,", parser.parseWithGenTest("get_Id").toString());
			assertEquals("get,id,", parser.parseWithGenTest("get_Id0").toString());
		} catch (IOException e) {
			fail("Failed to parse method name with GenTest.");
		}
	}
	
	@Test
	public void testParseWithCamelCase() {
		assertEquals("get,id", parser.parseWithCamelCase("getId"));
		assertEquals("get,id", parser.parseWithCamelCase("get_Id"));
		assertEquals("get,id", parser.parseWithCamelCase("get_Id0"));
	}

}
