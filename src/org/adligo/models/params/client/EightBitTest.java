package org.adligo.models.params.client;

import org.adligo.tests.ATest;

public class EightBitTest extends ATest {

	public void testEightBitConstructorAndUnsignedValue() {
		int counter = 0;
		for (byte i = 0; i < Byte.MAX_VALUE; i++) {
			EightBit eb = new EightBit(i);
			assertEquals(counter, eb.unsigned());
			counter++;
		}
		for (int i = Byte.MAX_VALUE; i < 256; i++) {
			EightBit eb = new EightBit((byte) i);
			assertEquals(counter, eb.unsigned());
			counter++;
		}
	}
	
	
	public void testEightBitCopy() {
		
		int counter = 0;
		for (byte i = 0; i < Byte.MAX_VALUE; i++) {
			EightBit eb = new EightBit(0);
			
			eb.copy(counter, 8); 
			assertEquals(counter, eb.unsigned());
			counter++;
		}
		for (int i = Byte.MAX_VALUE; i < 256; i++) {
			EightBit eb = new EightBit(0);
			
			eb.copy(counter, 8); 
			assertEquals(counter, eb.unsigned());
			counter++;
		}
		
		int right = 1+2+4+8;
		EightBit eb = new EightBit(right);
		eb.copy(0, 4); 
		assertEquals(right, eb.unsigned());
		
		eb = new EightBit(right);
		eb.copy(1, 4); 
		assertEquals(right + 16, eb.unsigned());
		
		eb = new EightBit(right);
		eb.copy(2, 4); 
		assertEquals(right + 32, eb.unsigned());
	
		eb = new EightBit(right);
		eb.copy(3, 4); 
		assertEquals(right + 32 + 16, eb.unsigned());
		
		eb = new EightBit(right);
		eb.copy(4, 4); 
		assertEquals(right + 64, eb.unsigned());
		
		eb = new EightBit(right);
		eb.copy(5, 4); 
		assertEquals(right + 64 + 16, eb.unsigned());
		
		eb = new EightBit(right);
		eb.copy(6, 4); 
		assertEquals(right + 64 + 32, eb.unsigned());
		
		eb = new EightBit(right);
		eb.copy(7, 4); 
		assertEquals(right + 64 + 32 + 16, eb.unsigned());
	}
	
}
