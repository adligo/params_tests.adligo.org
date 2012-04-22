package org.adligo.models.params.client;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.jse.util.JSECommonInit;
import org.adligo.tests.ATest;

public class Base64Tests extends ATest {
	private static final Log log = LogFactory.getLog(Base64Tests.class);

	public void setUp() {
		JSECommonInit.callLogDebug("Base64Tests");
	}
	
	public void testUnsign() {
		for (byte i = Byte.MIN_VALUE; i < Byte.MAX_VALUE; i++) {
			if (i < 0) {
				assertEquals(256 + i, Base64.unsign(i));
			} else if (i > 0) {
				assertEquals(i, Base64.unsign(i));
			} else {
				assertEquals(0, Base64.unsign(i));
			}
		}
		assertEquals(127, Base64.unsign(Byte.MAX_VALUE));
	}
	
	public void testSingleByteEncode() {
		
		byte [] bytes = toBytes(0);
		String encoded = Base64.encode(bytes);
		assertEquals("AA==", encoded);
		
		bytes = toBytes(1);
		encoded = Base64.encode(bytes);
		assertEquals("AQ==", encoded);
		
		bytes = toBytes(2);
		encoded = Base64.encode(bytes);
		assertEquals("Ag==", encoded);
		
		bytes = toBytes(3);
		encoded = Base64.encode(bytes);
		assertEquals("Aw==", encoded);
		
		bytes = toBytes(4);
		encoded = Base64.encode(bytes);
		assertEquals("BA==", encoded);
		
		bytes = toBytes(5);
		encoded = Base64.encode(bytes);
		assertEquals("BQ==", encoded);
		
		bytes = toBytes(6);
		encoded = Base64.encode(bytes);
		assertEquals("Bg==", encoded);
		
		bytes = toBytes(7);
		encoded = Base64.encode(bytes);
		assertEquals("Bw==", encoded);
		
		bytes = toBytes(8);
		encoded = Base64.encode(bytes);
		assertEquals("CA==", encoded);
		
		bytes = toBytes(9);
		encoded = Base64.encode(bytes);
		assertEquals("CQ==", encoded);
		
		bytes = toBytes(10);
		encoded = Base64.encode(bytes);
		assertEquals("Cg==", encoded);
		
		bytes = toBytes(11);
		encoded = Base64.encode(bytes);
		assertEquals("Cw==", encoded);
		
		bytes = toBytes(12);
		encoded = Base64.encode(bytes);
		assertEquals("DA==", encoded);
		
		bytes = toBytes(13);
		encoded = Base64.encode(bytes);
		assertEquals("DQ==", encoded);
		
		bytes = toBytes(14);
		encoded = Base64.encode(bytes);
		assertEquals("Dg==", encoded);
		
		bytes = toBytes(15);
		encoded = Base64.encode(bytes);
		assertEquals("Dw==", encoded);
		
		bytes = toBytes(16);
		encoded = Base64.encode(bytes);
		assertEquals("EA==", encoded);
		
		
		bytes = toBytes(17);
		encoded = Base64.encode(bytes);
		assertEquals("EQ==", encoded);
		
		bytes = toBytes(18);
		encoded = Base64.encode(bytes);
		assertEquals("Eg==", encoded);
		
		bytes = toBytes(19);
		encoded = Base64.encode(bytes);
		assertEquals("Ew==", encoded);
		
		bytes = toBytes(20);
		encoded = Base64.encode(bytes);
		assertEquals("FA==", encoded);
	}
	
	static byte[] toBytes(int p) {
		return  new byte [] {toUnsignedByte(p)};
	}

	static byte[] toBytes(int [] p) {
		 byte [] toRet = new byte[p.length];
		 for (int i = 0; i < toRet.length; i++) {
			toRet[i] = toUnsignedByte(p[i]);
		}
		 return toRet;
	}
	
