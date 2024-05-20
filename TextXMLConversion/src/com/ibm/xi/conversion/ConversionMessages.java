package com.ibm.xi.conversion;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.ibm.xi.conversion.utility.ConversionException;
import com.sap.it.api.msglog.MessageLog;

/**
 * This class use as to initiate the conversion JAR. You can also directly
 * access the main conversion class. But its better to use this class as it
 * provide access to all conversion class from one point only.
 * 
 * @author IBM GBS, IN (AnoopRai)
 * @version 1.0
 */
public class ConversionMessages {
	private ConversionInterface conversionInterface;
	private Map<String, String> parameterValues;
	private MessageLog log;

	/**
	 * Use this constructor to initialize the conversion class and parameters in Map
	 * variable.
	 * 
	 * @param parameterValues Parameters as Map<String,String> variable
	 */
	public ConversionMessages(Map<String, String> parameterValues) {
		this(parameterValues.get("conversionClass"), parameterValues, null);
	}

	/**
	 * Use this constructor to initialize the conversion class and parameters in Map
	 * variable. Using this method you can write audit logs also.
	 * 
	 * @param parameterValues Parameters as Map<String,String> variable
	 * @param msgID           Message ID to write audit logs using AuditAccess
	 * 
	 */
	public ConversionMessages(Map<String, String> parameterValues, MessageLog log) {
		this(parameterValues.get("conversionClass"), parameterValues, log);
	}

	/**
	 * Use this constructor to initialize the conversion class and parameters in Map
	 * variable. As well as execute method call directly using it.
	 * 
	 * @param parameterValues Parameters as Map<String,String> variable
	 * @param className       Need in case conversionClass is not part of map
	 *                        parameters
	 * @param inputStream     InputStream
	 * @param outputStream    OutputStream
	 * 
	 */
	public ConversionMessages(String className, InputStream inputStream, OutputStream outputStream,
			Map<String, String> parameterValues) {
		this(className, inputStream, outputStream, parameterValues, null);
	}

	/**
	 * Use this constructor to initialize the conversion class and parameters in Map
	 * variable. As well as execute method call directly using it.
	 * 
	 * @param parameterValues Parameters as Map<String,String> variable
	 * @param inputStream     InputStream
	 * @param outputStream    OutputStream
	 * 
	 */
	public ConversionMessages(InputStream inputStream, OutputStream outputStream, Map<String, String> parameterValues)
			throws ConversionException {
		this(parameterValues.get("conversionClass"), inputStream, outputStream, parameterValues, null);
	}

	/**
	 * Use this constructor to initialize the conversion class and parameters in Map
	 * variable. Using this method you can write audit logs also. As well as execute
	 * method call directly using it.
	 * 
	 * @param parameterValues Parameters as Map<String,String> variable
	 * @param msgID           Message ID to write audit logs using AuditAccess
	 * @param inputStream     InputStream
	 * @param outputStream    OutputStream
	 * 
	 */
	public ConversionMessages(InputStream inputStream, OutputStream outputStream, Map<String, String> parameterValues,
			MessageLog log) throws ConversionException {
		this(parameterValues.get("conversionClass"), inputStream, outputStream, parameterValues, log);
	}

	/**
	 * Use this constructor to initialize the conversion class and parameters in Map
	 * variable. Using this method you can write audit logs also.
	 * 
	 * @param parameterValues Parameters as Map<String,String> variable
	 * @param msgID           Message ID to write audit logs using AuditAccess
	 * @param className       Need in case conversionClass is not part of map
	 *                        parameters
	 * 
	 * @exception RuntimeException
	 * 
	 */
	@SuppressWarnings("deprecation")
	public ConversionMessages(String className, Map<String, String> parameterValues, MessageLog log) {
		try {
			this.parameterValues = parameterValues;
			conversionInterface = (ConversionInterface) Class.forName(className).newInstance();
			this.log = log;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException(e.getClass().getName() + " : " + e.getMessage(), e.getCause());
		}
	}

	/**
	 * Use this constructor to initialize the conversion class and parameters in Map
	 * variable. Using this method you can write audit logs also. As well as execute
	 * method call directly using it.
	 * 
	 * @param parameterValues Parameters as Map<String,String> variable
	 * @param msgID           Message ID to write audit logs using AuditAccess
	 * @param className       Need in case conversionClass is not part of map
	 *                        parameters
	 * @param inputStream     InputStream
	 * @param outputStream    OutputStream
	 * 
	 * @exception RuntimeException
	 * 
	 */
	public ConversionMessages(String className, InputStream inputStream, OutputStream outputStream,
			Map<String, String> parameterValues, MessageLog log) {
		this(className, parameterValues, log);
		try {
			execute(inputStream, outputStream);
		} catch (ConversionException e) {
			throw new RuntimeException(e.getClass().getName() + " : " + e.getMessage(), e.getCause());
		}
	}

	/**
	 * Use this method call conversion with InputStream and OutputStream.
	 * 
	 * @param inputStream  InputStream
	 * @param outputStream OutputStream
	 * 
	 * @exception ConversionException
	 * 
	 */
	public final void execute(InputStream inputStream, OutputStream outputStream) throws ConversionException {
		try {
			if (log == null)
				conversionInterface.execute(inputStream, outputStream, parameterValues);
			else
				conversionInterface.execute(inputStream, outputStream, parameterValues, log);
		} catch (ConversionException e) {
			throw new ConversionException(e.getClass().getName() + " : " + e.getMessage(), e.getCause());
		}
	}

	/**
	 * Use this method call conversion with InputStream and return converted message
	 * as byte array.
	 * 
	 * @param inputStream InputStream
	 * @return converted message as byte array
	 * 
	 * @exception ConversionException
	 * 
	 */
	public final byte[] getByteArray(InputStream inputStream) throws ConversionException {
		try {
			// Create a BufferedOutputStream..
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			OutputStream outputStream = new BufferedOutputStream(byteArrayOutputStream);
			execute(inputStream, outputStream);
			outputStream.close();
			return byteArrayOutputStream.toByteArray();
		} catch (ConversionException | IOException e) {
			throw new ConversionException(e.getClass().getName() + " : " + e.getMessage(), e.getCause());
		}
	}
}
