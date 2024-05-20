package com.ibm.xi.conversion;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.ibm.xi.conversion.utility.ConversionException;
import com.sap.it.api.msglog.MessageLog;

public interface ConversionInterface {

	public void execute(InputStream input, OutputStream output, Map<String, String> parameterValues)
			throws ConversionException;

	void execute(InputStream input, OutputStream output, Map<String, String> parameterValues, MessageLog log)
			throws ConversionException;

}
