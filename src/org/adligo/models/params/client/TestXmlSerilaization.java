package org.adligo.models.params.client;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.tests.ATest;

public class TestXmlSerilaization extends ATest {
	private static final Log log = LogFactory.getLog(TestXmlSerilaization.class);
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	public void testSerliaization() throws Exception {
		I_MultipleParamsObject params = getParams();
		
		String asXml = params.writeXML();
		if (log.isDebugEnabled()) {
			log.debug("wrote \n"  + asXml);
		}
		URL url = this.getClass().getResource("/org/adligo/models/params/client/serliaization.txt");
		InputStream is = url.openStream();
		StringBuilder str = new StringBuilder();
        byte b[] = new byte[1];

        while ( is.read(b) != -1 ) {
        	if (b[0] == '\n') {
        		str.append(I_XMLBuilder.DOS_LINE_FEED);
        	} else if (b[0] != '\r') {
        		str.append(new String(b));
        	}
        }
        is.close();
        String content = str.toString();
		
		assertEquals(asXml, content);
		/*
		I_LogMutant log = (I_LogMutant) LogFactory.getLog(Param.class);
		log.setLevel(DeferredLog.LOG_LEVEL_DEBUG);
		*/
		Object result = XMLObject.readXML(asXml);
		assertTrue(result instanceof Params);
		Params newParams = (Params) result;


		if (log.isDebugEnabled()) {
			log.debug("read in to \n\n\n"  + newParams.writeXML());
		}
		//assertEquals(params.writeXML(), newParams.writeXML());
		assertEquals(params, newParams);
	}
	
	public void testStringParamSerilaization() throws Exception {
		I_TemplateParams params = getStringParam();
		
		String asXml = params.writeXML();
		if (log.isDebugEnabled()) {
			log.debug("wrote \n"  + asXml);
		}
		/*
		I_LogMutant log = (I_LogMutant) LogFactory.getLog(Param.class);
		log.setLevel(DeferredLog.LOG_LEVEL_DEBUG);
		*/
		Object result = XMLObject.readXML(asXml);
		assertTrue(result instanceof Param);
		Param newParams = (Param) result;


		if (log.isDebugEnabled()) {
			log.debug("read in to \n\n\n"  + newParams.writeXML());
		}
		assertEquals(params, newParams);
	}

	public void testLongParamSerilaization() throws Exception {
		I_TemplateParams params = getLongParam();
		
		String asXml = params.writeXML();
		if (log.isDebugEnabled()) {
			log.debug("wrote \n"  + asXml);
		}
		/*
		I_LogMutant log = (I_LogMutant) LogFactory.getLog(Param.class);
		log.setLevel(DeferredLog.LOG_LEVEL_DEBUG);
		*/
		Object result = XMLObject.readXML(asXml);
		assertTrue(result instanceof Param);
		Param newParams = (Param) result;


		if (log.isDebugEnabled()) {
			log.debug("read in to \n\n\n"  + newParams.writeXML());
		}
		assertEquals(params, newParams);
	}
	
	public void testTwoParamSerilaization() throws Exception {
		Params params = new Params();
		params.addParam(getStringParam());
		params.addParam(getLongParam());
		
		String asXml = params.writeXML();
		if (log.isDebugEnabled()) {
			log.debug("wrote \n"  + asXml);
		}
		/*
		I_LogMutant log = (I_LogMutant) LogFactory.getLog(Param.class);
		log.setLevel(DeferredLog.LOG_LEVEL_DEBUG);
		*/
		Object result = XMLObject.readXML(asXml);
		assertTrue(result instanceof Params);
		Params newParams = (Params) result;


		if (log.isDebugEnabled()) {
			log.debug("read in to \n\n\n"  + newParams.writeXML());
		}
		assertEquals(params, newParams);
	}
	
	public void testDoubleParamSerilaization() throws Exception {
		I_TemplateParams params = getDoubleParam();
		
		String asXml = params.writeXML();
		if (log.isDebugEnabled()) {
			log.debug("wrote \n"  + asXml);
		}
		/*
		I_LogMutant log = (I_LogMutant) LogFactory.getLog(Param.class);
		log.setLevel(DeferredLog.LOG_LEVEL_DEBUG);
		*/
		Object result = XMLObject.readXML(asXml);
		assertTrue(result instanceof Param);
		Param newParams = (Param) result;


		if (log.isDebugEnabled()) {
			log.debug("read in to \n\n\n"  + newParams.writeXML());
		}
		assertEquals(params, newParams);
	}
	
	public void testFloatParamSerilaization() throws Exception {
		I_TemplateParams params = getFloatParam();
		
		String asXml = params.writeXML();
		if (log.isDebugEnabled()) {
			log.debug("wrote \n"  + asXml);
		}
		/*
		I_LogMutant log = (I_LogMutant) LogFactory.getLog(Param.class);
		log.setLevel(DeferredLog.LOG_LEVEL_DEBUG);
		*/
		Object result = XMLObject.readXML(asXml);
		assertTrue(result instanceof Param);
		Param newParams = (Param) result;


		if (log.isDebugEnabled()) {
			log.debug("read in to \n\n\n"  + newParams.writeXML());
		}
		assertEquals(params, newParams);
	}
	
