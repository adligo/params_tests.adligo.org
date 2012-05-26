package org.adligo.models.params.client;

import org.adligo.tests.ATest;

public class ParamsFactoryTests extends ATest {

	public void testGetOffset() {
		LimitOffset lo = new LimitOffset();
		Params params = new Params();
		
		ParamsFactory.getLimitOffset(params, lo);
		assertNull(lo.getLimit());
		assertNull(lo.getOffset());
		
		params.addParam(ParamsFactory.OFFSET, "asdf");
		ParamsFactory.getLimitOffset(params, lo);
		assertNull(lo.getLimit());
		assertNull(lo.getOffset());
		
		params = new Params();
		params.addParam(ParamsFactory.OFFSET, 123);
		ParamsFactory.getLimitOffset(params, lo);
		assertEquals(new Integer(123), lo.getOffset());
		
		params = new Params();
		Params limitParams = new Params();
		params.addParam(ParamsFactory.OFFSET, 123, limitParams);
		limitParams.addParam(ParamsFactory.NUM_ROWS, 100);
		ParamsFactory.getLimitOffset(params, lo);
		assertEquals(new Integer(123), lo.getOffset());
		assertEquals(new Integer(100), lo.getLimit());
	}
}
