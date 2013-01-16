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

import java.util.HashMap;

import org.wahlzeit.services.EmailAddress;

public class ClientCore implements Client {
	
	
	protected AccessRights rights = AccessRights.NONE;
	
	protected EmailAddress emailAddress = EmailAddress.EMPTY;
	
	protected ClientCore() {
		// do nothing
	}
	
	protected void initialize(AccessRights myRights, EmailAddress myEmailAddress) {
		rights = myRights;
		setEmailAddress(myEmailAddress);
	}

	public AccessRights getRights() {
		return rights;
	}

	public void setRights(AccessRights newRights) {
		rights = newRights;
	}

	public boolean hasRights(AccessRights otherRights) {
		return AccessRights.hasRights(rights, otherRights);
	}

	public boolean hasGuestRights() {
		return hasRights(AccessRights.GUEST);
	}

	public boolean hasUserRights() {
		return hasRights(AccessRights.USER);
	}

	public boolean hasModeratorRights
	() {
		return hasRights(AccessRights.MODERATOR);
	}

	public boolean hasAdministratorRights() {
		return hasRights(AccessRights.ADMINISTRATOR);
	}
	
	public EmailAddress getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(EmailAddress newEmailAddress) {
		emailAddress = newEmailAddress;
	}
	
	// Role Management
	protected HashMap<Class<? extends ClientRole>, ClientRole> roles = new HashMap<Class<? extends ClientRole>, ClientRole>();	
	
	public boolean hasRole(Class<? extends ClientRole> role) {
		return roles.containsKey(role);
	}
	
	public ClientRole getRole(Class<? extends ClientRole> role) {
		return (ClientRole) roles.get(role);
	}
	
	public void addRole(Class<? extends ClientRole> role, ClientRole client) {
		roles.put(role, client);
	}
	
	public void removeRole(Class<? extends ClientRole> role) {
		roles.remove(role);
	}
	
}
