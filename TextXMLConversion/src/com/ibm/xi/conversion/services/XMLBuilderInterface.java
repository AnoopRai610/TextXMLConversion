package com.ibm.xi.conversion.services;

import org.w3c.dom.Document;

import com.ibm.xi.conversion.utility.ConversionException;

/**
 * Use to convert data in List to required XML format Its very messy code,
 * please try to understand it before making any change.
 * 
 * @author IBM GBS, Kolkata, IN (AnoopRai)
 * @version 1.0
 */
public interface XMLBuilderInterface {
	/** First create instance of XMLBuilder that implements this interface.
	 * After that this method is use to create XML Document
	 * 
	 * @return {@link Document}
	*/
	Document creatXML() throws ConversionException;
	
	/**Use to convert xml Document to byte array
	 * 
	 * @param document xml document
	 * 
	 * @return byte array of document
	*/
	byte[] getDoctoByte(Document docment) throws ConversionException;
	

}
