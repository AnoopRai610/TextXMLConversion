package com.ibm.xi.conversion.utility;

/**Use to throw exception..
 * Extends this class based on where you are going to use this mode.
 * </br>In case of MM exception extends it {@link StreamTransformationException}
 * </br>In case of CM exception extends it {@link ModuleException}
 * 
 * @version 1.0
*/
public class ConversionException extends Exception {
	private static final long serialVersionUID = 1L;
	public ConversionException(String message) {
		super(message);
	}
	public ConversionException(String message, Throwable rootCause) {
		super(message, rootCause);
	}
}
