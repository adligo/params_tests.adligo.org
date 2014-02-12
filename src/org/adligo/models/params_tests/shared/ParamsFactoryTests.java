package org.adligo.models.params_tests.shared;

import org.adligo.models.params.shared.LimitOffset;
import org.adligo.models.params.shared.Params;
import org.adligo.models.params.shared.ParamsFactory;
import org.adligo.tests.ATest;

public class ParamsFactoryTests extends ATest {

	public void testGetOffset() {
		LimitOffset lo = new LimitOffset();
		Params params = new Params();
		
		ParamsFactory.getLimitOffset(params, lo);
		assertEquals(-1, lo.getLimit());
		assertEquals(-1, lo.getOffset());
		
		params.addParam(ParamsFactory.OFFSET, "asdf");
		ParamsFactory.getLimitOffset(params, lo);
		assertEquals(-1, lo.getLimit());
		assertEquals(-1, lo.getOffset());
		
		params = new Params();
		params.addParam(ParamsFactory.OFFSET, 123);
		ParamsFactory.getLimitOffset(params, lo);
		assertEquals(123, lo.getOffset());
		
		params = new Params();
		Params limitParams = new Params();
		params.addParam(ParamsFactory.OFFSET, 123, limitParams);
		limitParams.addParam(ParamsFactory.NUM_ROWS, 100);
		ParamsFactory.getLimitOffset(params, lo);
		assertEquals(123, lo.getOffset());
		assertEquals(100, lo.getLimit());
	}
}
