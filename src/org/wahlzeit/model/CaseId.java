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

import java.util.Random;


public class CaseId {
	
	public static final CaseId NULL_ID = new CaseId(0);
	
	private final int id;
	
	public CaseId (int id) {
		this.id = id;
	}
	
	public int asInt() {
		return id;
	}
	
	public CaseId next() {
		return new CaseId(id + 1);
	}
	
	public CaseId prev() {
		return new CaseId(id - 1);
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if ( !(obj instanceof CaseId) ) {
			return false;
		}
		CaseId other = (CaseId) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString () {
		return Integer.toString(id);
	}
	
}