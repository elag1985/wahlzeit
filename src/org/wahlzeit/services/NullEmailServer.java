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

import javax.mail.*;

//import org.wahlzeit.model.NullEmailServer;

/**
 * The NullEmailServer only pretends to send email.
 * 
 * @author dirkriehle
 *
 */
public class NullEmailServer implements EmailServer {		
	/**
	 * 
	 */	
	public void doSendEmail(Message msg) {
		SysLog.logInfo("pretending to send email...");
	}

	@Override
	public void sendEmail(EmailAddress from, EmailAddress to, String subject,
			String body) {
		// TODO Auto-generated method stub
		doSendEmail(null);
	}

	@Override
	public void sendEmail(EmailAddress from, EmailAddress to, EmailAddress bcc,
			String subject, String body) {
		// TODO Auto-generated method stub
		sendEmail(from, to, EmailAddress.NONE, subject, body);
	}

	
}
