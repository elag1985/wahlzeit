package org.wahlzeit.model;

import junit.framework.TestCase;

public class FlagReasonTest extends TestCase{
	public static void main(String[] args) {
		junit.textui.TestRunner.run(UserStatusTest.class);
	}

	public FlagReasonTest(String name) {
		super(name);
	}

	public void testGetFromInt() {
		assertTrue(FlagReason.getFromInt(FlagReason.MISMATCH.asInt()) == FlagReason.MISMATCH);
		assertTrue(FlagReason.getFromInt(FlagReason.OFFENSIVE.asInt()) == FlagReason.OFFENSIVE);
		assertTrue(FlagReason.getFromInt(FlagReason.COPYRIGHT.asInt()) == FlagReason.COPYRIGHT);
		assertTrue(FlagReason.getFromInt(FlagReason.OTHER.asInt()) == FlagReason.OTHER);
	}

	public void testGetFromString() {
		assertTrue(FlagReason.getFromString(FlagReason.MISMATCH.asString()) == FlagReason.MISMATCH);
		assertTrue(FlagReason.getFromString(FlagReason.OFFENSIVE.asString()) == FlagReason.OFFENSIVE);
		assertTrue(FlagReason.getFromString(FlagReason.COPYRIGHT.asString()) == FlagReason.COPYRIGHT);
		assertTrue(FlagReason.getFromString(FlagReason.OTHER.asString()) == FlagReason.OTHER);
	}
	
	public void testNonValidValues() {
		try {
			FlagReason.getFromInt(-1);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		
		try {
			FlagReason.getFromInt(4);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}


}
