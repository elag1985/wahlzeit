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

package org.wahlzeit.services;

import java.util.*;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.*;

/**
 * The EmailServer service lets clients send emails.
 * 
 * @author dirkriehle
 *
 */
public class AbstractEmailServer implements EmailServer{

	/**
	 * 
	 */
	protected static final EmailServer REAL_INSTANCE = new AbstractEmailServer();
	protected static final EmailServer NULL_INSTANCE = new NullEmailServer();

	/**
	 * 
	 */
	protected static EmailServer instance = getInstanceFromMode();

	/**
	 * 
	 */
	protected Session session = null;

	/**
	 * 
	 */
	public static EmailServer getInstance() {
		return instance;
	}

	/**
	 * 
	 */
	public static EmailServer getInstanceFromMode() {
		if (SysLog.isInProductionMode()) {
			return REAL_INSTANCE;
		} else {
			return NULL_INSTANCE;
		}
	}

	/**
	 * @methodtype set
	 */
	public static void setInstance(EmailServer server) {
		instance = server;
	}

	/**
	 * 
	 */
	public static void setNullInstance() {
		instance = NULL_INSTANCE;
	}


	/**
	 * 
	 * @methodproperties convenience
	 */
	public synchronized void sendEmail(EmailAddress from, EmailAddress to, String subject, String body) {
		sendEmail(from, to, EmailAddress.NONE, subject, body);
	}

	@Override
	public void sendEmail(EmailAddress from, EmailAddress to, EmailAddress bcc,
			String subject, String body) {
		// TODO Auto-generated method stub
		
	}

	
}