	public void testShortParamSerilaization() throws Exception {
		I_TemplateParams params = getShortParam();
		
		String asXml = params.writeXML();
		if (log.isDebugEnabled()) {
			log.debug("wrote \n"  + asXml);
		}
		/*
		I_LogMutant log = (I_LogMutant) LogFactory.getLog(Param.class);
		log.setLevel(DeferredLog.LOG_LEVEL_DEBUG);
		*/
		Object result = XMLObject.readXML(asXml);
		assertTrue(result instanceof Param);
		Param newParams = (Param) result;


		if (log.isDebugEnabled()) {
			log.debug("read in to \n\n\n"  + newParams.writeXML());
		}
		assertEquals(params, newParams);
	}
	
	public void testBooleanParamSerilaization() throws Exception {
		I_TemplateParams params = getBooleanParam();
		
		String asXml = params.writeXML();
		if (log.isDebugEnabled()) {
			log.debug("wrote \n"  + asXml);
		}
		/*
		I_LogMutant log = (I_LogMutant) LogFactory.getLog(Param.class);
		log.setLevel(DeferredLog.LOG_LEVEL_DEBUG);
		*/
		Object result = XMLObject.readXML(asXml);
		assertTrue(result instanceof Param);
		Param newParams = (Param) result;


		if (log.isDebugEnabled()) {
			log.debug("read in to \n\n\n"  + newParams.writeXML());
		}
		assertEquals(params, newParams);
	}
	
	public void testBigDecimalParamSerilaization() throws Exception {
		I_TemplateParams params = getBigDecimalParam();
		
		String asXml = params.writeXML();
		if (log.isDebugEnabled()) {
			log.debug("wrote \n"  + asXml);
		}
		/*
		I_LogMutant log = (I_LogMutant) LogFactory.getLog(Param.class);
		log.setLevel(DeferredLog.LOG_LEVEL_DEBUG);
		*/
		Object result = XMLObject.readXML(asXml);
		assertTrue(result instanceof Param);
		Param newParams = (Param) result;


		if (log.isDebugEnabled()) {
			log.debug("read in to \n\n\n"  + newParams.writeXML());
		}
		assertEquals(params, newParams);
	}
	
	
	public I_MultipleParamsObject getParams() throws ParseException {
		I_MultipleParamsObject params = new Params();
		Param startParam = new Param();
		startParam.setName("StartRow");
		startParam.setValue(10);
		params.addParam(startParam);
		
		Param endParam = new Param();
		endParam.setName("EndRow");
		endParam.setOperators(new String[] {"\"", "&"});
		endParam.setValue(20);
		params.addParam(endParam);
		
		Param selectParam = new Param();
		selectParam.setName("OnlyNameAndId");
		params.addParam(selectParam);
		
		Param whereParam = new Param();
		Params whereParams = new Params();
		whereParam.setParams(whereParams);
		whereParam.setName("Where");
		params.addParam(whereParam);
		
		whereParams.addParam(getStringParam());
		whereParams.addParam(getIntParam());
		whereParams.addParam(getDoubleParam());
		whereParams.addParam(getFloatParam());
		whereParams.addParam(getLongParam());
		whereParams.addParam(getShortParam());
		whereParams.addParam(getDateParam());
		whereParams.addParam(getBooleanParam());
		return params;
	}

	public Param getBooleanParam() {
		Param stringParam = new Param();
		stringParam.setName("Boolean");
		stringParam.setOperators(new String [] {"NOT", "IN"});
		stringParam.addValue(true);
		return stringParam;
	}
	
	public Param getBigDecimalParam() {
		Param stringParam = new Param();
		stringParam.setName("BigDecimal");
		stringParam.setOperators(new String [] {"="});
		stringParam.addValue(new BigDecimal("101.01"));
		return stringParam;
	}
	
	public Param getBigIntegerParam() {
		Param stringParam = new Param();
		stringParam.setName("BigInteger");
		stringParam.setOperators(new String [] {"NOT", "IN"});
		stringParam.addValue(new BigInteger("99999"));
		return stringParam;
	}
	
	public Param getStringParam() {
		Param stringParam = new Param();
		stringParam.setName("String");
		stringParam.setOperators(new String [] {"NOT", "IN"});
		stringParam.addValue("a");
		stringParam.addValue("b");
		stringParam.addValue("c");
		return stringParam;
	}

	public Param getIntParam() {
		Param intParam = new Param();
		intParam.setName("Integer");
		intParam.setOperator(">=");
		intParam.setValue(101);
		return intParam;
	}

	public Param getDoubleParam() {
		Param doubleParam = new Param();
		doubleParam.setName("Double");
		doubleParam.setOperator("<");
		doubleParam.setValue(1.1);
		return doubleParam;
	}

	public Param getFloatParam() {
		Param floatParam = new Param();
		floatParam.setName("Float");
		floatParam.setOperator("<>");
		floatParam.setValue(new Float(1.1));
		return floatParam;
	}

	public Param getLongParam() {
		Param longParam = new Param();
		longParam.setName("Long");
		longParam.setOperator("=");
		longParam.setValue(new Long(2049820498 * 10));
		return longParam;
	}

	public Param getShortParam() {
		Param shortParam = new Param();
		shortParam.setName("Short");
		shortParam.setOperator(">");
		shortParam.setValue((short) 10);
		return shortParam;
	}

	public Param getDateParam() throws ParseException {
		Param dateParam = new Param();
		dateParam.setName("Date");
		dateParam.setOperator("between");
		dateParam.addValue(sdf.parse("12/01/1999"));
		dateParam.addValue(sdf.parse("12/01/2001"));
		return dateParam;
	}
}
