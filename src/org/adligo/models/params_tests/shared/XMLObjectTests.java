package org.adligo.models.params_tests.shared;

import org.adligo.models.params.shared.ClassInfo;
import org.adligo.models.params.shared.XMLObject;
import org.adligo.tests.ATest;

public class XMLObjectTests extends ATest {

	public void testGetClassInfo() {
		String xmlChunk = "<object class=\"Param\" version=\"1.5\" name=\"StartRow\"> " +
		"      <object name=\"values\" >" +
		"         <object class=\"Integer\">10</object>" +
		"      </object>" +
		"</object>";
		
		ClassInfo info = XMLObject.getClassInfo(xmlChunk);
		assertEquals("Param", info.getName());
		assertEquals(1.5, info.getVersion());
		
	}
}
