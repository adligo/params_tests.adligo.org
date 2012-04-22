package org.adligo.models.params.client;

public class BinaryStringOperators {

	public static final void main(String [] args) {
		byte z = Byte.MIN_VALUE;
		int unsigned = Base64.unsign6bit(z);
		System.out.println("byte z "  + z + " unsigned " + unsigned);
		z =  (byte) (z >>> 2);
		unsigned = Base64.unsign6bit(z);
		System.out.println("byte z "  + z + " unsigned " + unsigned);
		
		/*
		for (byte i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
			System.out.println("byte " + i + " has " + Integer.toBinaryString(i));
			//insert a zero at the right, moving everything to the left
			byte b = (byte) (i << 1);
			System.out.println("<< 1 is " + Integer.toBinaryString(b));
			System.out.println("or " + b);
			//insert a 1 at the left moving everything to the right
			b = (byte) (i >>1);
			System.out.println(">> 1 is " + Integer.toBinaryString(b));
			System.out.println("or " + b);
			
			b = (byte) (i >>> 1);
			System.out.println(">>> 1 is " + Integer.toBinaryString(b));
			System.out.println("or " + b);
			
			b = (byte)  (( 0 & i) >> 1);
			System.out.println("(( 0 & i) >> 1 is " + Integer.toBinaryString(b));
			System.out.println("or " + b);
		}
		*/
	}
}
