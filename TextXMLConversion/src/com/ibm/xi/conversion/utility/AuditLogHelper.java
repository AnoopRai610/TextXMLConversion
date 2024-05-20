package com.ibm.xi.conversion.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sap.it.api.msglog.MessageLog;

/**
 * Use to write in message log in Audit log. </br>
 * Writing Log Entries to Audit Log</a>
*/
public class AuditLogHelper {
	
	private MessageLog audit;

	/** 
	 * @param msgID Message ID of message.
	*/
	public AuditLogHelper(MessageLog log) {
			this.audit = log;
	}

	public void addLog(String status, String message) {
		if (this.audit != null) {
			this.audit.setStringProperty(status + " [" + new SimpleDateFormat("yyyyMMdd'T'HHmmssSSSS").format(new Date()) + "]", message);
		} else {
			System.out.println("Audit Log: " + message);
		}
	}
}
