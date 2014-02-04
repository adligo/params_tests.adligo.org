package org.adligo.models.params.client;

import java.util.Date;

import org.adligo.tests.ATest;

public class ParamTests extends ATest {

	public void testValuesMatchTypes() {
		Param param = new Param();
		
		param.setValue("str");
		assertValues(new Object[] {"str"}, param);
		assertValueTypes(new ValueType[] {ValueTypes.STRING}, param);
		
		param.setValue(123);
		assertValues(new Object[] {123}, param);
		assertValueTypes(new ValueType[] {ValueTypes.INTEGER}, param);
		
		param.setValue((short) 123);
		assertValues(new Object[] {(short) 123}, param);
		assertValueTypes(new ValueType[] {ValueTypes.SHORT}, param);
		
		param.setValue((long) 123);
		assertValues(new Object[] {(long) 123}, param);
		assertValueTypes(new ValueType[] {ValueTypes.LONG}, param);
		
		param.setValue( 123.123);
		assertValues(new Object[] { 123.123}, param);
		assertValueTypes(new ValueType[] {ValueTypes.DOUBLE}, param);
		
		param.setValue((float) 123.123);
		assertValues(new Object[] {(float) 123.123}, param);
		assertValueTypes(new ValueType[] {ValueTypes.FLOAT}, param);
		
		param.setValue(new Date(0));
		assertValues(new Object[] {new Date(0)}, param);
		assertValueTypes(new ValueType[] {ValueTypes.DATE}, param);
		
		param.setValue(true);
		assertValues(new Object[] {true}, param);
		assertValueTypes(new ValueType[] {ValueTypes.BOOLEAN}, param);
		
		param = new Param();
		param.addValue("str");
		param.addValue("str");
		assertValues(new Object[] {"str","str"}, param);
		assertValueTypes(new ValueType[] {ValueTypes.STRING, ValueTypes.STRING}, param);
		
		param = new Param();
		param.addValue(321);
		param.addValue(321);
		assertValues(new Object[] {321,321}, param);
		assertValueTypes(new ValueType[] {ValueTypes.INTEGER, ValueTypes.INTEGER}, param);
		
		param = new Param();
		param.addValue((short) 321);
		param.addValue((short) 321);
		assertValues(new Object[] {(short) 321,(short) 321}, param);
		assertValueTypes(new ValueType[] {ValueTypes.SHORT, ValueTypes.SHORT}, param);
		
		param = new Param();
		param.addValue((long) 321);
		param.addValue((long) 321);
		assertValues(new Object[] {(long) 321,(long) 321}, param);
		assertValueTypes(new ValueType[] {ValueTypes.LONG, ValueTypes.LONG}, param);
		
		param = new Param();
		param.addValue(321.321);
		param.addValue(321.321);
		assertValues(new Object[] {321.321,321.321}, param);
		assertValueTypes(new ValueType[] {ValueTypes.DOUBLE, ValueTypes.DOUBLE}, param);
		
		param = new Param();
		param.addValue((float) 321.321);
		param.addValue((float) 321.321);
		assertValues(new Object[] {(float) 321.321,(float) 321.321}, param);
		assertValueTypes(new ValueType[] {ValueTypes.FLOAT, ValueTypes.FLOAT}, param);
		
		
		param = new Param();
		param.addValue(new Date(0));
		param.addValue(new Date(100));
		assertValues(new Object[] {new Date(0),new Date(100)}, param);
		assertValueTypes(new ValueType[] {ValueTypes.DATE, ValueTypes.DATE}, param);
		
		param = new Param();
		param.addValue(true);
		param.addValue(false);
		assertValues(new Object[] {true, false}, param);
		assertValueTypes(new ValueType[] {ValueTypes.BOOLEAN, 
					ValueTypes.BOOLEAN}, param);
		
	}

	private void assertValues(Object [] example, Param param) {
		Object [] actual = param.getValues();
		assertEquals(example.length, actual.length);
		for (int i = 0; i < actual.length; i++) {
			assertEquals(example[i], actual[i]);
		}
		
	}
	
	private void assertValueTypes(ValueType [] example, Param param) {
		ValueType [] actual = param.getValueTypes();
		assertEquals(example.length, actual.length);
		for (int i = 0; i < actual.length; i++) {
			assertEquals(example[i], actual[i]);
		}
	}
}