	public void testDoubleByteEncode() {
		
		byte [] bytes = toBytes(new int [] {0, 0});
		String encoded = Base64.encode(bytes);
		assertEquals("AAA=", encoded);
		
		bytes = toBytes(new int [] {0, 1});
		encoded = Base64.encode(bytes);
		assertEquals("AAB=", encoded);
		
		bytes = toBytes(new int [] {0, 2});
		encoded = Base64.encode(bytes);
		assertEquals("AAC=", encoded);
		
		bytes = toBytes(new int [] {0, 3});
		encoded = Base64.encode(bytes);
		assertEquals("AAD=", encoded);
		
		bytes = toBytes(new int [] {0, 4});
		encoded = Base64.encode(bytes);
		assertEquals("AAE=", encoded);
		
		bytes = toBytes(new int [] {0, 15});
		encoded = Base64.encode(bytes);
		assertEquals("AAP=", encoded);
		
		bytes = toBytes(new int [] {0, 16});
		encoded = Base64.encode(bytes);
		assertEquals("ABA=", encoded);
		
		bytes = toBytes(new int [] {0, 17});
		encoded = Base64.encode(bytes);
		assertEquals("ABB=", encoded);
		
		bytes = toBytes(new int [] {0, 18});
		encoded = Base64.encode(bytes);
		assertEquals("ABC=", encoded);
		
		bytes = toBytes(new int [] {1, 1});
		encoded = Base64.encode(bytes);
		assertEquals("AQB=", encoded);
		
		bytes = toBytes(new int [] {1, 2});
		encoded = Base64.encode(bytes);
		assertEquals("AQC=", encoded);
		
		bytes = toBytes(new int [] {2, 1});
		encoded = Base64.encode(bytes);
		assertEquals("AgB=", encoded);
		
		bytes = toBytes(new int [] {2, 2});
		encoded = Base64.encode(bytes);
		assertEquals("AgC=", encoded);
		
		bytes = toBytes(11);
		encoded = Base64.encode(bytes);
		assertEquals("Cw==", encoded);
		
		bytes = toBytes(12);
		encoded = Base64.encode(bytes);
		assertEquals("DA==", encoded);
		
		bytes = toBytes(13);
		encoded = Base64.encode(bytes);
		assertEquals("DQ==", encoded);
		
		bytes = toBytes(14);
		encoded = Base64.encode(bytes);
		assertEquals("Dg==", encoded);
		
		bytes = toBytes(15);
		encoded = Base64.encode(bytes);
		assertEquals("Dw==", encoded);
		
		bytes = toBytes(16);
		encoded = Base64.encode(bytes);
		assertEquals("EA==", encoded);
		
		
		bytes = toBytes(17);
		encoded = Base64.encode(bytes);
		assertEquals("EQ==", encoded);
		
		bytes = toBytes(18);
		encoded = Base64.encode(bytes);
		assertEquals("Eg==", encoded);
		
		bytes = toBytes(19);
		encoded = Base64.encode(bytes);
		assertEquals("Ew==", encoded);
		
		bytes = toBytes(20);
		encoded = Base64.encode(bytes);
		assertEquals("FA==", encoded);
	}

	static byte toUnsignedByte(int p) {
		if (p < 0 || p > 255) {
			throw new IllegalArgumentException("toUnsignedByte only accepts ints between 0 and 255");
		}
		return new Integer(p).byteValue();
	}

	
	public void testWikipediaExamples() throws Exception {
		String input = new String("pleasure.".getBytes("ASCII"), "ASCII");
		String output = Base64.encode(input);
		assertEquals("cGxlYXN1cmUu", output);
		
		input = new String("leasure.".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		//i actually think the wikipedia article is wrong here
		// the period ASCII hex is 2e
		// which equates to 0010 0111
		// also it has 56->char(4)
		// and the highest a 4 bit number could get is 8+4+2+1=15
		assertEquals("bGVhc3VyZSO=", output);
		
		input = new String("easure.".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("ZWFzdXJlLg==", output);
		
		input = new String("asure.".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("YXN1cmUu", output);
		
		input = new String("sure.".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("c3VyZSO=", output);
		
		
		//
		input = new String("any carnal pleasure.".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("YW55IGNhcm5hbCBwbGVhc3VyZSO=", output);
		
		input = new String("any carnal pleasure".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("YW55IGNhcm5hbCBwbGVhc3VyZQ==", output);
		
		input = new String("any carnal pleasur".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("YW55IGNhcm5hbCBwbGVhc3Vy", output);
		//according to 
		//http://www.robelle.com/smugbook/ascii.html
		//the hex for u is 75
		//or 01110101 as a binary string and 5 is F according to the charstrings
		// which should match with the BASE_64_CHARS
		input = new String("su".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("c3F=", output);
		
		input = new String("any carnal pleasu".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("YW55IGNhcm5hbCBwbGVhc3F=", output);
		
		input = new String("any carnal pleas".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("YW55IGNhcm5hbCBwbGVhcw==", output);
	}
}
