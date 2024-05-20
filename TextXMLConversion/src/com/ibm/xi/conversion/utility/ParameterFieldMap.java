package com.ibm.xi.conversion.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is highly specific class use to set some HashMap parameters that will be
 * helpful while creating XML documents from CSV or flat files. </br>
 * <b>Don't create instance of this class with empty constructor and other
 * purpose other than specified above.</b> </br>
 * </br>
 * Better to use <i> ParameterFieldMap param = new ParameterFieldMap(new
 * ParameterHelper())</i>
 * 
 * @author IBM GBS, IN (AnoopRai)
 * @version 1.0
 */
public class ParameterFieldMap {

	/** Use to map field names with field structure */
	private Map<String, Object> paramFieldMap;
	/** Use to map with key field value */
	private Map<String, String> keyFieldValueMap;
	/** Use to map with length of each element with field structure */
	private Map<String, Object> paramLengthMap;
	private Map<String, String> keyFieldSeparatorMap;
	/** Use to set global separator clause */
	private boolean separator = true;
	/** Use to set global keyFieldName clause */
	private boolean globalKeyFieldName = false;
	/** Use to set keyFieldValue requirement clause. false for single recordSetStructure */
	private boolean keyFieldValueRequire = false;
	/** Use to get parameter values set in module parameters */
	private ParameterHelper param;

	/**
	 * <b>Don't create instance of this class with empty constructor and other
	 * purpose other than specified above.</b>
	 */
	public ParameterFieldMap() throws Exception {
		throw new Exception(
				"Never create instance of " + this.getClass().getName() + " class with empty counstructor");
	}

	/**
	 * This is highly specific class use to set some HashMap parameters that will be
	 * helpful while creating XML documents from CSV or flat files. </br>
	 * <b>Don't create instance of this class with empty constructor and other
	 * purpose other than specified above.</b> </br>
	 * </br>
	 * Better to use <i> ParamFieldMap param = new ParamFieldMap(new
	 * ParameterHelper())</i>
	 * 
	 * @param param Instance of ParameterHelper class
	 */
	public ParameterFieldMap(ParameterHelper param) throws ConversionException {
		this("recordsetStructure", "fieldNames", "keyFieldValue", "fieldFixedLengths", "fieldSeparator", param);
	}

	/**
	 * 
	 * @param paramName         recordsetStructure
	 * @param fieldNames        fieldNames
	 * @param keyFieldValue     keyFieldValue
	 * @param fieldFixedLengths fieldFixedLengths
	 * @param fieldSeparator    fieldSeparator
	 * @param param             Instance of ParameterHelper class
	 */
	public ParameterFieldMap(String paramName, String fieldNames, String keyFieldValue, String fieldFixedLengths,
			String fieldSeparator, ParameterHelper param) throws ConversionException {
		this.param = param;
		this.paramFieldMap = new HashMap<>();
		this.keyFieldValueMap = new HashMap<>();
		if (param.getParameter("fieldSeparator") == null) {
			this.separator = false;
			this.keyFieldSeparatorMap = new HashMap<>();
			this.paramLengthMap = new HashMap<>();
		}
		if(param.getParameter("keyFieldName")!=null)
			setGlobalKeyFieldName(true);
			
		setValuesToParamHashMaps(paramName, fieldNames, keyFieldValue, fieldFixedLengths, fieldSeparator);
	}

	/**
	 * Main method to set all values to HashMap variables defined under this class.
	 * 
	 * @param paramName         recordsetStructure
	 * @param fieldNames        fieldNames
	 * @param keyFieldValue     keyFieldValue
	 * @param fieldFixedLengths fieldFixedLengths
	 * @throws ConversionException 
	 * 
	 */
	private void setValuesToParamHashMaps(String paramName, String fieldNames, String keyFieldValue,
			String fieldFixedLengths, String fieldSeparator) throws ConversionException {

		String structureSet = param.getMandatoryParameter(paramName);
		Utility util = new Utility();
		this.keyFieldValueRequire = structureSet.contains(",");
		// Check duplicate recordsetStructure
		if (!(util.checkDuplicateFromArray(structureSet.split(","))))
			throw new ConversionException(util.getFirstDuplicateFromArray(structureSet.split(",")) + " is duplicate"
					+ " in " + paramName + ". Please check.");
		for (String setName : structureSet.split(",")) {

			// Set keyFieldValue value as key of HashMap to get specific keySetStructure
			// name
			if (this.keyFieldValueRequire)
				this.setKeyFieldValueMap(setName, keyFieldValue);

			// Set field names to recordSet
			this.setParamFieldMap(setName, fieldNames);

			// Set fix length in case of no separator in parameters
			if (!this.separator)
				if(!this.setKeyFieldSeparatorMap(setName, fieldSeparator)) 			//Set fieldSeparator value. if not available check for fixedLength value
					this.setParamLengthMap(setName, fieldNames, fieldFixedLengths);
		}

	}

