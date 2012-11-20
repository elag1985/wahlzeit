package org.wahlzeit.services;

import javax.mail.Message;

import junit.framework.Assert;


public class MockEmailServer extends AbstractEmailServer {
	
	public MockEmailServer(){
		super();
	}

	/**
	 * 
	 * @methodproperties composed
	 */
	public synchronized void sendEmail(EmailAddress from, EmailAddress to, EmailAddress bcc, String subject, String body) {
		SysLog.logInfo("Mock Server");
	}
	
}
