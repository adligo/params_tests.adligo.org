package org.adligo.models.params.client;

import java.util.Date;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.util.client.I_Iterator;
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
	
	public void testGetContent() {
		String xmlChunk = "<object class=\"Param\" version=\"1.5\" name=\"StartRow\">" +
		"123</object>";			
		String content = Parser.getContent(xmlChunk, XMLObject.OBJECT_HEADER,XMLObject.OBJECT_ENDER);
		assertEquals("123", content);
	}
	
	public void testStripComments() {
		
		String result = Parser.stripComments(
				"<foo <!-- > < --> > <!--</foo>");
		assertEquals("<foo  > ", result);
		
		result = Parser.stripComments(
				"<foo <!-- > < --> > <!--</foo> --> <bar/></foo>");
		assertEquals("<foo  >  <bar/></foo>", result);
	}
	
	public void testGetTagNextInfoTagWithOutEnder() {
		
		TagInfo result = Parser.getNextTagInfo("<foo/>", 0);
		assertEquals("foo", result.getTagName());
		assertEquals(false, result.hasEnder());
		assertEquals(0, result.getHeaderStart());
		assertEquals(5, result.getHeaderEnd());
		
		result = Parser.getNextTagInfo("<foobar/><bar/>", 0);
		assertEquals("foobar", result.getTagName());
		assertEquals(false, result.hasEnder());
		assertEquals(0, result.getHeaderStart());
		assertEquals(8, result.getHeaderEnd());
	}
	
	public void testGetTagNextInfoTagWithEnder() {
		
		TagInfo result = Parser.getNextTagInfo("<foo></foo>", 0);
		assertEquals("foo", result.getTagName());
		assertEquals(true, result.hasEnder());
		assertEquals(0, result.getHeaderStart());
		assertEquals(4, result.getHeaderEnd());
		assertEquals(5, result.getEnderStart());
		assertEquals(10, result.getEnderEnd());
	}
	
	public void testGetTagWithNestedAndEnder() {	
		TagInfo result = Parser.getNextTagInfo("<foo><bar/></foo>", 0);
		assertEquals("foo", result.getTagName());
		assertEquals(true, result.hasEnder());
		assertEquals(0, result.getHeaderStart());
		assertEquals(4, result.getHeaderEnd());
		assertEquals(11, result.getEnderStart());
		assertEquals(16, result.getEnderEnd());
		

		I_Iterator it = result.getChildren();
		assertTrue(it.hasNext());
		TagInfo child = (TagInfo) it.next();
		assertEquals("bar", child.getTagName());
		assertEquals(false, child.hasEnder());
		assertEquals(5, child.getHeaderStart());
		assertEquals(10, child.getHeaderEnd());
	}
	
	public void testGetTagNextInfoTagStartingInMiddle() {
		
		TagInfo result = Parser.getNextTagInfo("<foo><bar/></foo>", 1);
		assertEquals("bar", result.getTagName());
		assertEquals(false, result.hasEnder());
		assertEquals(5, result.getHeaderStart());
		assertEquals(10, result.getHeaderEnd());
		
		
		
	}
	
	public void testGetTagMalformedTag() {
		
		TagInfo result = Parser.getNextTagInfo("<foo><bar></foo></bar>", 1);
		assertEquals("bar", result.getTagName());
		assertEquals(true, result.hasEnder());
		assertEquals(5, result.getHeaderStart());
		assertEquals(9, result.getHeaderEnd());
		assertEquals(16, result.getEnderStart());
		assertEquals(21, result.getEnderEnd());
	}
	
	public void testGetTagMalformedNoEndTag() {
		
		TagInfo result = Parser.getNextTagInfo(
				"<foo><bar><foo></bar></foo>", 6);
		assertEquals("foo", result.getTagName());
		assertEquals(true, result.hasEnder());
		assertEquals(10, result.getHeaderStart());
		assertEquals(14, result.getHeaderEnd());
		assertEquals(21, result.getEnderStart());
		assertEquals(26, result.getEnderEnd());
	}
	

	public void testGetTagNestedTags() {
		
		TagInfo result = Parser.getNextTagInfo(
				"<foo><bar><foo></foo><foo></foo></bar></foo>", 0);
		assertEquals("foo", result.getTagName());
		assertEquals(true, result.hasEnder());
		assertEquals(0, result.getHeaderStart());
		assertEquals(4, result.getHeaderEnd());
		assertEquals(38, result.getEnderStart());
		assertEquals(43, result.getEnderEnd());
		
		I_Iterator it =  result.getChildren();
		assertTrue(it.hasNext());
		
		TagInfo bar = (TagInfo)  it.next();
		assertFalse(it.hasNext());
		assertEquals(true, bar.hasEnder());
		assertEquals(5, bar.getHeaderStart());
		assertEquals(9, bar.getHeaderEnd());
		assertEquals(32, bar.getEnderStart());
		assertEquals(37, bar.getEnderEnd());
		
		it =  bar.getChildren();
		assertTrue(it.hasNext());
		
		TagInfo foo1 = (TagInfo)  it.next();
		assertTrue(it.hasNext());
		
		TagInfo foo2 = (TagInfo)  it.next();
		assertFalse(it.hasNext());
		
		assertEquals(true, foo1.hasEnder());
		assertEquals(10, foo1.getHeaderStart());
		assertEquals(14, foo1.getHeaderEnd());
		assertEquals(15, foo1.getEnderStart());
		assertEquals(20, foo1.getEnderEnd());
		
		assertEquals(true, foo2.hasEnder());
		assertEquals(21, foo2.getHeaderStart());
		assertEquals(25, foo2.getHeaderEnd());
		assertEquals(26, foo2.getEnderStart());
		assertEquals(31, foo2.getEnderEnd());
		
	}
	
	public void testStringList() {
		String xml = "<L>" + XMLBuilder.DOS_LINE_FEED
				+ XMLBuilder.SPACE_INDENT + "<s>a</s>" + XMLBuilder.DOS_LINE_FEED
				+ XMLBuilder.SPACE_INDENT + "<s>b</s>" + XMLBuilder.DOS_LINE_FEED
				+ XMLBuilder.SPACE_INDENT + "<s>c</s>" + XMLBuilder.DOS_LINE_FEED
				+ "</L>" + XMLBuilder.DOS_LINE_FEED;
		TagInfo info = Parser.getNextTagInfo(xml, 0);
		assertNotNull(info);
		
		assertEquals("L", info.getTagName());
		assertTrue(info.hasEnder());
		assertEquals(0, info.getHeaderStart());
		assertEquals(2, info.getHeaderEnd());
		assertEquals(44, info.getEnderStart());
		assertEquals(47, info.getEnderEnd());
		
		I_Iterator it =  info.getChildren();
		assertTrue(it.hasNext());
		TagInfo stringTag1 = (TagInfo) it.next();
		
		assertTrue(it.hasNext());
		TagInfo stringTag2 = (TagInfo) it.next();
		
		assertTrue(it.hasNext());
		TagInfo stringTag3= (TagInfo) it.next();
		assertFalse(it.hasNext());
		
		assertEquals("s", stringTag1.getTagName());
		assertTrue(stringTag1.hasEnder());
		assertEquals(8, stringTag1.getHeaderStart());
		assertEquals(10, stringTag1.getHeaderEnd());
		assertEquals(12, stringTag1.getEnderStart());
		assertEquals(15, stringTag1.getEnderEnd());
		
		assertEquals("s", stringTag2.getTagName());
		assertTrue(stringTag2.hasEnder());
		assertEquals(21, stringTag2.getHeaderStart());
		assertEquals(23, stringTag2.getHeaderEnd());
		assertEquals(25, stringTag2.getEnderStart());
		assertEquals(28, stringTag2.getEnderEnd());	
		
		assertEquals("s", stringTag3.getTagName());
		assertTrue(stringTag3.hasEnder());
		assertEquals(34, stringTag3.getHeaderStart());
		assertEquals(36, stringTag3.getHeaderEnd());
		assertEquals(38, stringTag3.getEnderStart());
		assertEquals(41, stringTag3.getEnderEnd());	
	}
}
