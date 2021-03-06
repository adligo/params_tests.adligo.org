package org.adligo.models.params_tests.shared;

import org.adligo.i.log.shared.Log;
import org.adligo.i.log.shared.LogFactory;
import org.adligo.models.params.shared.EightBit;
import org.adligo.tests.ATest;

public class EightBitTest extends ATest {
	private static final Log log = LogFactory.getLog(EightBitTest.class);
	
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
		
		EightBit eb = new EightBit("0000");
		assertEquals(0, eb.unsigned());
		eb = new EightBit("0001");
		assertEquals(1, eb.unsigned());
		eb = new EightBit("0010");
		assertEquals(2, eb.unsigned());
		eb = new EightBit("0011");
		assertEquals(3, eb.unsigned());
	}
	
	public void testEightBitStringConstructorAndUnsignedValue() {
		int counter = 0;
		for (int i = 0; i < 2; i++) {
			
			String binString = Integer.toBinaryString(i);
			if (i <= 1) {
				binString = "0000000" + binString;
			} else if (i <= 3) {
				binString = "000000" + binString;
			} else if (i <= 7) {
				binString = "00000" + binString;
			} else if (i <= 15) {
				binString = "0000" + binString;
			} else if (i <= 15) {
				binString = "000" + binString;
			} else if (i <= 31) {
				binString = "00" + binString;
			} else if (i <= 63) {
				binString = "0" + binString;
			}
			assertEquals(8, binString.length());
			EightBit eb = new EightBit(binString);
			assertEquals(counter, eb.unsigned());
			assertEquals(binString, eb.toOnesAndZeros());
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
	

	public void testToAscii() {
		int counter = 0;
		for (byte i = 0; i < Byte.MAX_VALUE; i++) {
			EightBit eb = new EightBit(i);
			assertEquals(counter, eb.unsigned());
			assertEquals(i, eb.toByte());
			
			log.debug("ascii was:" + eb.toCharAsciiUtf8());
			counter++;
		}
	}
}
