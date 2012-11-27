/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.model;

import junit.framework.*;

public class PhotoFactoryTest extends TestCase {
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(UserStatusTest.class);
	}

	public PhotoFactoryTest(String name) {
		super(name);
	}
	
	public void testSingleton() {
		PhotoFactory test1 = PhotoFactory.INSTANCE;
		test1.createPhoto();
		PhotoFactory test2 = PhotoFactory.INSTANCE;
		test2.createPhoto();
		
		assertTrue(test1 == test2);
	
	}
	
}