	/** Use to get fields for specific recordSet */
	public Object getSpecificParamFieldValue(String keyName) {
		return this.paramFieldMap.get(keyName);
	}

	/** Use to get recordSet for specific keyValue */
	public Object getSpecificParamKeyValue(String keyName) {
		return this.keyFieldValueMap.get(keyName);
	}

	/** Use to get lengths for specific recordSet in case of fixed length file*/
	public Object getSpecificParamLengthValue(String keyName) {
		return this.paramLengthMap.get(keyName);
	}
	
	/** Use to get fieldSeparator for specific recordSet */
	public Object getSpecificParamFieldSeparatorValue(String keyName) {
		return this.keyFieldSeparatorMap.get(keyName);
	}

	public Map<String, Object> getParamFieldMap() {
		return this.paramFieldMap;
	}

	private void setParamFieldMap(String setName, String fieldNames) throws ConversionException {
		List<String> fields = new ArrayList<String>();
		Utility util = new Utility();
		String setFields = param.getMandatoryParameter(setName + "." + fieldNames);
		if (util.checkDuplicateFromArray(setFields.split(",")))
			for (String field : setFields.split(","))
				fields.add(field);
		else
			throw new ConversionException("For record set " + setName + " fields are not unique."
					+ util.getFirstDuplicateFromArray(setFields.split(",")) + ".Duplicate fields name not allowed.");
		this.paramFieldMap.put(setName, fields);
	}

	public Map<String, String> getKeyFieldValueMap() {
		return keyFieldValueMap;
	}

	private void setKeyFieldValueMap(String setName, String keyFieldValue) throws ConversionException {
		String keyValue = this.param.getMandatoryParameter(setName + "." + keyFieldValue);
		if (!this.keyFieldValueMap.containsKey(keyValue)) {
			this.keyFieldValueMap.put(keyValue, setName);
		} else
			throw new ConversionException("Record with duplicate key value for " + setName);
	}

	public Map<String, Object> getParamLengthMap() {
		return paramLengthMap;
	}

	private void setParamLengthMap(String setName, String fieldNames, String fieldFixedLengths) throws ConversionException {
		List<Integer> fieldLengths = new ArrayList<>();
		String fieldLen = param.getMandatoryParameter(setName + "." + fieldFixedLengths);
		for (String length : fieldLen.split(",")) {
			fieldLengths.add(Integer.parseInt(length.trim()));
		}
		if (fieldLengths.size() != this.param.getMandatoryParameter(setName + "." + fieldNames).split(",").length)
			throw new ConversionException("For record set " + setName + " counts of " + fieldNames + " and "
					+ fieldFixedLengths + " values are not same. Please check.");
		this.paramLengthMap.put(setName, fieldLengths);
	}
	
	private boolean setKeyFieldSeparatorMap(String setName, String fieldSeparator) {
		if(this.param.getParameter(setName+"."+fieldSeparator)!=null) {
			this.keyFieldSeparatorMap.put(setName,fieldSeparator);
			return true;
		}
		return false;
	}
	
	public Map<String, String> getKeyFieldSeparatorMap() {
		return keyFieldSeparatorMap;
	}

	public boolean isSeparator() {
		return separator;
	}

	public boolean isKeyFieldValueRequire() {
		return keyFieldValueRequire;
	}

	@Override
	public String toString() {
		return "ParamFieldMap [paramFieldMap=" + paramFieldMap + ", keyFieldValueMap=" + keyFieldValueMap
				+ ", paramLengthMap=" + paramLengthMap + ",  keyFieldSeparatorMap"+ keyFieldSeparatorMap +"]";
	}

	public void printString() {
		System.out.println("ParamFieldMap [paramFieldMap=" + paramFieldMap + ",\nkeyFieldValueMap=" + keyFieldValueMap
				+ ",\nparamLengthMap=" + paramLengthMap + ",  keyFieldSeparatorMap"+ keyFieldSeparatorMap + "]");
	}

	public boolean isGlobalKeyFieldName() {
		return globalKeyFieldName;
	}

	public void setGlobalKeyFieldName(boolean globalKeyFieldName) {
		this.globalKeyFieldName = globalKeyFieldName;
	}

}
