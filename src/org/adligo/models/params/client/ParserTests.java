package org.adligo.models.params.client;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.tests.ATest;

public class ParserTests extends ATest {
	private static final Log log = LogFactory.getLog(ParserTests.class);
	
	
	public void testGetTagIndexes() {
		String xmlChunk = "<object class=\"Param\" version=\"1.5\" name=\"StartRow\"> " +
		"      <object name=\"values\" >" +
		"         <object class=\"Integer\">10</object>" +
		"      </object>" +
		"</object>";
		
		int [] indexes = Parser.getTagIndexs(xmlChunk, XMLObject.OBJECT_HEADER, XMLObject.OBJECT_ENDER);
		//System.out.println(indexes[0] + "," + indexes[1]);
		assertEquals(0, indexes[0]);
		assertEquals(150, indexes[1]);
		
		int [] header = Parser.getTagIndexs(xmlChunk, XMLObject.OBJECT_HEADER, ">");
		//logIntArray(header);
		assertEquals(0, header[0]);
		assertEquals(52, header[1]);
		
		String innerChunk = xmlChunk.substring(header[1], indexes[1] - XMLObject.OBJECT_ENDER.length());
		//log.warn(innerChunk);
		indexes = Parser.getTagIndexs(innerChunk, XMLObject.OBJECT_HEADER, XMLObject.OBJECT_ENDER);
		assertEquals(7, indexes[0]);
		assertEquals(89, indexes[1]);
		
		header = Parser.getTagIndexs(innerChunk, XMLObject.OBJECT_HEADER, ">");
		assertEquals(7, header[0]);
		assertEquals(30, header[1]);
		
		innerChunk = innerChunk.substring(header[1], indexes[1] - XMLObject.OBJECT_ENDER.length());
		//log.warn(innerChunk);
		
		assertEquals("         <object class=\"Integer\">10</object>      ", innerChunk);
		
	}
	
	public void testGetTagIndexesEnderInHeader() {
		String methodChunk = "<object name=\"Integer\" />";
		
		int [] indexes = Parser.getTagIndexs(methodChunk, XMLObject.OBJECT_HEADER, XMLObject.OBJECT_ENDER);
		assertEquals(0, indexes[0]);
		assertEquals(methodChunk.length(), indexes[1]);
		//log.warn(methodChunk.substring(0, methodChunk.length()));
		
		String xmlChunk = "<object class=\"Param\" version=\"1.5\" name=\"StartRow\"> " +
		methodChunk + 
		"      <object name=\"values\" >" +
		"         <object class=\"Integer\">10</object>" +
		"      </object>" +
		"</object>";
		
		indexes = Parser.getTagIndexs(xmlChunk, XMLObject.OBJECT_HEADER, XMLObject.OBJECT_ENDER);
		assertEquals(0, indexes[0]);
		assertEquals(xmlChunk.length(), indexes[1]);
		
	}
	
	public void logIntArray(int [] p) {
		log.warn(p[0] + "," + p[1]);
	}
}
