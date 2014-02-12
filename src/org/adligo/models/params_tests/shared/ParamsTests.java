package org.adligo.models.params_tests.shared;

import java.util.Date;

import org.adligo.models.params.shared.Params;
import org.adligo.tests.ATest;

public class ParamsTests extends ATest {

	public void testAddStringParam() {
		Params params = new Params();
		params.addParam("key","val");
		params.First();
		assertTrue(params.getNextParam("key"));
		Object [] values = params.getValues();
		assertEquals(1, values.length);
		assertEquals("val", values[0]);
	}
	
	public void testAddIntegerParam() {
		Params params = new Params();
		params.addParam("key",123);
		params.First();
		assertTrue(params.getNextParam("key"));
		Object [] values = params.getValues();
		assertEquals(1, values.length);
		assertEquals(123, values[0]);
	}

	public void testAddLongParam() {
		Params params = new Params();
		params.addParam("key",new Long(999999));
		params.First();
		assertTrue(params.getNextParam("key"));
		Object [] values = params.getValues();
		assertEquals(1, values.length);
		assertEquals(new Long(999999), values[0]);
	}
	
	public void testAddDoubleParam() {
		Params params = new Params();
		params.addParam("key",new Double(999.999));
		params.First();
		assertTrue(params.getNextParam("key"));
		Object [] values = params.getValues();
		assertEquals(1, values.length);
		assertEquals(new Double(999.999), values[0]);
	}
	
	public void testAddFloatParam() {
		Params params = new Params();
		params.addParam("key",new Double(111.111));
		params.First();
		assertTrue(params.getNextParam("key"));
		Object [] values = params.getValues();
		assertEquals(1, values.length);
		assertEquals(new Double(111.111), values[0]);
	}
	
	public void testAddBooleanParam() {
		Params params = new Params();
		params.addParam("key",true);
		params.First();
		assertTrue(params.getNextParam("key"));
		Object [] values = params.getValues();
		assertEquals(1, values.length);
		assertEquals(true, values[0]);		
	}
	
	public void testAddDateParam() {
		Params params = new Params();
		params.addParam("key",new Date(new Long(1929)));
		params.First();
		assertTrue(params.getNextParam("key"));
		Object [] values = params.getValues();
		assertEquals(1, values.length);
		assertEquals(new Date(new Long(1929)), values[0]);			
	}
	

}
