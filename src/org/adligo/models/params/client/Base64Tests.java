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
		assertEquals("AAE=", encoded);
		
		bytes = toBytes(new int [] {0, 2});
		encoded = Base64.encode(bytes);
		assertEquals("AAI=", encoded);
		
		bytes = toBytes(new int [] {0, 3});
		encoded = Base64.encode(bytes);
		assertEquals("AAM=", encoded);
		
		bytes = toBytes(new int [] {0, 4});
		encoded = Base64.encode(bytes);
		assertEquals("AAQ=", encoded);
		
		bytes = toBytes(new int [] {0, 15});
		encoded = Base64.encode(bytes);
		assertEquals("AA8=", encoded);
		
		bytes = toBytes(new int [] {0, 16});
		encoded = Base64.encode(bytes);
		assertEquals("ABA=", encoded);
		
		bytes = toBytes(new int [] {0, 17});
		encoded = Base64.encode(bytes);
		assertEquals("ABE=", encoded);
		
		bytes = toBytes(new int [] {0, 18});
		encoded = Base64.encode(bytes);
		assertEquals("ABI=", encoded);
		
		bytes = toBytes(new int [] {1, 1});
		encoded = Base64.encode(bytes);
		assertEquals("AQE=", encoded);
		
		bytes = toBytes(new int [] {1, 2});
		encoded = Base64.encode(bytes);
		assertEquals("AQI=", encoded);
		
		bytes = toBytes(new int [] {2, 1});
		encoded = Base64.encode(bytes);
		assertEquals("AgE=", encoded);
		
		bytes = toBytes(new int [] {2, 2});
		encoded = Base64.encode(bytes);
		assertEquals("AgI=", encoded);
	}

	static byte toUnsignedByte(int p) {
		if (p < 0 || p > 255) {
			throw new IllegalArgumentException("toUnsignedByte only accepts ints between 0 and 255");
		}
		return new Integer(p).byteValue();
	}

	
	public void testEncodeWikipediaExamples() throws Exception {
		String input = new String("pleasure.".getBytes("ASCII"), "ASCII");
		String output = Base64.encode(input);
		assertEquals("cGxlYXN1cmUu", output);
		
		input = new String("leasure.".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("bGVhc3VyZS4=", output);
		
		input = new String("easure.".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("ZWFzdXJlLg==", output);
		
		input = new String("asure.".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("YXN1cmUu", output);
		
		input = new String("sure.".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("c3VyZS4=", output);
		
		input = new String("any carnal pleasure.".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("YW55IGNhcm5hbCBwbGVhc3VyZS4=", output);
		
		input = new String("any carnal pleasure".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("YW55IGNhcm5hbCBwbGVhc3VyZQ==", output);
		
		input = new String("any carnal pleasur".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("YW55IGNhcm5hbCBwbGVhc3Vy", output);

		input = new String("su".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("c3U=", output);
		
		input = new String("any carnal pleasu".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("YW55IGNhcm5hbCBwbGVhc3U=", output);
		
		input = new String("any carnal pleas".getBytes("ASCII"), "ASCII");
		output = Base64.encode(input);
		assertEquals("YW55IGNhcm5hbCBwbGVhcw==", output);
	}
	
	
	public void testSingleByteDecode() {
		
		byte [] bytes = Base64.decode("AA==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 0, bytes[0]);
		
		bytes = Base64.decode("AQ==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 1, bytes[0]);
		
		
		bytes = Base64.decode("Ag==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 2, bytes[0]);
		
		bytes = Base64.decode("Aw==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 3, bytes[0]);
		
		bytes = Base64.decode("BA==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 4, bytes[0]);
		
		bytes = Base64.decode("BQ==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 5, bytes[0]);
		
		bytes = Base64.decode("Bg==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 6, bytes[0]);
		
		bytes = Base64.decode("Bw==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 7, bytes[0]);
		
		bytes = Base64.decode("CA==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 8, bytes[0]);
		
		bytes = Base64.decode("CQ==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 9, bytes[0]);
		
		bytes = Base64.decode("Cg==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 10, bytes[0]);
		
		bytes = Base64.decode("Cw==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 11, bytes[0]);
		
		bytes = Base64.decode("DA==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 12, bytes[0]);
		
		bytes = Base64.decode("DQ==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 13, bytes[0]);
		
		bytes = Base64.decode("Dg==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 14, bytes[0]);
		
		bytes = Base64.decode("Dw==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 15, bytes[0]);
		
		bytes = Base64.decode("EA==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 16, bytes[0]);
		
		bytes = Base64.decode("EQ==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 17, bytes[0]);
		
		bytes = Base64.decode("Eg==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 18, bytes[0]);
		
		bytes = Base64.decode("Ew==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 19, bytes[0]);
		
		bytes = Base64.decode("FA==");
		assertEquals(1, bytes.length);
		assertEquals((byte) 20, bytes[0]);
		
	}
	
	
	public void testDoubleByteDecode() {
		
		byte [] bytes = Base64.decode("AAA=");
		assertEquals(2, bytes.length);
		assertEquals((byte) 0, bytes[0]);
		assertEquals((byte) 0, bytes[1]);
		
		bytes = Base64.decode("AAE=");
		assertEquals(2, bytes.length);
		assertEquals((byte) 0, bytes[0]);
		assertEquals((byte) 1, bytes[1]);

		bytes = Base64.decode("AAI=");
		assertEquals(2, bytes.length);
		assertEquals((byte) 0, bytes[0]);
		assertEquals((byte) 2, bytes[1]);
		
		bytes = Base64.decode("AAM=");
		assertEquals(2, bytes.length);
		assertEquals((byte) 0, bytes[0]);
		assertEquals((byte) 3, bytes[1]);
		
		bytes = Base64.decode("AAQ=");
		assertEquals(2, bytes.length);
		assertEquals((byte) 0, bytes[0]);
		assertEquals((byte) 4, bytes[1]);
		
		bytes = Base64.decode("AA8=");
		assertEquals(2, bytes.length);
		assertEquals((byte) 0, bytes[0]);
		assertEquals((byte) 15, bytes[1]);
		
		bytes = Base64.decode("ABA=");
		assertEquals(2, bytes.length);
		assertEquals((byte) 0, bytes[0]);
		assertEquals((byte) 16, bytes[1]);
		
		bytes = Base64.decode("ABE=");
		assertEquals(2, bytes.length);
		assertEquals((byte) 0, bytes[0]);
		assertEquals((byte) 17, bytes[1]);
		
		bytes = Base64.decode("ABI=");
		assertEquals(2, bytes.length);
		assertEquals((byte) 0, bytes[0]);
		assertEquals((byte) 18, bytes[1]);
		
		bytes = Base64.decode("AQE=");
		assertEquals(2, bytes.length);
		assertEquals((byte) 1, bytes[0]);
		assertEquals((byte) 1, bytes[1]);
		
		bytes = Base64.decode("AQI=");
		assertEquals(2, bytes.length);
		assertEquals((byte) 1, bytes[0]);
		assertEquals((byte) 2, bytes[1]);
		
		bytes = Base64.decode("AgE=");
		assertEquals(2, bytes.length);
		assertEquals((byte) 2, bytes[0]);
		assertEquals((byte) 1, bytes[1]);
		
		bytes = Base64.decode("AgI=");
		assertEquals(2, bytes.length);
		assertEquals((byte) 2, bytes[0]);
		assertEquals((byte) 2, bytes[1]);
		
	}
	
	public void testDecodeWikipediaExamples() throws Exception {
		byte [] bytes = Base64.decode("cGxlYXN1cmUu");
		assertEquals("pleasure.", new String(bytes, "ASCII"));
		
		bytes = Base64.decode("bGVhc3VyZS4=");
		assertEquals("leasure.", new String(bytes, "ASCII"));
		
		bytes = Base64.decode("ZWFzdXJlLg==");
		assertEquals("easure.", new String(bytes, "ASCII"));
		
		bytes = Base64.decode("YXN1cmUu");
		assertEquals("asure.", new String(bytes, "ASCII"));
		
		bytes = Base64.decode("c3VyZS4=");
		assertEquals("sure.", new String(bytes, "ASCII"));
		
		bytes = Base64.decode("YW55IGNhcm5hbCBwbGVhc3VyZS4=");
		assertEquals("any carnal pleasure.", new String(bytes, "ASCII"));
		
		bytes = Base64.decode("YW55IGNhcm5hbCBwbGVhc3VyZQ==");
		assertEquals("any carnal pleasure", new String(bytes, "ASCII"));
		
		bytes = Base64.decode("YW55IGNhcm5hbCBwbGVhc3Vy");
		assertEquals("any carnal pleasur", new String(bytes, "ASCII"));
		
		bytes = Base64.decode("c3U=");
		assertEquals("su", new String(bytes, "ASCII"));
		
		bytes = Base64.decode("YW55IGNhcm5hbCBwbGVhc3U=");
		assertEquals("any carnal pleasu", new String(bytes, "ASCII"));
		
		bytes = Base64.decode("YW55IGNhcm5hbCBwbGVhcw==");
		assertEquals("any carnal pleas", new String(bytes, "ASCII"));
		
	}
}
