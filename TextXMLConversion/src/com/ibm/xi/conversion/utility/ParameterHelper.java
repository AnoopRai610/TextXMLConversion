package com.ibm.xi.conversion.utility;

import java.util.List;

/**
 * This is class use to get parameters set in Map variable.
 * 
 * @version 1.0
 */
public class ParameterHelper {

	private final ParameterContext pc;

	public ParameterHelper(ParameterContext pc) {
		this.pc = pc;
	}

	public List<String> getParameterKeyList() {
		return this.pc.getContextDataKeys();
	}

	public String getMandatoryParameter(String paramName) throws ConversionException {
		String paramValue = this.pc.getContextData(paramName);
		if (paramValue == null) {
			throw new ConversionException("Mandatory parameter '" + paramName + "' is missing");
		}
		return paramValue;
	}

	public String getParameter(String paramName, String defaultValue) {
		String paramValue = this.pc.getContextData(paramName);
		if (paramValue == null && defaultValue != null) {
			paramValue = defaultValue;
		}
		return paramValue;
	}

	public String getGParameter(String paramName, String gParamName, String defaultValue) {
		String paramValue = getParameter(paramName + "." + gParamName);
		if (paramValue == null)
			paramValue = getParameter(gParamName, defaultValue);
		return paramValue;
	}

	public String getConditionallyMandatoryParameter(String paramName, String dependentParamName,
			String dependentParamValue) throws ConversionException {
		String paramValue = this.pc.getContextData(paramName);
		if (paramValue == null) {
			throw new ConversionException("Parameter '" + paramName + "' required when '" + dependentParamName + "' = "
					+ dependentParamValue);
		}
		return paramValue;
	}

	public String getParameter(String paramName) {
		return getParameter(paramName, null);
	}

	public int getIntParameter(String paramName, int defaultValue) throws ConversionException {
		String paramValue = getParameter(paramName, Integer.toString(defaultValue));
		try {
			int result = Integer.parseInt(paramValue);
			if (result < 0) {
				throw new ConversionException("Negative integers not allowed for " + paramName);
			}
			return result;
		} catch (NumberFormatException e) {
			throw new ConversionException("Only integers allowed for " + paramName);
		}
	}

	public int getIntParameter(String paramName) throws ConversionException {
		return getIntParameter(paramName, 0);
	}

	public int getIntMandatoryParameter(String paramName) throws ConversionException {
		getMandatoryParameter(paramName);
		return getIntParameter(paramName);
	}

	public boolean getBoolParameter(String paramName, String defaultValue) throws ConversionException {
		String paramValue = getParameter(paramName, defaultValue);
		if (paramValue.equalsIgnoreCase("Y") || paramValue.equalsIgnoreCase("YES")
				|| paramValue.equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean getBoolParameter(String paramName, boolean defaultValue) throws ConversionException {
		return getBoolParameter(paramName, String.valueOf(defaultValue));
	}

	public boolean getBoolParameter(String paramName) throws ConversionException {
		return getBoolParameter(paramName, "false");
	}

	public boolean getBoolMandatoryParameter(String paramName) throws ConversionException {
		getMandatoryParameter(paramName);
		return getBoolParameter(paramName);
	}

	public void checkParamValidValues(String paramName, String validValues) throws ConversionException {
		String paramValue = this.pc.getContextData(paramName);
		if (paramValue != null) {
			String[] valid = validValues.split(",");
			boolean found = false;
			for (int i = 0; i < valid.length; i++) {
				if (valid[i].trim().equalsIgnoreCase(paramValue)) {
					found = true;
					break;
				}
			}
			if (!found) {
				throw new ConversionException("Value '" + paramValue + "' not valid for parameter " + paramName);
			}
		}
	}

	public ParameterContext getParameterContext() {
		return this.pc;
	}

	public boolean hasParameter(String paramName) {
		return this.pc.containsKey(paramName);
	}
}
