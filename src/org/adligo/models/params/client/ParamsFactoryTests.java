package org.adligo.models.params.client;

import org.adligo.tests.ATest;

public class ParamsFactoryTests extends ATest {

	public void testGetOffset() {
		Params params = new Params();
		Exception caught = null;
		try {
			ParamsFactory.getOffset(params);
		} catch (Exception x) {
			caught = x;
		}
		assertTrue(caught instanceof IllegalArgumentException);
		assertEquals("Didn't find offset parameter", caught.getMessage());
		
		params.addParam(ParamsFactory.OFFSET, "asdf");
		caught = null;
		try {
			ParamsFactory.getOffset(params);
		} catch (Exception x) {
			caught = x;
		}
		assertTrue(caught instanceof IllegalArgumentException);
		assertEquals("Didn't find offset parameter", caught.getMessage());
		
		params = new Params();
		params.addParam(ParamsFactory.OFFSET, 123);
		assertEquals(123, ParamsFactory.getOffset(params));
		
	}
}
