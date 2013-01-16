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

import org.wahlzeit.services.EmailAddress;

public class ClientRole implements Client {

	protected ClientCore core;
	
	public ClientRole(ClientCore core) {
		this.core = core;
	}
	
	protected void initialize(AccessRights myRights, EmailAddress myEmailAddress){
		core.initialize(myRights, myEmailAddress);
	}
	
	public AccessRights getRights(){
		return core.getRights();
	}
	
	public void setRights(AccessRights newRights){
		core.setRights(newRights);
	}
	
	public boolean hasRights(AccessRights otherRights){
		return core.hasRights(otherRights);
	}
	
	public boolean hasGuestRights(){
		return core.hasGuestRights();
	}
	
	 public boolean hasUserRights(){
		 return core.hasUserRights();
	 }
	
	 public boolean hasModeratorRights(){
		 return core.hasModeratorRights();
	 }
	
	 public boolean hasAdministratorRights(){
		 return core.hasAdministratorRights();
	 }
	
	 public EmailAddress getEmailAddress(){
		 return core.getEmailAddress();
	 }
	 
	 public void setEmailAddress(EmailAddress newEmailAddress){
		 core.setEmailAddress(newEmailAddress);
	 }

	public boolean hasRole(Class<? extends ClientRole> role) {
		return core.hasRole(role);
	}

	public ClientRole getRole(Class<? extends ClientRole> role) {
		return core.getRole(role);
	}

	public void addRole(Class<? extends ClientRole> role, ClientRole client) {
		core.addRole(role, client);
		
	}

	public void removeRole(Class<? extends ClientRole> role) {
		core.removeRole(role);
	}
	 
	 
	
}
