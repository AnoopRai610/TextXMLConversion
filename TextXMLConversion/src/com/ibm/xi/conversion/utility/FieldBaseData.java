package com.ibm.xi.conversion.utility;

import java.util.List;

public class FieldBaseData {
	
	private String recordSetName;
	private List<String> dataSet;
	
	
	public FieldBaseData(String recordSetName, List<String> dataSet) {
		super();
		this.recordSetName = recordSetName;
		this.setDataSet(dataSet);
	}
	/**
	 * @return the recordSetName
	 */
	public String getRecordSetName() {
		return recordSetName;
	}
	/**
	 * @param recordSetName the recordSetName to set
	 */
	public void setRecordSetName(String recordSetName) {
		this.recordSetName = recordSetName;
	}
	/**
	 * @return the dataSet
	 */
	public List<String> getDataSet() {
		return dataSet;
	}
	/**
	 * @param dataSet the dataSet to set
	 */
	public void setDataSet(List<String> dataSet) {
		this.dataSet = dataSet;
	}
}
