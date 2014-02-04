package org.adligo.models.params.client;

import org.adligo.tests.ATest;

public class TagInfoTests extends ATest {

	public void testTagInfoExceptions() {
		TagInfoMutant mut = new TagInfoMutant();
		IllegalArgumentException caught = null;
		try {
			mut.validate();
		} catch (IllegalArgumentException x) {
			caught = x;
		}
		assertNotNull(caught);
		assertEquals(TagInfoMutant.TAG_INFO_REQUIRES_A_NON_EMPTY_NAME, caught.getMessage());
		
		mut.setTagName("a");
		caught = null;
		try {
			mut.validate();
		} catch (IllegalArgumentException x) {
			caught = x;
		}
		assertNotNull(caught);
		assertEquals(TagInfoMutant.TAG_INFO_INDEXES_MUST_BE_GREATER_THAN_OR_EQUAL_TO_ZERO, caught.getMessage());
		
		mut.setHeaderStart(0);
		mut.setHeaderEnd(-1);
		caught = null;
		try {
			mut.validate();
		} catch (IllegalArgumentException x) {
			caught = x;
		}
		assertNotNull(caught);
		assertEquals(TagInfoMutant.TAG_INFO_INDEXES_MUST_BE_GREATER_THAN_OR_EQUAL_TO_ZERO, caught.getMessage());
		
		
		mut.setHeaderStart(0);
		mut.setHeaderEnd(0);
		caught = null;
		try {
			mut.validate();
		} catch (IllegalArgumentException x) {
			caught = x;
		}
		assertNotNull(caught);
		assertEquals(TagInfoMutant.THE_START_TAG_START_INDEX_MUST_BE_BEFORE_THE_START_TAG_END_INDEX, caught.getMessage());
	}
	
	public void testEndTagExceptions() {
		TagInfoMutant mut = new TagInfoMutant();
		IllegalArgumentException caught = null;
		
		mut.setTagName("a");
		mut.setHeaderStart(0);
		mut.setHeaderEnd(3);
		mut.setEnderStart(-1);
		caught = null;
		try {
			mut.validate();
		} catch (IllegalArgumentException x) {
			caught = x;
		}
		assertNotNull(caught);
		assertEquals(TagInfoMutant.TAG_INFO_INDEXES_MUST_BE_GREATER_THAN_OR_EQUAL_TO_ZERO, caught.getMessage());
		
		mut = new TagInfoMutant();
		mut.setTagName("a");
		mut.setHeaderStart(0);
		mut.setHeaderEnd(3);
		mut.setEnderEnd(-1);
		caught = null;
		try {
			mut.validate();
		} catch (IllegalArgumentException x) {
			caught = x;
		}
		assertNotNull(caught);
		assertEquals(TagInfoMutant.TAG_INFO_INDEXES_MUST_BE_GREATER_THAN_OR_EQUAL_TO_ZERO, caught.getMessage());
		
		mut.setEnderEnd(-2);
		caught = null;
		try {
			mut.validate();
		} catch (IllegalArgumentException x) {
			caught = x;
		}
		assertNotNull(caught);
		assertEquals(TagInfoMutant.TAG_INFO_INDEXES_MUST_BE_GREATER_THAN_OR_EQUAL_TO_ZERO, caught.getMessage());
		
		mut.setEnderEnd(4);
		mut.setEnderStart(3);
		caught = null;
		try {
			mut.validate();
		} catch (IllegalArgumentException x) {
			caught = x;
		}
		assertNotNull(caught);
		assertEquals(TagInfoMutant.THE_START_TAG_END_INDEX_MUST_BE_BEFORE_THE_END_TAG_START_INDEX, caught.getMessage());
		
		mut.setEnderEnd(6);
		mut.setEnderStart(7);
		caught = null;
		try {
			mut.validate();
		} catch (IllegalArgumentException x) {
			caught = x;
		}
		assertNotNull(caught);
		assertEquals(TagInfoMutant.THE_END_TAG_START_INDEX_MUST_BE_BEFORE_THE_END_TAG_END_INDEX, caught.getMessage());
		
		mut.setEnderEnd(6);
		mut.setEnderStart(4);
		mut.setHasEnder(true);
		caught = null;
		mut.validate();
	}
}
